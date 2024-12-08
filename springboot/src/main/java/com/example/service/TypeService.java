package com.example.service;

import com.example.entity.Type;
import com.example.mapper.TypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service layer for handling business logic related to the Type entity.
 * Provides operations for adding, deleting, updating, and querying type information.
 */
@Service
public class TypeService {

    @Resource
    private TypeMapper typeMapper;

    /**
     * Adds a new type.
     *
     * @param type The Type entity to be added.
     */
    public void add(Type type) {
        typeMapper.insert(type);
    }

    /**
     * Deletes a type by its ID.
     *
     * @param id The ID of the Type to be deleted.
     */
    public void deleteById(Integer id) {
        typeMapper.deleteById(id);
    }

    /**
     * Deletes multiple types by their IDs in batch.
     *
     * @param ids A list of IDs of the Types to be deleted.
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            typeMapper.deleteById(id);
        }
    }

    /**
     * Updates type information by its ID.
     *
     * @param type The updated Type entity.
     */
    public void updateById(Type type) {
        typeMapper.updateById(type);
    }

    /**
     * Finds a type by its ID.
     *
     * @param id The ID of the Type to be retrieved.
     * @return The Type entity matching the given ID.
     */
    public Type selectById(Integer id) {
        return typeMapper.selectById(id);
    }

    /**
     * Finds all types that match the given criteria.
     *
     * @param type The criteria for querying types.
     * @return A list of Type entities matching the criteria.
     */
    public List<Type> selectAll(Type type) {
        return typeMapper.selectAll(type);
    }

    /**
     * Retrieves types with pagination based on filter criteria.
     *
     * @param type     The criteria for querying types.
     * @param pageNum  The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return A PageInfo object containing the paginated result.
     */
    public PageInfo<Type> selectPage(Type type, Integer pageNum, Integer pageSize) {
        // Start pagination using PageHelper
        PageHelper.startPage(pageNum, pageSize);
        // Query the list of types based on filter criteria
        List<Type> list = typeMapper.selectAll(type);
        // Return the paginated result as a PageInfo object
        return PageInfo.of(list);
    }

}
