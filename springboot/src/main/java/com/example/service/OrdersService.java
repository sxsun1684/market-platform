package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Cart;
import com.example.entity.Goods;
import com.example.entity.Orders;
import com.example.mapper.CartMapper;
import com.example.mapper.GoodsMapper;
import com.example.mapper.OrdersMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.relation.Role;
import java.util.Date;
import java.util.List;

/**
 * Service for handling order-related business logic.
 * Provides functionalities to add, delete, update, and query orders.
 */
@Service
public class OrdersService {

    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private CartMapper cartMapper;
    @Resource
    private GoodsMapper goodsMapper;

    /**
     * Adds a new order and processes associated cart items.
     *
     * @param orders The order entity containing cart data to be processed.
     */
    public void add(Orders orders) {
        // Generate a unique order ID using the current date and time
        orders.setOrderId(DateUtil.format(new Date(), "yyyyMMddHHmmss"));

        // Process each item in the cart associated with the order
        for(Cart cart: orders.getCartData()) {
            Orders dbOrders = new Orders();
            BeanUtils.copyProperties(orders, dbOrders); // Copy common properties from the order entity
            dbOrders.setGoodsId(cart.getGoodsId());
            dbOrders.setBusinessId(cart.getBusinessId());
            dbOrders.setNum(cart.getNum());
            dbOrders.setPrice(cart.getNum() * cart.getGoodsPrice()); // Calculate the total price for the cart item
            ordersMapper.insert(dbOrders); // Insert the processed order into the database

            // Remove the processed item from the shopping cart
            cartMapper.deleteById(cart.getId());

            // Update the sales count for the associated goods
            Goods goods = goodsMapper.selectById(cart.getGoodsId());
            goods.setCount(goods.getCount() + cart.getNum());
            goodsMapper.updateById(goods);
        }
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id The ID of the order to delete.
     */
    public void deleteById(Integer id) {
        ordersMapper.deleteById(id);
    }

    /**
     * Deletes multiple orders by their IDs.
     *
     * @param ids List of IDs of orders to delete.
     */
    public void deleteBatch(List<Integer> ids) {
        for(Integer id: ids) {
            ordersMapper.deleteById(id);
        }
    }

    /**
     * Updates an order by its ID.
     *
     * @param orders The updated order entity.
     */
    public void updateById(Orders orders) {
        ordersMapper.updateById(orders);
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return The order entity matching the given ID.
     */
    public Orders selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    /**
     * Retrieves all orders matching the given criteria.
     *
     * @param orders The filter criteria for retrieving orders.
     * @return List of orders matching the criteria.
     */
    public List<Orders> selectAll(Orders orders) {
        return ordersMapper.selectAll(orders);
    }

    /**
     * Retrieves paginated orders based on filter criteria.
     *
     * @param orders   Filter criteria for retrieving orders.
     * @param pageNum  The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return Paginated list of orders.
     */
    public PageInfo<Orders> selectPage(Orders orders, Integer pageNum, Integer pageSize) {
        // Get the currently logged-in user
        Account currentUser = TokenUtils.getCurrentUser();

        // Apply user-specific filters
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            orders.setUserId(currentUser.getId());
        }

        // Apply business-specific filters
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            orders.setBusinessId(currentUser.getId());
        }

        // Start paginated query using PageHelper
        PageHelper.startPage(pageNum, pageSize);
        List<Orders> list = ordersMapper.selectAll(orders);
        return PageInfo.of(list);
    }
}
