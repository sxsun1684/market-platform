package com.example.service;

import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Address;
import com.example.mapper.AddressMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service layer for handling address-related business logic.
 * Provides functionalities for managing addresses, including adding, deleting, updating, and querying.
 */
@Service
public class AddressService {

    @Resource
    private AddressMapper addressMapper;

    /**
     * Adds a new address to the database.
     *
     * @param address The address entity to be added.
     */
    public void add(Address address) {
        addressMapper.insert(address);
    }

    /**
     * Deletes an address by its ID.
     *
     * @param id The ID of the address to be deleted.
     */
    public void deleteById(Integer id) {
        addressMapper.deleteById(id);
    }

    /**
     * Deletes multiple addresses by their IDs in batch.
     *
     * @param ids A list of IDs of addresses to be deleted.
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            addressMapper.deleteById(id);
        }
    }

    /**
     * Updates an existing address by its ID.
     *
     * @param address The updated address entity.
     */
    public void updateById(Address address) {
        addressMapper.updateById(address);
    }

    /**
     * Retrieves an address by its ID.
     *
     * @param id The ID of the address to retrieve.
     * @return The address entity matching the given ID.
     */
    public Address selectById(Integer id) {
        return addressMapper.selectById(id);
    }

    /**
     * Retrieves all addresses matching the provided filter criteria.
     * Filters addresses by the current user's ID if the user is logged in and has a USER role.
     *
     * @param address Filter criteria for querying addresses.
     * @return A list of addresses matching the criteria.
     */
    public List<Address> selectAll(Address address) {
        // Get the currently logged-in user
        Account currentUser = TokenUtils.getCurrentUser();

        // Apply user-specific filter if the role is USER
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            address.setUserId(currentUser.getId());
        }

        return addressMapper.selectAll(address);
    }

    /**
     * Retrieves addresses with pagination based on filter criteria.
     * Filters addresses by the current user's ID if the user is logged in and has a USER role.
     *
     * @param address  Filter criteria for querying addresses.
     * @param pageNum  The page number to retrieve.
     * @param pageSize The number of records per page.
     * @return A PageInfo object containing the paginated result.
     */
    public PageInfo<Address> selectPage(Address address, Integer pageNum, Integer pageSize) {
        // Get the currently logged-in user
        Account currentUser = TokenUtils.getCurrentUser();

        // Apply user-specific filter if the role is USER
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            address.setUserId(currentUser.getId());
        }

        // Start pagination using PageHelper
        PageHelper.startPage(pageNum, pageSize);

        // Query the list of addresses based on filter criteria
        List<Address> list = addressMapper.selectAll(address);

        // Return the paginated result as a PageInfo object
        return PageInfo.of(list);
    }
}
