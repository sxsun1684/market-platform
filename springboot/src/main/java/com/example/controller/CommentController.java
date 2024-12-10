package com.example.controller;

import com.example.common.Result;
import com.example.entity.Comment;
import com.example.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Controller for managing comment operations.
 * Provides endpoints for adding, deleting, updating, and querying comments.
 */
@RestController
@RequestMapping ("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * Add a new comment.
     *
     * @param comment The comment entity to be added.
     * @return A success result.
     */
    @PostMapping ("/add")
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);
        return Result.success();
    }

    /**
     * Delete a comment by its ID.
     *
     * @param id The ID of the comment to be deleted.
     * @return A success result.
     */
    @DeleteMapping ("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        commentService.deleteById(id);
        return Result.success();
    }

    /**
     * Delete multiple comments by their IDs.
     *
     * @param ids A list of IDs representing the comments to be deleted.
     * @return A success result.
     */
    @DeleteMapping ("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        commentService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Update an existing comment by its ID.
     *
     * @param comment The updated comment entity.
     * @return A success result.
     */
    @PutMapping ("/update")
    public Result updateById(@RequestBody Comment comment) {
        commentService.updateById(comment);
        return Result.success();
    }

    /**
     * Retrieve a comment by its ID.
     *
     * @param id The ID of the comment to retrieve.
     * @return A success result containing the retrieved comment entity.
     */
    @GetMapping ("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Comment comment = commentService.selectById(id);
        return Result.success(comment);
    }

    /**
     * Retrieve all comments associated with a specific goods ID.
     *
     * @param id The ID of the goods to retrieve comments for.
     * @return A success result containing a list of comments.
     */
    @GetMapping ("/selectByGoodsId")
    public Result selectByGoodsId(@RequestParam Integer id) {
        List<Comment> list = commentService.selectByGoodsId(id);
        return Result.success(list);
    }

    /**
     * Retrieve all comments that match the specified criteria.
     *
     * @param comment The comment entity containing the filter criteria.
     * @return A success result containing a list of matching comments.
     */
    @GetMapping ("/selectAll")
    public Result selectAll(Comment comment) {
        List<Comment> list = commentService.selectAll(comment);
        return Result.success(list);
    }

    /**
     * Retrieve comments with pagination support.
     *
     * @param comment  The comment entity containing the filter criteria.
     * @param pageNum  The page number to retrieve (default is 1).
     * @param pageSize The number of items per page (default is 10).
     * @return A success result containing paginated comment data.
     */
    @GetMapping ("/selectPage")
    public Result selectPage(Comment comment,
                             @RequestParam (defaultValue = "1") Integer pageNum,
                             @RequestParam (defaultValue = "10") Integer pageSize) {
        PageInfo<Comment> page = commentService.selectPage(comment, pageNum, pageSize);
        return Result.success(page);
    }
}
