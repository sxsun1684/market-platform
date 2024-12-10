package com.example.controller;

import com.example.common.Result;
import com.example.entity.Collect;
import com.example.service.CollectService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller for managing user collection operations.
 * Provides endpoints for adding, deleting, updating, and querying collections.
 */
@RestController
@RequestMapping ("/collect")
public class CollectController {

    @Resource
    private CollectService collectService;

    /**
     * Add a new collection.
     *
     * @param collect The collection entity to be added.
     * @return A success result.
     */
    @PostMapping ("/add")
    public Result add(@RequestBody Collect collect) {
        collectService.add(collect);
        return Result.success();
    }

    /**
     * Delete a collection by its ID.
     *
     * @param id The ID of the collection to be deleted.
     * @return A success result.
     */
    @DeleteMapping ("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        collectService.deleteById(id);
        return Result.success();
    }

    /**
     * Delete multiple collections by their IDs.
     *
     * @param ids A list of IDs representing the collections to be deleted.
     * @return A success result.
     */
    @DeleteMapping ("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        collectService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Update an existing collection by its ID.
     *
     * @param collect The updated collection entity.
     * @return A success result.
     */
    @PutMapping ("/update")
    public Result updateById(@RequestBody Collect collect) {
        collectService.updateById(collect);
        return Result.success();
    }

    /**
     * Retrieve a collection by its ID.
     *
     * @param id The ID of the collection to retrieve.
     * @return A success result containing the retrieved collection entity.
     */
    @GetMapping ("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Collect collect = collectService.selectById(id);
        return Result.success(collect);
    }

    /**
     * Retrieve all collections that match the specified criteria.
     *
     * @param collect The collection entity containing the filter criteria.
     * @return A success result containing a list of matching collections.
     */
    @GetMapping ("/selectAll")
    public Result selectAll(Collect collect) {
        List<Collect> list = collectService.selectAll(collect);
        return Result.success(list);
    }

    /**
     * Retrieve collections with pagination support.
     *
     * @param collect  The collection entity containing the filter criteria.
     * @param pageNum  The page number to retrieve (default is 1).
     * @param pageSize The number of items per page (default is 10).
     * @return A success result containing paginated collection data.
     */
    @GetMapping ("/selectPage")
    public Result selectPage(Collect collect,
                             @RequestParam (defaultValue = "1") Integer pageNum,
                             @RequestParam (defaultValue = "10") Integer pageSize) {
        PageInfo<Collect> page = collectService.selectPage(collect, pageNum, pageSize);
        return Result.success(page);
    }

}
