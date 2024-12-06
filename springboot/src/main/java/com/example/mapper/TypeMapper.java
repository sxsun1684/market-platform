package com.example.mapper;

import com.example.entity.Type;

import java.util.List;

/**
 * Mapper interface for handling operations related to the Type entity.
 */
public interface TypeMapper {

    /**
     * Inserts a new Type entity into the database.
     *
     * @param type the Type entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(Type type);

    /**
     * Deletes a Type entity from the database by its ID.
     *
     * @param id the ID of the Type entity to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing Type entity in the database by its ID.
     *
     * @param type the Type entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(Type type);

    /**
     * Retrieves a Type entity from the database by its ID.
     *
     * @param id the ID of the Type entity to be retrieved
     * @return the Type entity with the specified ID, or null if not found
     */
    Type selectById(Integer id);

    /**
     * Retrieves a list of all Type entities that match the criteria specified in the given Type entity.
     *
     * @param type a Type entity with fields used as filter criteria
     * @return a list of Type entities that match the specified criteria
     */
    List<Type> selectAll(Type type);
}
