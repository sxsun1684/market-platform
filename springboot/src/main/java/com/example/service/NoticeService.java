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
 * Business logic for handling the Notice information table
 **/
@Service
public class NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    /**
     * Add a new notice
     */
    public void add(Notice notice) {
        notice.setTime(DateUtil.today());
        Account currentUser = TokenUtils.getCurrentUser();
        notice.setUser(currentUser.getUsername());
        noticeMapper.insert(notice);
    }

    /**
     * Delete a notice by ID
     */
    public void deleteById(Integer id) {
        noticeMapper.deleteById(id);
    }

    /**
     * Batch delete notices
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            noticeMapper.deleteById(id);
        }
    }

    /**
     * Update a notice by ID
     */
    public void updateById(Notice notice) {
        noticeMapper.updateById(notice);
    }

    /**
     * Query a notice by ID
     */
    public Notice selectById(Integer id) {
        return noticeMapper.selectById(id);
    }

    /**
     * Query all notices
     */
    public List<Notice> selectAll(Notice notice) {
        return noticeMapper.selectAll(notice);
    }

    /**
     * Paginated query for notices
     */
    public PageInfo<Notice> selectPage(Notice notice, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> list = noticeMapper.selectAll(notice);
        return PageInfo.of(list);
    }
}
