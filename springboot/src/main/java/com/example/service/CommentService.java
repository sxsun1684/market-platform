package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Comment;
import com.example.mapper.CommentMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service layer for handling comment-related business logic.
 * Provides functionalities for adding, deleting, updating, and querying comments.
 */
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    /**
     * Adds a new comment.
     * Automatically sets the current timestamp for the comment.
     *
     * @param comment The comment entity to be added.
     */
    public void add(Comment comment) {
        // Set the current time as the comment timestamp
        comment.setTime(DateUtil.now());
        commentMapper.insert(comment);
    }

    /**
     * Deletes a comment by its ID.
     *
     * @param id The ID of the comment to be deleted.
     */
    public void deleteById(Integer id) {
        commentMapper.deleteById(id);
    }

    /**
     * Deletes multiple comments by their IDs in batch.
     *
     * @param ids A list of IDs of comments to be deleted.
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            commentMapper.deleteById(id);
        }
    }

    /**
     * Updates a comment by its ID.
     *
     * @param comment The updated comment entity.
     */
    public void updateById(Comment comment) {
        commentMapper.updateById(comment);
    }

    /**
     * Retrieves a comment by its ID.
     *
     * @param id The ID of the comment to retrieve.
     * @return The comment entity matching the given ID.
     */
    public Comment selectById(Integer id) {
        return commentMapper.selectById(id);
    }

    /**
     * Retrieves all comments matching the given criteria.
     *
     * @param comment Filter criteria for querying comments.
     * @return A list of comments matching the criteria.
     */
    public List<Comment> selectAll(Comment comment) {
        return commentMapper.selectAll(comment);
    }

    /**
     * Retrieves comments with pagination based on filter criteria.
     * Automatically filters comments based on the user's role (e.g., USER or BUSINESS).
     *
     * @param comment  Filter criteria for querying comments.
     * @param pageNum  The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return A PageInfo object containing the paginated result.
     */
    public PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize) {
        // Get the currently logged-in user
        Account currentUser = TokenUtils.getCurrentUser();

        // Apply user-specific filters based on the role
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            comment.setUserId(currentUser.getId());
        }
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            comment.setBusinessId(currentUser.getId());
        }

        // Start pagination using PageHelper
        PageHelper.startPage(pageNum, pageSize);

        // Query the list of comments based on filter criteria
        List<Comment> list = commentMapper.selectAll(comment);

        // Return the paginated result as a PageInfo object
        return PageInfo.of(list);
    }

    /**
     * Retrieves comments associated with a specific goods ID.
     *
     * @param id The ID of the goods.
     * @return A list of comments associated with the specified goods ID.
     */
    public List<Comment> selectByGoodsId(Integer id) {
        return commentMapper.selectByGoodsId(id);
    }
}
