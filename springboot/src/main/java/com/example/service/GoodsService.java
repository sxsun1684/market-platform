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
 * Service for handling goods-related business logic.
 * Provides functionality to add, delete, update, query, and recommend goods.
 */
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
     * Adds a new good to the database.
     *
     * @param goods The goods entity to be added.
     */
    public void add(Goods goods) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            goods.setBusinessId(currentUser.getId());
        }
        goodsMapper.insert(goods);
    }

    /**
     * Deletes a good by its ID.
     *
     * @param id The ID of the good to delete.
     */
    public void deleteById(Integer id) {
        goodsMapper.deleteById(id);
    }

    /**
     * Deletes multiple goods by their IDs.
     *
     * @param ids List of IDs of goods to delete.
     */
    public void deleteBatch(List<Integer> ids) {
        for(Integer id: ids) {
            goodsMapper.deleteById(id);
        }
    }

    /**
     * Updates the details of a good by its ID.
     *
     * @param goods The updated goods entity.
     */
    public void updateById(Goods goods) {
        goodsMapper.updateById(goods);
    }

    /**
     * Retrieves a good by its ID.
     *
     * @param id The ID of the good to retrieve.
     * @return The goods entity.
     */
    public Goods selectById(Integer id) {
        return goodsMapper.selectById(id);
    }

    /**
     * Retrieves all goods matching the given filters.
     *
     * @param goods Filter criteria.
     * @return List of goods matching the criteria.
     */
    public List<Goods> selectAll(Goods goods) {
        return goodsMapper.selectAll(goods);
    }

    /**
     * Retrieves goods with pagination support.
     *
     * @param goods    Filter criteria.
     * @param pageNum  Page number.
     * @param pageSize Number of records per page.
     * @return Paginated list of goods.
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

    /**
     * Retrieves the top 15 goods.
     *
     * @return List of top 15 goods.
     */
    public List<Goods> selectTop15() {
        return goodsMapper.selectTop15();
    }

    /**
     * Retrieves goods by their type ID.
     *
     * @param id The type ID.
     * @return List of goods with the given type ID.
     */
    public List<Goods> selectByTypeId(Integer id) {
        return goodsMapper.selectByTypeId(id);
    }

    /**
     * Retrieves goods by their business ID.
     *
     * @param id The business ID.
     * @return List of goods associated with the given business ID.
     */
    public List<Goods> selectByBusinessId(Integer id) {
        return goodsMapper.selectByBusinessId(id);
    }

    /**
     * Retrieves goods by their name.
     *
     * @param name The name of the goods.
     * @return List of goods matching the given name.
     */
    public List<Goods> selectByName(String name) {
        return goodsMapper.selectByName(name);
    }

    /**
     * Recommends goods for the currently logged-in user based on their interactions.
     *
     * @return List of recommended goods.
     */
    public List<Goods> recommend() {
        Account currentUser = TokenUtils.getCurrentUser();
        if (ObjectUtil.isEmpty(currentUser)) {
            return new ArrayList<>();
        }

        // Fetch all user and goods interaction data
        List<Collect> allCollects = collectMapper.selectAll(null);
        List<Cart> allCarts = cartMapper.selectAll(null);
        List<Orders> allOrders = ordersMapper.selectAllOKOrders();
        List<Comment> allComments = commentMapper.selectAll(null);
        List<User> allUsers = userMapper.selectAll(null);
        List<Goods> allGoods = goodsMapper.selectAll(null);

        List<RelatedAlgo> data = new ArrayList<>();
        List<Goods> recommendResult;

        CountDownLatch countDownLatch = new CountDownLatch(allGoods.size() * allUsers.size());
        ExecutorService threadPool = Executors.newCachedThreadPool();

        // Build user-goods relationship data
        for(Goods goods: allGoods) {
            Integer goodsId = goods.getId();
            for(User user: allUsers) {
                threadPool.execute(() -> {
                    Integer userId = user.getId();
                    int index = 1;

                    if (allCollects.stream().anyMatch(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId))) {
                        index += 1;
                    }
                    if (allCarts.stream().anyMatch(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId))) {
                        index += 2;
                    }
                    if (allOrders.stream().anyMatch(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId))) {
                        index += 3;
                    }
                    if (allComments.stream().anyMatch(x -> x.getGoodsId().equals(goodsId) && x.getUserId().equals(userId))) {
                        index += 2;
                    }

                    if (index > 1) {
                        data.add(new RelatedAlgo(userId, goodsId, index));
                    }
                    countDownLatch.countDown();
                });
            }
        }

        try {
            countDownLatch.await();
            threadPool.shutdown();
        } catch(InterruptedException e) {
            e.printStackTrace();
        } finally{
            List<Integer> goodsIds = UserCF.recommend(currentUser.getId(), data);
            recommendResult = goodsIds.stream()
                                      .map(goodsId -> allGoods.stream()
                                                              .filter(x -> x.getId().equals(goodsId))
                                                              .findFirst().orElse(null))
                                      .limit(10)
                                      .collect(Collectors.toList());
        }

        return recommendResult;
    }

    /**
     * Retrieves a random selection of goods.
     *
     * @param num The number of goods to select.
     * @return List of randomly selected goods.
     */
    private List<Goods> getRandomGoods(int num) {
        List<Goods> list = new ArrayList<>(num);
        List<Goods> goods = goodsMapper.selectAll(null);
        for(int i = 0 ; i < num ; i++) {
            int index = new Random().nextInt(goods.size());
            list.add(goods.get(index));
        }
        return list;
    }
}
