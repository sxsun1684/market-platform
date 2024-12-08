package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Notice;
import com.example.mapper.NoticeMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for handling business logic related to notices.
 * Provides functionalities to add, delete, update, and query notices.
 */
@Service
public class NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    /**
     * Adds a new notice.
     * Automatically sets the current date and the username of the logged-in user as the notice creator.
     *
     * @param notice The notice entity to be added.
     */
    public void add(Notice notice) {
        notice.setTime(DateUtil.today()); // Set the notice creation date to today's date
        Account currentUser = TokenUtils.getCurrentUser(); // Retrieve the current logged-in user
        notice.setUser(currentUser.getUsername()); // Set the creator of the notice
        noticeMapper.insert(notice); // Save the notice to the database
    }

    /**
     * Deletes a notice by its ID.
     *
     * @param id The ID of the notice to be deleted.
     */
    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }

    /**
     * Deletes multiple notices by their IDs.
     *
     * @param ids List of IDs of notices to be deleted.
     */
    public void deleteBatch(List<Integer> ids) {
        for(Integer id: ids) {
            noticeMapper.deleteById(id);
        }
    }

    /**
     * Updates a notice by its ID.
     *
     * @param notice The updated notice entity.
     */
    public void updateById(Notice notice) {
        noticeMapper.updateById(notice);
    }

    /**
     * Queries a notice by its ID.
     *
     * @param id The ID of the notice to query.
     * @return The notice entity matching the given ID.
     */
    public Notice selectById(Integer id) {
        return noticeMapper.selectById(id);
    }

    /**
     * Queries all notices based on filter criteria.
     *
     * @param notice Filter criteria for querying notices.
     * @return List of notices matching the criteria.
     */
    public List<Notice> selectAll(Notice notice) {
        return noticeMapper.selectAll(notice);
    }

    /**
     * Queries notices with pagination support.
     *
     * @param notice   Filter criteria for querying notices.
     * @param pageNum  The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return A paginated list of notices.
     */
    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize); // Start pagination
        List<Notice> list = noticeMapper.selectAll(notice); // Query notices based on filter criteria
        return PageInfo.of(list); // Return paginated result
    }
}
