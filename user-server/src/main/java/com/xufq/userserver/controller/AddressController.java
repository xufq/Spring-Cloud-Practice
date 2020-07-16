package com.xufq.userserver.controller;

import com.xufq.userserver.bo.AddressDeleteBo;
import com.xufq.userserver.bo.AddressSaveBo;
import com.xufq.userserver.bo.AddressUpdateBo;
import com.xufq.userserver.service.AddressService;
import com.xufq.userserver.vo.AddressVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String save(@Valid @RequestBody AddressSaveBo address) {
        return addressService.save(address);
    }

    @GetMapping("/uuid/{addressUuid}")
    public AddressVo selectByAddressUuid(@PathVariable String addressUuid) {
        return addressService.selectByAddressUuid(addressUuid);
    }

    @GetMapping("/user/{userUuid}")
    public List<AddressVo> selectByUserUuid(@PathVariable String userUuid) {
        return addressService.selectByUserUuid(userUuid);
    }

    @PutMapping
    public void update(@Valid @RequestBody AddressUpdateBo address) {
        addressService.update(address);
    }

    @PutMapping("deletion")
    public void delete(@Valid @RequestBody AddressDeleteBo deleteBo) {
        addressService.delete(deleteBo);
    }
}
