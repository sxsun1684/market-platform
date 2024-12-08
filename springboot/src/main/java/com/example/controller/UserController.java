package com.example.controller;

import com.example.common.Result;
import com.example.entity.User;
import com.example.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * API controller for managing user-related operations.
 * Provides endpoints for adding, deleting, updating, and querying users.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * Adds a new user.
     *
     * @param user The user entity to be added.
     * @return A success result.
     */
    @PostMapping("/add")
    public Result add(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        userService.deleteById(id);
        return Result.success();
    }

    /**
     * Deletes multiple users in batch.
     *
     * @param ids The list of IDs of users to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        userService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Updates a user's information by their ID.
     *
     * @param user The user entity containing updated information.
     * @return A success result.
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    /**
     * Queries a user by their ID.
     *
     * @param id The ID of the user to query.
     * @return A result containing the queried user entity.
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        User user = userService.selectById(id);
        return Result.success(user);
    }

    /**
     * Queries all users that match the provided criteria.
     *
     * @param user The criteria for querying users.
     * @return A result containing a list of matching users.
     */
    @GetMapping("/selectAll")
    public Result selectAll(User user) {
        List<User> list = userService.selectAll(user);
        return Result.success(list);
    }

    /**
     * Queries users with pagination support.
     *
     * @param user The criteria for querying users.
     * @param pageNum The page number to retrieve (default is 1).
     * @param pageSize The number of records per page (default is 10).
     * @return A result containing a paginated list of users.
     */
    @GetMapping("/selectPage")
    public Result selectPage(User user,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<User> page = userService.selectPage(user, pageNum, pageSize);
        return Result.success(page);
    }
}
