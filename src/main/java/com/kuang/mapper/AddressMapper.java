package com.kuang.mapper;

import com.kuang.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AddressMapper {

    List<Address> getAddressbyUserid(int id);

    int addAddress(Address address);

    int deleteAddress(int id);

}
