package com.example.controller;

import com.example.common.Result;
import com.example.entity.Collect;
import com.example.service.CollectService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Interface for frontend collection operations
 **/
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Resource
    private CollectService collectService;

    /**
     * Add a new collection
     */
    @PostMapping("/add")
    public Result add(@RequestBody Collect collect) {
        collectService.add(collect);
        return Result.success();
    }

    /**
     * Delete a collection by ID
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        collectService.deleteById(id);
        return Result.success();
    }

    /**
     * Batch delete collections
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        collectService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Update a collection
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Collect collect) {
        collectService.updateById(collect);
        return Result.success();
    }

    /**
     * Query a collection by ID
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Collect collect = collectService.selectById(id);
        return Result.success(collect);
    }

    /**
     * Query all collections
     */
    @GetMapping("/selectAll")
    public Result selectAll(Collect collect ) {
        List<Collect> list = collectService.selectAll(collect);
        return Result.success(list);
    }

    /**
     * Paginated query for collections
     */
    @GetMapping("/selectPage")
    public Result selectPage(Collect collect,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Collect> page = collectService.selectPage(collect, pageNum, pageSize);
        return Result.success(page);
    }

}
