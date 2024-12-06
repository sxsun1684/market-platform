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
 * Cart Service - Business Logic for Cart Operations
 **/
@Service
public class CartService {

    @Resource
    private CartMapper cartMapper;

    /**
     * Add a new item to the cart
     */
    public void add(Cart cart) {
        // Check if the user has already added the product to the cart. If so, just update the record's quantity (increment by 1)
        Cart dbCart = cartMapper.selectByUserIdAndGoodsId(cart.getUserId(), cart.getGoodsId());
        if (ObjectUtil.isNotEmpty(dbCart)) {
            dbCart.setNum(dbCart.getNum() + 1);
            cartMapper.updateById(dbCart);
        } else {
            cartMapper.insert(cart);
        }
    }

    /**
     * Delete an item from the cart by ID
     */
    public void deleteById(Integer id) {
        cartMapper.deleteById(id);
    }

    /**
     * Batch delete items from the cart
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            cartMapper.deleteById(id);
        }
    }

    /**
     * Update an item in the cart by ID
     */
    public void updateById(Cart cart) {
        cartMapper.updateById(cart);
    }

    /**
     * Select an item from the cart by ID
     */
    public Cart selectById(Integer id) {
        return cartMapper.selectById(id);
    }

    /**
     * Select all items in the cart
     */
    public List<Cart> selectAll(Cart cart) {
        return cartMapper.selectAll(cart);
    }

    /**
     * Paginated query of cart items
     */
    public PageInfo<Cart> selectPage(Cart cart, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            cart.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Cart> list = cartMapper.selectAll(cart);
        return PageInfo.of(list);
    }
}
