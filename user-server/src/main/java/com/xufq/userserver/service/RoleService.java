package com.xufq.userserver.service;

import com.xufq.practicecore.constants.Constants;
import com.xufq.practicecore.utils.UUIDUtil;
import com.xufq.userserver.bo.SaveRoleBo;
import com.xufq.userserver.bo.UpdateRoleBo;
import com.xufq.userserver.dao.RoleDao;
import com.xufq.userserver.entity.RoleEntity;
import com.xufq.userserver.exception.ErrorCode;
import com.xufq.userserver.exception.NotFoundResourceException;
import com.xufq.userserver.exception.SaveOrUpdateException;
import com.xufq.userserver.vo.RoleVo;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleService {

    private final RoleDao dao;

    public RoleService(RoleDao dao) {
        this.dao = dao;
    }

    public RoleVo getRoleByCode(String roleCode) {
        RoleEntity param = RoleEntity.builder().roleCode(roleCode).build();
        return getRoleVo(param);
    }

    public RoleVo getRoleByName(String roleName) {
        RoleEntity param = RoleEntity.builder().roleName(roleName).build();
        return getRoleVo(param);
    }

    private RoleVo getRoleVo(RoleEntity param) {
        RoleEntity roleInfo = dao.getRole(param);
        if (Objects.nonNull(roleInfo)) {
            return RoleVo.builder()
                    .roleCode(roleInfo.getRoleCode())
                    .roleName(roleInfo.getRoleName())
                    .deleted(roleInfo.getDeleted())
                    .build();
        } else {
            throw new NotFoundResourceException(ErrorCode.ROLE_NOT_FOUND);
        }
    }

    public String saveRole(SaveRoleBo roleBo) {
        RoleEntity param = RoleEntity.builder()
                .uuid(UUIDUtil.getUUID())
                .roleCode(roleBo.getRoleCode())
                .roleName(roleBo.getRoleName())
                .deleted(Constants.UNDELETED)
                .version(1)
                .build();
        int saveCount = dao.saveRole(param);
        if (saveCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_SAVE_ERROR);
        }
        return param.getUuid();
    }

    public void updateRole(UpdateRoleBo roleBo) {
        RoleEntity param = RoleEntity.builder()
                .uuid(UUIDUtil.getUUID())
                .roleCode(roleBo.getRoleCode())
                .roleName(roleBo.getRoleName())
                .version(roleBo.getVersion())
                .build();
        int updateCount = dao.updateRole(param);
        if (updateCount == 0) {
            throw new SaveOrUpdateException(ErrorCode.USER_UPDATE_ERROR);
        }
    }
}
