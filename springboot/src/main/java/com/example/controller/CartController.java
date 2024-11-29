package com.example.controller;

import com.example.common.Result;
import com.example.entity.Cart;
import com.example.service.CartService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Cart Frontend Operation Interface
 **/
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * Add a new item to the cart
     */
    @PostMapping("/add")
    public Result add(@RequestBody Cart cart) {
        cartService.add(cart);
        return Result.success();
    }

    /**
     * Delete an item from the cart by ID
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        cartService.deleteById(id);
        return Result.success();
    }

    /**
     * Delete multiple items from the cart
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        cartService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Update cart information
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Cart cart) {
        cartService.updateById(cart);
        return Result.success();
    }

    /**
     * Retrieve cart information by ID
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Cart cart = cartService.selectById(id);
        return Result.success(cart);
    }

    /**
     * Retrieve all cart items
     */
    @GetMapping("/selectAll")
    public Result selectAll(Cart cart) {
        List<Cart> list = cartService.selectAll(cart);
        return Result.success(list);
    }

    /**
     * Paginated query for cart items
     */
    @GetMapping("/selectPage")
    public Result selectPage(Cart cart,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Cart> page = cartService.selectPage(cart, pageNum, pageSize);
        return Result.success(page);
    }

}
