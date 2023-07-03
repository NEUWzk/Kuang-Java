package com.kuang.controller;

import com.kuang.common.Result;
import com.kuang.entity.Address;
import com.kuang.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("/api/address/{id}")
    public Result getAddressbyUserid(@PathVariable int id)
    {
        return Result.success(addressService.getAddressbyUserid(id));
    }

    @PostMapping("/api/address")
    public Result addAddress(@RequestBody Address address)
    {
        int res = addressService.addAddress(address);  // 0 or 1
        return Result.success(res);
    }

    @DeleteMapping("/api/address/{id}")
    public Result deleteAddress(@PathVariable int id)
    {
        int res = addressService.deleteAddress(id);
        return Result.success(res);
    }

}
