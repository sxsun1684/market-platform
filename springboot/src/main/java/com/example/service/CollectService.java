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
 * Service layer for handling collection-related business logic.
 * Provides functionalities for adding, deleting, updating, and querying collections.
 */
@Service
public class CollectService {

    @Resource
    private CollectMapper collectMapper;

    /**
     * Adds a new collection.
     * Prevents duplicate collections by checking if the item is already collected by the user.
     *
     * @param collect The Collect entity to be added.
     * @throws CustomException If the user has already collected the item.
     */
    public void add(Collect collect) {
        // Check if the user has already collected the item
        Collect dbCollect = collectMapper.selectByUserIdAndGoodsId(collect.getUserId(), collect.getGoodsId());
        if (ObjectUtil.isNotEmpty(dbCollect)) {
            // Throw an exception if the item is already collected
            throw new CustomException(ResultCodeEnum.COLLECT_ALREADY_ERROR);
        }
        // Add the new collection
        collectMapper.insert(collect);
    }

    /**
     * Deletes a collection by its ID.
     *
     * @param id The ID of the collection to be deleted.
     */
    public void deleteById(Integer id) {
        collectMapper.deleteById(id);
    }

    /**
     * Deletes multiple collections by their IDs in batch.
     *
     * @param ids A list of IDs of collections to be deleted.
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            collectMapper.deleteById(id);
        }
    }

    /**
     * Updates a collection by its ID.
     *
     * @param collect The updated Collect entity.
     */
    public void updateById(Collect collect) {
        collectMapper.updateById(collect);
    }

    /**
     * Retrieves a collection by its ID.
     *
     * @param id The ID of the collection to retrieve.
     * @return The Collect entity matching the given ID.
     */
    public Collect selectById(Integer id) {
        return collectMapper.selectById(id);
    }

    /**
     * Retrieves all collections matching the given criteria.
     *
     * @param collect Filter criteria for querying collections.
     * @return A list of Collect entities matching the criteria.
     */
    public List<Collect> selectAll(Collect collect) {
        return collectMapper.selectAll(collect);
    }

    /**
     * Retrieves collections with pagination based on filter criteria.
     * Automatically filters collections based on the user's role (e.g., USER).
     *
     * @param collect  Filter criteria for querying collections.
     * @param pageNum  The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return A PageInfo object containing the paginated result.
     */
    public PageInfo<Collect> selectPage(Collect collect, Integer pageNum, Integer pageSize) {
        // Get the currently logged-in user
        Account currentUser = TokenUtils.getCurrentUser();

        // Apply user-specific filters if the role is USER
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            collect.setUserId(currentUser.getId());
        }

        // Start pagination using PageHelper
        PageHelper.startPage(pageNum, pageSize);

        // Query the list of collections based on filter criteria
        List<Collect> list = collectMapper.selectAll(collect);

        // Return the paginated result as a PageInfo object
        return PageInfo.of(list);
    }
}
