package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.exception.CustomException;
import com.example.mapper.UserMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service for handling user-related business logic.
 * Provides functionalities for user management, authentication, and registration.
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * Adds a new user.
     * Ensures the username is unique, sets default values for password and name if not provided.
     *
     * @param user The user entity to be added.
     * @throws CustomException If the username already exists.
     */
    public void add(User user) {
        // Check if the username already exists in the database
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (ObjectUtil.isNotNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }

        // Set default password if not provided
        if (ObjectUtil.isEmpty(user.getPassword())) {
            user.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }

        // Set the default name to the username if not provided
        if (ObjectUtil.isEmpty(user.getName())) {
            user.setName(user.getUsername());
        }

        // Assign the default role as USER
        user.setRole(RoleEnum.USER.name());
        userMapper.insert(user);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to be deleted.
     */
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * Deletes multiple users by their IDs.
     *
     * @param ids List of user IDs to be deleted.
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            userMapper.deleteById(id);
        }
    }

    /**
     * Updates a user by ID.
     *
     * @param user The updated user entity.
     */
    public void updateById(User user) {
        userMapper.updateById(user);
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user entity.
     */
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * Retrieves all users matching the provided filter criteria.
     *
     * @param user Filter criteria for the query.
     * @return List of users matching the criteria.
     */
    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    /**
     * Retrieves users with pagination.
     *
     * @param user     Filter criteria for the query.
     * @param pageNum  The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return A paginated list of users.
     */
    public PageInfo<User> selectPage(User user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll(user);
        return PageInfo.of(list);
    }

    /**
     * Handles user login.
     * Validates the username and password, and generates a token upon successful login.
     *
     * @param account The account object containing login credentials.
     * @return The authenticated account object with a generated token.
     * @throws CustomException If the username does not exist or the password is incorrect.
     */
    public Account login(Account account) {
        // Retrieve the user by username
        Account dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // Validate the provided password
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        // Generate a token for the authenticated user
        String tokenData = dbUser.getId() + "-" + RoleEnum.USER.name();
        String token = TokenUtils.createToken(tokenData, dbUser.getPassword());
        dbUser.setToken(token);

        return dbUser;
    }

    /**
     * Handles user registration.
     * Copies properties from an account object to a user entity and adds the user to the database.
     *
     * @param account The account object containing registration details.
     */
    public void register(Account account) {
        User user = new User();
        BeanUtils.copyProperties(account, user); // Copy properties from account to user
        add(user); // Add the user to the database
    }

    /**
     * Updates a user's password.
     * Validates the current password before allowing the update.
     *
     * @param account The account object containing the current and new passwords.
     * @throws CustomException If the username does not exist or the current password is incorrect.
     */
    public void updatePassword(Account account) {
        // Retrieve the user by username
        User dbUser = userMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbUser)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // Validate the current password
        if (!account.getPassword().equals(dbUser.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }

        // Update the password
        dbUser.setPassword(account.getNewPassword());
        userMapper.updateById(dbUser);
    }
}
