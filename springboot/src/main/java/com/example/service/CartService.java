package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Cart;
import com.example.mapper.CartMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service layer for managing cart operations.
 * Provides functionalities for adding, deleting, updating, and querying cart items.
 */
@Service
public class CartService {

    @Resource
    private CartMapper cartMapper;

    /**
     * Adds a new item to the cart.
     * If the item is already in the cart, increments the quantity by 1.
     *
     * @param cart The cart entity containing user ID and product ID.
     */
    public void add(Cart cart) {
        // Check if the product is already in the user's cart
        Cart dbCart = cartMapper.selectByUserIdAndGoodsId(cart.getUserId(), cart.getGoodsId());
        if (ObjectUtil.isNotEmpty(dbCart)) {
            // If the product exists in the cart, increment its quantity
            dbCart.setNum(dbCart.getNum() + 1);
            cartMapper.updateById(dbCart);
        } else {
            // If the product does not exist, add it to the cart
            cartMapper.insert(cart);
        }
    }

    /**
     * Deletes a cart item by its ID.
     *
     * @param id The ID of the cart item to delete.
     */
    public void deleteById(Integer id) {
        cartMapper.deleteById(id);
    }

    /**
     * Deletes multiple cart items by their IDs in batch.
     *
     * @param ids A list of IDs of cart items to delete.
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            cartMapper.deleteById(id);
        }
    }

    /**
     * Updates a cart item by its ID.
     *
     * @param cart The updated cart entity.
     */
    public void updateById(Cart cart) {
        cartMapper.updateById(cart);
    }

    /**
     * Retrieves a cart item by its ID.
     *
     * @param id The ID of the cart item to retrieve.
     * @return The cart entity matching the given ID.
     */
    public Cart selectById(Integer id) {
        return cartMapper.selectById(id);
    }

    /**
     * Retrieves all cart items matching the given criteria.
     *
     * @param cart Filter criteria for querying cart items.
     * @return A list of cart entities matching the criteria.
     */
    public List<Cart> selectAll(Cart cart) {
        return cartMapper.selectAll(cart);
    }

    /**
     * Retrieves cart items with pagination based on filter criteria.
     * Automatically filters items based on the logged-in user's role.
     *
     * @param cart     Filter criteria for querying cart items.
     * @param pageNum  The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return A PageInfo object containing the paginated result.
     */
    public PageInfo<Cart> selectPage(Cart cart, Integer pageNum, Integer pageSize) {
        // Get the currently logged-in user
        Account currentUser = TokenUtils.getCurrentUser();

        // If the current user is a regular user, filter cart items by their user ID
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            cart.setUserId(currentUser.getId());
        }

        // Start pagination using PageHelper
        PageHelper.startPage(pageNum, pageSize);

        // Query the list of cart items based on filter criteria
        List<Cart> list = cartMapper.selectAll(cart);

        // Return the paginated result as a PageInfo object
        return PageInfo.of(list);
    }
}
