package com.example.mapper;

import com.example.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for handling operations related to User data.
 */
public interface UserMapper {

    /**
     * Inserts a new User entity into the database.
     *
     * @param user the User entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(User user);

    /**
     * Deletes a User entity from the database by its ID.
     *
     * @param id the ID of the User entity to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing User entity in the database by its ID.
     *
     * @param user the User entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(User user);

    /**
     * Retrieves a User entity from the database by its ID.
     *
     * @param id the ID of the User entity to be retrieved
     * @return the User entity with the specified ID, or null if not found
     */
    User selectById(Integer id);

    /**
     * Retrieves a list of all User entities that match the criteria specified in the given User entity.
     *
     * @param user a User entity with fields used as filter criteria
     * @return a list of User entities that match the specified criteria
     */
    List<User> selectAll(User user);

    /**
     * Retrieves a User entity from the database by their username.
     *
     * @param username the username of the User entity to be retrieved
     * @return the User entity with the specified username, or null if not found
     */
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);
}
