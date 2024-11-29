package com.example.mapper;

import com.example.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Interface for operations related to Address data
 */
public interface AddressMapper {

    /**
     * Add a new address
     */
    int insert(Address address);

    /**
     * Delete an address by ID
     */
    int deleteById(Integer id);

    /**
     * Update an address by ID
     */
    int updateById(Address address);

    /**
     * Query an address by ID
     */
    Address selectById(Integer id);

    /**
     * Query all addresses
     */
    List<Address> selectAll(Address address);
}
