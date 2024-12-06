package com.example.mapper;

import com.example.entity.Business;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for performing operations related to Business data.
 */
public interface BusinessMapper {

    /**
     * Inserts a new Business entity into the database.
     *
     * @param business the Business entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(Business business);

    /**
     * Deletes a Business entity from the database by its ID.
     *
     * @param id the ID of the Business entity to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing Business entity in the database by its ID.
     *
     * @param business the Business entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(Business business);

    /**
     * Retrieves a Business entity from the database by its ID.
     *
     * @param id the ID of the Business entity to be retrieved
     * @return the Business entity with the specified ID, or null if not found
     */
    Business selectById(Integer id);

    /**
     * Retrieves a list of all Business entities that match the criteria specified in the given Business entity.
     *
     * @param business a Business entity with fields used as filter criteria
     * @return a list of Business entities that match the specified criteria
     */
    List<Business> selectAll(Business business);

    /**
     * Retrieves a Business entity from the database by its username.
     *
     * @param username the username of the Business entity to be retrieved
     * @return the Business entity with the specified username, or null if not found
     */
    @Select("select * from business where username = #{username}")
    Business selectByUsername(String username);
}
