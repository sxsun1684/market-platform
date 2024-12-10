package com.example.controller;

import com.example.common.Result;
import com.example.entity.Type;
import com.example.service.TypeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller for managing category information.
 * Provides endpoints for adding, deleting, updating, and querying categories.
 */
@RestController
@RequestMapping("/type")
public class TypeController {

    @Resource
    private TypeService typeService;

    /**
     * Add a new category.
     *
     * @param type The category entity to be added.
     * @return A success result.
     */
    @PostMapping("/add")
    public Result add(@RequestBody Type type) {
        typeService.add(type);
        return Result.success();
    }

    /**
     * Delete a category by its ID.
     *
     * @param id The ID of the category to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        typeService.deleteById(id);
        return Result.success();
    }

    /**
     * Delete multiple categories by their IDs.
     *
     * @param ids A list of IDs representing the categories to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        typeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Update an existing category by its ID.
     *
     * @param type The updated category entity.
     * @return A success result.
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Type type) {
        typeService.updateById(type);
        return Result.success();
    }

    /**
     * Retrieve a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return A success result containing the retrieved category entity.
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Type type = typeService.selectById(id);
        return Result.success(type);
    }

    /**
     * Retrieve all categories that match the specified criteria.
     *
     * @param type The category entity containing the filter criteria.
     * @return A success result containing a list of matching categories.
     */
    @GetMapping("/selectAll")
    public Result selectAll(Type type) {
        List<Type> list = typeService.selectAll(type);
        return Result.success(list);
    }

    /**
     * Retrieve categories with pagination support.
     *
     * @param type The category entity containing the filter criteria.
     * @param pageNum The page number to retrieve (default is 1).
     * @param pageSize The number of items per page (default is 10).
     * @return A success result containing paginated category data.
     */
    @GetMapping("/selectPage")
    public Result selectPage(Type type,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Type> page = typeService.selectPage(type, pageNum, pageSize);
        return Result.success(page);
    }
}
