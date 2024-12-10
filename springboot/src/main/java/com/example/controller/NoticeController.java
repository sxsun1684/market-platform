package com.example.controller;

import com.example.common.Result;
import com.example.entity.Notice;
import com.example.service.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * Controller for managing notice information.
 * Provides endpoints for adding, deleting, updating, and querying notices.
 */
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    private NoticeService noticeService;

    /**
     * Add a new notice.
     *
     * @param notice The notice entity to be added.
     * @return A success result indicating the notice was added successfully.
     */
    @PostMapping("/add")
    public Result add(@RequestBody Notice notice) {
        noticeService.add(notice);
        return Result.success();
    }

    /**
     * Delete a notice by its ID.
     *
     * @param id The ID of the notice to be deleted.
     * @return A success result indicating the notice was deleted successfully.
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        noticeService.deleteById(id);
        return Result.success();
    }

    /**
     * Delete multiple notices by their IDs.
     *
     * @param ids A list of IDs representing the notices to be deleted.
     * @return A success result indicating the notices were deleted successfully.
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        noticeService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Update an existing notice.
     *
     * @param notice The updated notice entity.
     * @return A success result indicating the notice was updated successfully.
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Notice notice) {
        noticeService.updateById(notice);
        return Result.success();
    }

    /**
     * Retrieve a notice by its ID.
     *
     * @param id The ID of the notice to retrieve.
     * @return A success result containing the retrieved notice entity.
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Notice notice = noticeService.selectById(id);
        return Result.success(notice);
    }

    /**
     * Retrieve all notices that match the specified criteria.
     *
     * @param notice The notice entity containing the filter criteria.
     * @return A success result containing a list of matching notices.
     */
    @GetMapping("/selectAll")
    public Result selectAll(Notice notice) {
        List<Notice> list = noticeService.selectAll(notice);
        return Result.success(list);
    }

    /**
     * Retrieve notices with pagination support.
     *
     * @param notice The notice entity containing the filter criteria.
     * @param pageNum The page number to retrieve (default is 1).
     * @param pageSize The number of items per page (default is 10).
     * @return A success result containing paginated notice data.
     */
    @GetMapping("/selectPage")
    public Result selectPage(Notice notice,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Notice> page = noticeService.selectPage(notice, pageNum, pageSize);
        return Result.success(page);
    }
}
