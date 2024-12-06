package com.example.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.*;
import com.example.mapper.*;
import com.example.utils.TokenUtils;
import com.example.utils.UserCF;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Goods information business processing
 **/
@Service
public class GoodsService {

    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private CollectMapper collectMapper;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CartMapper cartMapper;
    @Resource
    private OrdersMapper ordersMapper;

    /**
     * Add new goods
     */
    public void add(Goods goods) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            goods.setBusinessId(currentUser.getId());
        }
        goodsMapper.insert(goods);
    }

    /**
     * Delete by ID
     */
    public void deleteById(Integer id) {
        goodsMapper.deleteById(id);
    }

    /**
     * Batch delete
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            goodsMapper.deleteById(id);
        }
    }

    /**
     * Update by ID
     */
    public void updateById(Goods goods) {
        goodsMapper.updateById(goods);
    }

    /**
     * Select by ID
     */
    public Goods selectById(Integer id) {
        return goodsMapper.selectById(id);
    }

    /**
     * Select all goods
     */
    public List<Goods> selectAll(Goods goods) {
        return goodsMapper.selectAll(goods);
    }

    /**
     * Paginated query
     */
    public PageInfo<Goods> selectPage(Goods goods, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            goods.setBusinessId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list = goodsMapper.selectAll(goods);
        return PageInfo.of(list);
    }

    public List<Goods> selectTop15() {
        return goodsMapper.selectTop15();
    }

    public List<Goods> selectByTypeId(Integer id) {
        return goodsMapper.selectByTypeId(id);
    }

    public List<Goods> selectByBusinessId(Integer id) {
        return goodsMapper.selectByBusinessId(id);
    }

    public List<Goods> selectByName(String name) {
        return goodsMapper.selectByName(name);
    }

    public List<Goods> recommend() {
        Account currentUser = TokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(currentUser)) {
            // No user logged in
            return new ArrayList<>();
        }
        // What user actions can be considered as having a relationship with goods? Collecting, adding to cart, placing an order, commenting
        // 1. Get all collection information
        List<Collect> allCollects = collectMapper.selectAll(null);
        // 2. Get all cart information
        List<Cart> allCarts = cartMapper.selectAll(null);
        // 3. Get all order information
        List<Orders> allOrders = ordersMapper.selectAllOKOrders();
        // 4. Get all comment information
        List<Comment> allComments = commentMapper.selectAll(null);
        // 5. Get all user information
        List<User> allUsers = userMapper.selectAll(null);
        // 6. Get all goods information
        List<Goods> allGoods = goodsMapper.selectAll(null);

        // Define a List to store the relationship between each product and each user
        List<RelateDTO> data = new ArrayList<>();
        // Define a List to store the final goods that will be returned to the front end
        List<Goods> recommendResult;

        // Create a countdown latch to wait for all asynchronous processing to finish before proceeding
        CountDownLatch countDownLatch = new CountDownLatch(allGoods.size() * allUsers.size());
        // Create a thread pool
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // Start calculating the relationship data between each product and each user
        for (Goods goods : allGoods) {
            Integer goodsId = goods.getId();
            for (User user : allUsers) {
                threadPool.execute(() -> {
                    Integer userId = user.getId();
                    int index = 1;
                    // 1. Check if the user has collected the product, assigning a weight of 1 to collection
                    Optional<Collect> collectOptional = allCollects.stream().filter(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId)).findFirst();
                    if (collectOptional.isPresent()) {
                        index += 1;
                    }
                    // 2. Check if the user has added the product to their cart, assigning a weight of 2 to adding to cart
                    Optional<Cart> cartOptional = allCarts.stream().filter(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId)).findFirst();
                    if (cartOptional.isPresent()) {
                        index += 2;
                    }
                    // 3. Check if the user has placed an order for the product (completed orders), assigning a weight of 3 to orders
                    Optional<Orders> ordersOptional = allOrders.stream().filter(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId)).findFirst();
                    if (ordersOptional.isPresent()) {
                        index += 3;
                    }
                    // 4. Check if the user has commented on the product, assigning a weight of 2 to comments
                    Optional<Comment> commentOptional = allComments.stream().filter(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId)).findFirst();
                    if (commentOptional.isPresent()) {
                        index += 2;
                    }
                    if (index > 1) {
                        RelateDTO relateDTO = new RelateDTO(userId, goodsId, index);
                        data.add(relateDTO);
                    }
                    countDownLatch.countDown();
                });
            }
        }

        try {
            countDownLatch.await();
            threadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // After data preparation ends, feed all the data into the recommendation algorithm
            List<Integer> goodsIds = UserCF.recommend(currentUser.getId(), data);
            // Convert goods IDs to goods
            recommendResult = goodsIds.stream().map(goodsId -> allGoods.stream()
                                                                       .filter(x -> x.getId().equals(goodsId)).findFirst().orElse(null))
                                      .limit(10).collect(Collectors.toList());
        }


        //        if (CollectionUtil.isEmpty(recommendResult)) {
        //            // Randomly recommend 10 products
        //            return getRandomGoods(10);
        //        }
        //        if (recommendResult.size() < 10) {
        //            int num = 10 - recommendResult.size();
        //            List<Goods> list = getRandomGoods(num);
        //            result.addAll(list);
        //        }
        return recommendResult;
    }

    private List<Goods> getRandomGoods(int num) {
        List<Goods> list = new ArrayList<>(num);
        List<Goods> goods = goodsMapper.selectAll(null);
        for (int i = 0; i < num; i++) {
            int index = new Random().nextInt(goods.size());
            list.add(goods.get(index));
        }
        return list;
    }
}
