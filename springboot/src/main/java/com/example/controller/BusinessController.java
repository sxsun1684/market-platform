package com.example.controller;

import com.example.common.Result;
import com.example.entity.Business;
import com.example.service.BusinessService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Merchant frontend operation interface
 **/
@RestController
@RequestMapping("/business")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    /**
     * Add a new business
     */
    @PostMapping("/add")
    public Result add(@RequestBody Business business) {
        businessService.add(business);
        return Result.success();
    }

    /**
     * Delete a business by ID
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        businessService.deleteById(id);
        return Result.success();
    }

    /**
     * Batch delete businesses
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        businessService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Update a business by ID
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Business business) {
        businessService.updateById(business);
        return Result.success();
    }

    /**
     * Query a business by ID
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Business business = businessService.selectById(id);
        return Result.success(business);
    }

    /**
     * Query all businesses
     */
    @GetMapping("/selectAll")
    public Result selectAll(Business business) {
        List<Business> list = businessService.selectAll(business);
        return Result.success(list);
    }

    /**
     * Paginated query for businesses
     */
    @GetMapping("/selectPage")
    public Result selectPage(Business business,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Business> page = businessService.selectPage(business, pageNum, pageSize);
        return Result.success(page);
    }

}
