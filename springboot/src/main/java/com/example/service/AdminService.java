package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Admin;
import com.example.exception.CustomException;
import com.example.mapper.AdminMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service layer for managing administrator-related operations.
 * Provides functionalities for adding, deleting, updating, and querying administrators, as well as authentication and password management.
 */
@Service
public class AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * Adds a new administrator.
     * Ensures the username is unique and sets default values for missing fields.
     *
     * @param admin The administrator entity to be added.
     * @throws CustomException If the username already exists.
     */
    public void add(Admin admin) {
        // Check if the username already exists in the database
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if (ObjectUtil.isNotNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }

        // Set default password if not provided
        if (ObjectUtil.isEmpty(admin.getPassword())) {
            admin.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }

        // Set default name to username if not provided
        if (ObjectUtil.isEmpty(admin.getName())) {
            admin.setName(admin.getUsername());
        }

        // Assign role as ADMIN
        admin.setRole(RoleEnum.ADMIN.name());

        // Insert the administrator record into the database
        adminMapper.insert(admin);
    }

    /**
     * Deletes an administrator by its ID.
     *
     * @param id The ID of the administrator to delete.
     */
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    /**
     * Deletes multiple administrators by their IDs in batch.
     *
     * @param ids A list of IDs of administrators to delete.
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            adminMapper.deleteById(id);
        }
    }

    /**
     * Updates an administrator's information by its ID.
     *
     * @param admin The updated administrator entity.
     */
    public void updateById(Admin admin) {
        adminMapper.updateById(admin);
    }

    /**
     * Retrieves an administrator by its ID.
     *
     * @param id The ID of the administrator to retrieve.
     * @return The administrator entity matching the given ID.
     */
    public Admin selectById(Integer id) {
        return adminMapper.selectById(id);
    }

    /**
     * Retrieves all administrators matching the provided filter criteria.
     *
     * @param admin Filter criteria for querying administrators.
     * @return A list of administrators matching the criteria.
     */
    public List<Admin> selectAll(Admin admin) {
        return adminMapper.selectAll(admin);
    }

    /**
     * Retrieves administrators with pagination based on filter criteria.
     *
     * @param admin    Filter criteria for querying administrators.
     * @param pageNum  The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return A PageInfo object containing the paginated result.
     */
    public PageInfo<Admin> selectPage(Admin admin, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> list = adminMapper.selectAll(admin);
        return PageInfo.of(list);
    }

    /**
     * Handles administrator login.
     * Validates the username and password and generates a token upon successful authentication.
     *
     * @param account The account object containing login credentials.
     * @return The authenticated account object with a generated token.
     * @throws CustomException If the username does not exist or the password is incorrect.
     */
    public Account login(Account account) {
        // Retrieve the administrator account by username
        Account dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // Validate the provided password
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        // Generate a token for the authenticated administrator account
        String tokenData = dbAdmin.getId() + "-" + RoleEnum.ADMIN.name();
        String token = TokenUtils.createToken(tokenData, dbAdmin.getPassword());
        dbAdmin.setToken(token);

        return dbAdmin;
    }

    /**
     * Registers a new administrator account.
     * Copies properties from an account object to an administrator entity and adds it to the database.
     *
     * @param account The account object containing registration details.
     */
    public void register(Account account) {
        Admin admin = new Admin();
        BeanUtils.copyProperties(account, admin);
        add(admin);
    }

    /**
     * Updates the password for an administrator account.
     * Validates the current password before allowing the update.
     *
     * @param account The account object containing the current and new passwords.
     * @throws CustomException If the username does not exist or the current password is incorrect.
     */
    public void updatePassword(Account account) {
        // Retrieve the administrator account by username
        Admin dbAdmin = adminMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbAdmin)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // Validate the current password
        if (!account.getPassword().equals(dbAdmin.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }

        // Update the password
        dbAdmin.setPassword(account.getNewPassword());
        adminMapper.updateById(dbAdmin);
    }
}
