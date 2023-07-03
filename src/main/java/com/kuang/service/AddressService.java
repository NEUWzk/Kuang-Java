package com.kuang.service;

import com.kuang.entity.Address;
import com.kuang.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;

    public List<Address> getAddressbyUserid(int id) {
        return addressMapper.getAddressbyUserid(id);
    }

    public int addAddress(Address address) {
        return addressMapper.addAddress(address);
    }

    public int deleteAddress(int id) {
        return addressMapper.deleteAddress(id);
    }
}
