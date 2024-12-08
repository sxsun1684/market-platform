package com.example.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.common.Constants;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.common.enums.StatusEnum;
import com.example.entity.Account;
import com.example.entity.Business;
import com.example.exception.CustomException;
import com.example.mapper.BusinessMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service layer for handling business-related operations.
 * Provides functionalities for managing business accounts, including registration, authentication, and CRUD operations.
 */
@Service
public class BusinessService {

    @Resource
    private BusinessMapper businessMapper;

    /**
     * Adds a new business account.
     * Ensures the username is unique and sets default values for missing fields.
     *
     * @param business The business entity to be added.
     * @throws CustomException If the username already exists.
     */
    public void add(Business business) {
        // Check if the username already exists in the database
        Business dbBusiness = businessMapper.selectByUsername(business.getUsername());
        if (ObjectUtil.isNotNull(dbBusiness)) {
            throw new CustomException(ResultCodeEnum.USER_EXIST_ERROR);
        }

        // Set default password if not provided
        if (ObjectUtil.isEmpty(business.getPassword())) {
            business.setPassword(Constants.USER_DEFAULT_PASSWORD);
        }

        // Set default name to username if not provided
        if (ObjectUtil.isEmpty(business.getName())) {
            business.setName(business.getUsername());
        }

        // Set default status to "Under Review" if not provided
        if (ObjectUtil.isEmpty(business.getStatus())) {
            business.setStatus(StatusEnum.CHECKING.status);
        }

        // Assign role as BUSINESS
        business.setRole(RoleEnum.BUSINESS.name());

        // Insert the business record into the database
        businessMapper.insert(business);
    }

    /**
     * Deletes a business account by its ID.
     *
     * @param id The ID of the business account to delete.
     */
    public void deleteById(Integer id) {
        businessMapper.deleteById(id);
    }

    /**
     * Deletes multiple business accounts by their IDs.
     *
     * @param ids A list of IDs of business accounts to delete.
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            businessMapper.deleteById(id);
        }
    }

    /**
     * Updates a business account by its ID.
     *
     * @param business The updated business entity.
     */
    public void updateById(Business business) {
        businessMapper.updateById(business);
    }

    /**
     * Retrieves a business account by its ID.
     *
     * @param id The ID of the business account to retrieve.
     * @return The business entity matching the given ID.
     */
    public Business selectById(Integer id) {
        return businessMapper.selectById(id);
    }

    /**
     * Retrieves all business accounts matching the provided criteria.
     *
     * @param business Filter criteria for querying businesses.
     * @return A list of business entities matching the criteria.
     */
    public List<Business> selectAll(Business business) {
        return businessMapper.selectAll(business);
    }

    /**
     * Retrieves business accounts with pagination based on filter criteria.
     *
     * @param business Filter criteria for querying businesses.
     * @param pageNum  The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return A PageInfo object containing the paginated result.
     */
    public PageInfo<Business> selectPage(Business business, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Business> list = businessMapper.selectAll(business);
        return PageInfo.of(list);
    }

    /**
     * Handles business account login.
     * Validates the username and password and generates a token upon successful authentication.
     *
     * @param account The account object containing login credentials.
     * @return The authenticated account object with a generated token.
     * @throws CustomException If the username does not exist or the password is incorrect.
     */
    public Account login(Account account) {
        // Retrieve the business account by username
        Account dbBusiness = businessMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbBusiness)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // Validate the provided password
        if (!account.getPassword().equals(dbBusiness.getPassword())) {
            throw new CustomException(ResultCodeEnum.USER_ACCOUNT_ERROR);
        }

        // Generate a token for the authenticated business account
        String tokenData = dbBusiness.getId() + "-" + RoleEnum.BUSINESS.name();
        String token = TokenUtils.createToken(tokenData, dbBusiness.getPassword());
        dbBusiness.setToken(token);

        return dbBusiness;
    }

    /**
     * Registers a new business account.
     * Copies properties from an account object to a business entity and adds it to the database.
     *
     * @param account The account object containing registration details.
     */
    public void register(Account account) {
        Business business = new Business();
        BeanUtils.copyProperties(account, business);
        add(business);
    }

    /**
     * Updates the password for a business account.
     * Validates the current password before allowing the update.
     *
     * @param account The account object containing the current and new passwords.
     * @throws CustomException If the username does not exist or the current password is incorrect.
     */
    public void updatePassword(Account account) {
        // Retrieve the business account by username
        Business dbBusiness = businessMapper.selectByUsername(account.getUsername());
        if (ObjectUtil.isNull(dbBusiness)) {
            throw new CustomException(ResultCodeEnum.USER_NOT_EXIST_ERROR);
        }

        // Validate the current password
        if (!account.getPassword().equals(dbBusiness.getPassword())) {
            throw new CustomException(ResultCodeEnum.PARAM_PASSWORD_ERROR);
        }

        // Update the password
        dbBusiness.setPassword(account.getNewPassword());
        businessMapper.updateById(dbBusiness);
    }
}
