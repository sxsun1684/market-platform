package com.example.mapper;

import com.example.entity.Admin;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for performing operations related to Admin data.
 */
public interface AdminMapper {

    /**
     * Inserts a new Admin into the database.
     *
     * @param admin the Admin entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(Admin admin);

    /**
     * Deletes an Admin from the database by its ID.
     *
     * @param id the ID of the Admin to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing Admin in the database by its ID.
     *
     * @param admin the Admin entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(Admin admin);

    /**
     * Retrieves an Admin from the database by its ID.
     *
     * @param id the ID of the Admin to be retrieved
     * @return the Admin entity with the specified ID, or null if not found
     */
    Admin selectById(Integer id);

    /**
     * Retrieves a list of all Admins that match the criteria specified in the given Admin entity.
     *
     * @param admin an Admin entity with fields used as filter criteria
     * @return a list of Admin entities that match the specified criteria
     */
    List<Admin> selectAll(Admin admin);

    /**
     * Retrieves an Admin from the database by their username.
     *
     * @param username the username of the Admin to be retrieved
     * @return the Admin entity with the specified username, or null if not found
     */
    @Select("select * from admin where username = #{username}")
    Admin selectByUsername(String username);
}
