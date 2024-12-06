package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Comment;
import com.example.exception.CustomException;
import com.example.mapper.CommentMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Comment business processing
 **/
@Service
public class CommentService {

    @Resource
    private CommentMapper commentMapper;

    /**
     * Add new comment
     */
    public void add(Comment comment) {
        comment.setTime(DateUtil.now());
        commentMapper.insert(comment);
    }

    /**
     * Delete by ID
     */
    public void deleteById(Integer id) {
        commentMapper.deleteById(id);
    }

    /**
     * Batch delete
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            commentMapper.deleteById(id);
        }
    }

    /**
     * Update by ID
     */
    public void updateById(Comment comment) {
        commentMapper.updateById(comment);
    }

    /**
     * Select by ID
     */
    public Comment selectById(Integer id) {
        return commentMapper.selectById(id);
    }

    /**
     * Select all comments
     */
    public List<Comment> selectAll(Comment comment) {
        return commentMapper.selectAll(comment);
    }

    /**
     * Paginated query
     */
    public PageInfo<Comment> selectPage(Comment comment, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            comment.setUserId(currentUser.getId());
        }
        if (RoleEnum.BUSINESS.name().equals(currentUser.getRole())) {
            comment.setBusinessId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Comment> list = commentMapper.selectAll(comment);
        return PageInfo.of(list);
    }

    /**
     * Select comments by goods ID
     */
    public List<Comment> selectByGoodsId(Integer id) {
        return commentMapper.selectByGoodsId(id);
    }
}
