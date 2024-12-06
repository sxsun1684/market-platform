package com.example.mapper;

import com.example.entity.Address;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper interface for performing database operations related to Address entities.
 */
public interface AddressMapper {

    /**
     * Inserts a new address into the database.
     *
     * @param address the Address entity to be inserted
     * @return the number of rows affected by the insert operation
     */
    int insert(Address address);

    /**
     * Deletes an address from the database by its ID.
     *
     * @param id the ID of the address to be deleted
     * @return the number of rows affected by the delete operation
     */
    int deleteById(Integer id);

    /**
     * Updates an existing address in the database by its ID.
     *
     * @param address the Address entity containing updated information
     * @return the number of rows affected by the update operation
     */
    int updateById(Address address);

    /**
     * Retrieves an address from the database by its ID.
     *
     * @param id the ID of the address to be retrieved
     * @return the Address entity with the specified ID, or null if not found
     */
    Address selectById(Integer id);

    /**
     * Retrieves a list of all addresses that match the criteria specified in the given Address entity.
     *
     * @param address an Address entity with fields used as filter criteria (e.g., city, postal code)
     * @return a list of Address entities that match the specified criteria
     */
    List<Address> selectAll(Address address);
}
