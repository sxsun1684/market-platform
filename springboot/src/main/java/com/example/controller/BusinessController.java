package com.example.controller;

import com.example.common.Result;
import com.example.entity.Business;
import com.example.service.BusinessService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * API controller for managing business-related operations.
 * Provides endpoints for adding, deleting, updating, and querying businesses.
 */
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    /**
     * Adds a new business.
     *
     * @param business The business entity to be added.
     * @return A success result.
     */
    @PostMapping("/add")
    public Result add(@RequestBody Business business) {
        businessService.add(business);
        return Result.success();
    }

    /**
     * Deletes a business by its ID.
     *
     * @param id The ID of the business to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        businessService.deleteById(id);
        return Result.success();
    }

    /**
     * Deletes multiple businesses in batch.
     *
     * @param ids The list of IDs of businesses to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        businessService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Updates a business by its ID.
     *
     * @param business The business entity containing updated information.
     * @return A success result.
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Business business) {
        businessService.updateById(business);
        return Result.success();
    }

    /**
     * Queries a business by its ID.
     *
     * @param id The ID of the business to query.
     * @return A result containing the queried business entity.
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Business business = businessService.selectById(id);
        return Result.success(business);
    }

    /**
     * Queries all businesses that match the provided criteria.
     *
     * @param business The criteria for querying businesses.
     * @return A result containing a list of matching businesses.
     */
    @GetMapping("/selectAll")
    public Result selectAll(Business business) {
        List<Business> list = businessService.selectAll(business);
        return Result.success(list);
    }

    /**
     * Queries businesses with pagination support.
     *
     * @param business The criteria for querying businesses.
     * @param pageNum The page number to retrieve (default is 1).
     * @param pageSize The number of records per page (default is 10).
     * @return A result containing a paginated list of businesses.
     */
    @GetMapping("/selectPage")
    public Result selectPage(Business business,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Business> page = businessService.selectPage(business, pageNum, pageSize);
        return Result.success(page);
    }
}
