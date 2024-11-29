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
 * Address business processing
 **/
@Service
public class AddressService {

    @Resource
    private AddressMapper addressMapper;

    /**
     * Add a new address
     */
    public void add(Address address) {
        addressMapper.insert(address);
    }

    /**
     * Delete an address by ID
     */
    public void deleteById(Integer id) {
        addressMapper.deleteById(id);
    }

    /**
     * Batch delete addresses
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            addressMapper.deleteById(id);
        }
    }

    /**
     * Update an address by ID
     */
    public void updateById(Address address) {
        addressMapper.updateById(address);
    }

    /**
     * Query an address by ID
     */
    public Address selectById(Integer id) {
        return addressMapper.selectById(id);
    }

    /**
     * Query all addresses
     */
    public List<Address> selectAll(Address address) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            address.setUserId(currentUser.getId());
        }
        return addressMapper.selectAll(address);
    }

    /**
     * Paginated query for addresses
     */
    public PageInfo<Address> selectPage(Address address, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            address.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Address> list = addressMapper.selectAll(address);
        return PageInfo.of(list);
    }
}
