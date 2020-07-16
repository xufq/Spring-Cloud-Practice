package com.xufq.userserver.controller;

import com.xufq.practicecore.security.OnlyAdmin;
import com.xufq.userserver.bo.SaveRoleBo;
import com.xufq.userserver.bo.UpdateRoleBo;
import com.xufq.userserver.service.RoleService;
import com.xufq.userserver.vo.RoleVo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("role")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping("name/{roleName}")
    public RoleVo getRoleByName(@PathVariable String roleName) {
        return service.getRoleByName(roleName);
    }


    @GetMapping("code/{roleCode}")
    public RoleVo getRoleByCode(@PathVariable String roleCode) {
        return service.getRoleByCode(roleCode);
    }

    @PostMapping(value = "private", produces = MediaType.TEXT_PLAIN_VALUE)
    @OnlyAdmin
    public String saveRole(@Valid @RequestBody SaveRoleBo roleBo) {
        return service.saveRole(roleBo);
    }

    @PutMapping("private")
    @OnlyAdmin
    public void updateRole(@Valid UpdateRoleBo roleBo) {
        service.updateRole(roleBo);
    }
}
