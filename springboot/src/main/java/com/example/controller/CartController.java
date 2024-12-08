package com.example.controller;

import com.example.common.Result;
import com.example.entity.Cart;
import com.example.service.CartService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * API controller for managing shopping cart operations.
 * Provides endpoints for adding, deleting, updating, and querying cart items.
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private CartService cartService;

    /**
     * Adds a new item to the cart.
     *
     * @param cart The cart item information to be added.
     * @return A success result.
     */
    @PostMapping("/add")
    public Result add(@RequestBody Cart cart) {
        cartService.add(cart);
        return Result.success();
    }

    /**
     * Deletes an item from the cart by its ID.
     *
     * @param id The ID of the cart item to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        cartService.deleteById(id);
        return Result.success();
    }

    /**
     * Deletes multiple items from the cart in batch.
     *
     * @param ids The list of IDs of cart items to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        cartService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Updates cart information for a specific item.
     *
     * @param cart The cart item information containing updated details.
     * @return A success result.
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Cart cart) {
        cartService.updateById(cart);
        return Result.success();
    }

    /**
     * Retrieves cart information by its ID.
     *
     * @param id The ID of the cart item to retrieve.
     * @return A result containing the cart item details.
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Cart cart = cartService.selectById(id);
        return Result.success(cart);
    }

    /**
     * Retrieves all cart items that match the provided criteria.
     *
     * @param cart The criteria for querying cart items.
     * @return A result containing a list of cart items.
     */
    @GetMapping("/selectAll")
    public Result selectAll(Cart cart) {
        List<Cart> list = cartService.selectAll(cart);
        return Result.success(list);
    }

    /**
     * Retrieves cart items with pagination support.
     *
     * @param cart The criteria for querying cart items.
     * @param pageNum The page number to retrieve (default is 1).
     * @param pageSize The number of records per page (default is 10).
     * @return A result containing a paginated list of cart items.
     */
    @GetMapping("/selectPage")
    public Result selectPage(Cart cart,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Cart> page = cartService.selectPage(cart, pageNum, pageSize);
        return Result.success(page);
    }
}
