package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Collect;
import com.example.exception.CustomException;
import com.example.mapper.CollectMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Collection business processing
 **/
@Service
public class CollectService {

    @Resource
    private CollectMapper collectMapper;

    /**
     * Add new collection
     */
    public void add(Collect collect) {
        // Check if the user has already collected this item. If yes, prompt the user that duplicate collections are not allowed.
        Collect dbCollect = collectMapper.selectByUserIdAndGoodsId(collect.getUserId(), collect.getGoodsId());
        if (ObjectUtil.isNotEmpty(dbCollect)) {
            throw new CustomException(ResultCodeEnum.COLLECT_ALREADY_ERROR);
        }
        collectMapper.insert(collect);
    }

    /**
     * Delete by ID
     */
    public void deleteById(Integer id) {
        collectMapper.deleteById(id);
    }

    /**
     * Batch delete
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            collectMapper.deleteById(id);
        }
    }

    /**
     * Update by ID
     */
    public void updateById(Collect collect) {
        collectMapper.updateById(collect);
    }

    /**
     * Query by ID
     */
    public Collect selectById(Integer id) {
        return collectMapper.selectById(id);
    }

    /**
     * Query all collections
     */
    public List<Collect> selectAll(Collect collect) {
        return collectMapper.selectAll(collect);
    }

    /**
     * Paginated query
     */
    public PageInfo<Collect> selectPage(Collect collect, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            collect.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Collect> list = collectMapper.selectAll(collect);
        return PageInfo.of(list);
    }
}
