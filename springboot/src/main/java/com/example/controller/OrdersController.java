package com.example.controller;

import com.example.common.Result;
import com.example.entity.Orders;
import com.example.service.OrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller for managing orders.
 * Provides endpoints for adding, deleting, updating, and querying orders.
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Resource
    private OrdersService ordersService;

    /**
     * Add a new order.
     *
     * @param orders The order entity to be added.
     * @return A success result indicating the order was added successfully.
     */
    @PostMapping("/add")
    public Result add(@RequestBody Orders orders) {
        ordersService.add(orders);
        return Result.success();
    }

    /**
     * Delete an order by its ID.
     *
     * @param id The ID of the order to be deleted.
     * @return A success result indicating the order was deleted successfully.
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        ordersService.deleteById(id);
        return Result.success();
    }

    /**
     * Delete multiple orders by their IDs.
     *
     * @param ids A list of IDs representing the orders to be deleted.
     * @return A success result indicating the orders were deleted successfully.
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        ordersService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Update an existing order by its ID.
     *
     * @param orders The updated order entity.
     * @return A success result indicating the order was updated successfully.
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Orders orders) {
        ordersService.updateById(orders);
        return Result.success();
    }

    /**
     * Retrieve an order by its ID.
     *
     * @param id The ID of the order to retrieve.
     * @return A success result containing the retrieved order entity.
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Orders orders = ordersService.selectById(id);
        return Result.success(orders);
    }

    /**
     * Retrieve all orders that match the specified criteria.
     *
     * @param orders The order entity containing the filter criteria.
     * @return A success result containing a list of matching orders.
     */
    @GetMapping("/selectAll")
    public Result selectAll(Orders orders) {
        List<Orders> list = ordersService.selectAll(orders);
        return Result.success(list);
    }

    /**
     * Retrieve orders with pagination support.
     *
     * @param orders The order entity containing the filter criteria.
     * @param pageNum The page number to retrieve (default is 1).
     * @param pageSize The number of items per page (default is 10).
     * @return A success result containing paginated order data.
     */
    @GetMapping("/selectPage")
    public Result selectPage(Orders orders,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Orders> page = ordersService.selectPage(orders, pageNum, pageSize);
        return Result.success(page);
    }
}
