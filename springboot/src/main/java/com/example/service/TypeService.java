package com.example.service;

import com.example.entity.Type;
import com.example.mapper.TypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Business logic for the Type entity.
 * Handles operations related to type information.
 **/
@Service
public class TypeService {

    @Resource
    private TypeMapper typeMapper;

    /**
     * Add a new type.
     */
    public void add(Type type) {
        typeMapper.insert(type);
    }

    /**
     * Delete a type by ID.
     */
    public void deleteById(Integer id) {
        typeMapper.deleteById(id);
    }

    /**
     * Delete multiple types in batch.
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            typeMapper.deleteById(id);
        }
    }

    /**
     * Update type information by ID.
     */
    public void updateById(Type type) {
        typeMapper.updateById(type);
    }

    /**
     * Find a type by ID.
     */
    public Type selectById(Integer id) {
        return typeMapper.selectById(id);
    }

    /**
     * Find all types that match the given criteria.
     */
    public List<Type> selectAll(Type type) {
        return typeMapper.selectAll(type);
    }

    /**
     * Paginated query for types.
     *
     * @param type The criteria for querying types.
     * @param pageNum The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return A PageInfo object containing the result.
     */
    public PageInfo<Type> selectPage(Type type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Type> list = typeMapper.selectAll(type);
        return PageInfo.of(list);
    }

}
