package com.example.controller;

import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * API controller for managing administrator-related operations.
 * Provides endpoints for adding, deleting, updating, and querying administrators.
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * Adds a new administrator.
     *
     * @param admin The administrator entity to be added.
     * @return A success result.
     */
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success();
    }

    /**
     * Deletes an administrator by its ID.
     *
     * @param id The ID of the administrator to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }

    /**
     * Deletes multiple administrators in batch.
     *
     * @param ids The list of IDs of administrators to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        adminService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Updates an administrator by its ID.
     *
     * @param admin The administrator entity containing updated information.
     * @return A success result.
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Admin admin) {
        adminService.updateById(admin);
        return Result.success();
    }

    /**
     * Queries an administrator by its ID.
     *
     * @param id The ID of the administrator to query.
     * @return A result containing the queried administrator entity.
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Admin admin = adminService.selectById(id);
        return Result.success(admin);
    }

    /**
     * Queries all administrators that match the provided criteria.
     *
     * @param admin The criteria for querying administrators.
     * @return A result containing a list of matching administrators.
     */
    @GetMapping("/selectAll")
    public Result selectAll(Admin admin) {
        List<Admin> list = adminService.selectAll(admin);
        return Result.success(list);
    }

    /**
     * Queries administrators with pagination support.
     *
     * @param admin The criteria for querying administrators.
     * @param pageNum The page number to retrieve (default is 1).
     * @param pageSize The number of records per page (default is 10).
     * @return A result containing a paginated list of administrators.
     */
    @GetMapping("/selectPage")
    public Result selectPage(Admin admin,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Admin> page = adminService.selectPage(admin, pageNum, pageSize);
        return Result.success(page);
    }
}
