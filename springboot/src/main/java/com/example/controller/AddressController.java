package com.example.controller;

import com.example.common.Result;
import com.example.entity.Address;
import com.example.service.AddressService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * API controller for managing address-related operations.
 * Provides endpoints for adding, deleting, updating, and querying addresses.
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Resource
    private AddressService addressService;

    /**
     * Adds a new address.
     *
     * @param address The address entity to be added.
     * @return A success result.
     */
    @PostMapping("/add")
    public Result add(@RequestBody Address address) {
        addressService.add(address);
        return Result.success();
    }

    /**
     * Deletes an address by its ID.
     *
     * @param id The ID of the address to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        addressService.deleteById(id);
        return Result.success();
    }

    /**
     * Deletes multiple addresses in batch.
     *
     * @param ids The list of IDs of addresses to be deleted.
     * @return A success result.
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        addressService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * Updates an address by its ID.
     *
     * @param address The address entity containing updated information.
     * @return A success result.
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Address address) {
        addressService.updateById(address);
        return Result.success();
    }

    /**
     * Queries an address by its ID.
     *
     * @param id The ID of the address to query.
     * @return A result containing the queried address.
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Address address = addressService.selectById(id);
        return Result.success(address);
    }

    /**
     * Queries all addresses that match the provided criteria.
     *
     * @param address The criteria for querying addresses.
     * @return A result containing a list of matching addresses.
     */
    @GetMapping("/selectAll")
    public Result selectAll(Address address) {
        List<Address> list = addressService.selectAll(address);
        return Result.success(list);
    }

    /**
     * Queries addresses with pagination support.
     *
     * @param address The criteria for querying addresses.
     * @param pageNum The page number to retrieve (default is 1).
     * @param pageSize The number of records per page (default is 10).
     * @return A result containing a paginated list of addresses.
     */
    @GetMapping("/selectPage")
    public Result selectPage(Address address,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Address> page = addressService.selectPage(address, pageNum, pageSize);
        return Result.success(page);
    }
}
