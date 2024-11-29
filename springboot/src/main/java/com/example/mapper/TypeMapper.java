package com.example.mapper;

import com.example.entity.Type;

import java.util.List;

/**
 * Interface for handling operations related to the Type entity
 */
public interface TypeMapper {

    /**
     * Add a new record
     */
    int insert(Type type);

    /**
     * Delete a record by its ID
     */
    int deleteById(Integer id);

    /**
     * Update a record by its ID
     */
    int updateById(Type type);

    /**
     * Query a record by its ID
     */
    Type selectById(Integer id);

    /**
     * Query all records
     */
    List<Type> selectAll(Type type);
}
