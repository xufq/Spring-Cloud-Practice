package com.xufq.userserver.controller;

import com.xufq.practicecore.security.OnlyAdmin;
import com.xufq.userserver.bo.RoleBo;
import com.xufq.userserver.entity.RoleEntity;
import com.xufq.userserver.exception.ErrorCode;
import com.xufq.userserver.exception.NotFoundResourceException;
import com.xufq.userserver.exception.SaveOrUpdateException;
import com.xufq.userserver.service.RoleService;
import com.xufq.userserver.vo.RoleVo;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("role")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping("name/{name}")
    public RoleVo getRoleByName(@PathVariable("name") String roleName) {
        RoleBo roleBo = RoleBo.builder()
                .roleName(roleName)
                .build();
        return service.getRole(roleBo);
    }


    @GetMapping("code/{code}")
    public RoleVo getRoleByCode(@PathVariable("code") String roleCode) {
        RoleBo roleBo = RoleBo.builder()
                .roleCode(roleCode)
                .build();
        return service.getRole(roleBo);
    }

    @PostMapping("private")
    @OnlyAdmin
    public void saveRole(@RequestBody RoleBo roleBo) {
        service.saveRole(roleBo);
    }

    @PutMapping("private")
    @OnlyAdmin
    public void updateRole(RoleBo roleBo){
        service.updateRole(roleBo);
    }
}
