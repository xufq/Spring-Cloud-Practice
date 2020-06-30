package com.xufq.userserver.service;

import com.xufq.practicecore.constants.Constants;
import com.xufq.userserver.bo.RoleBo;
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

    public RoleVo getRole(RoleBo roleBo) {
        RoleEntity param = getRoleEntity(roleBo);
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

    public void saveRole(RoleBo roleBo) {
        roleBo.setDeleted(Constants.UNDELETED);
        RoleEntity param = getRoleEntity(roleBo);
        int saveCount = dao.saveRole(param);
        if (saveCount < 1) {
            throw new SaveOrUpdateException(ErrorCode.USER_SAVE_ERROR);
        }
    }

    public void updateRole(RoleBo roleBo){
        RoleEntity param = getRoleEntity(roleBo);
        int updateCount = dao.updateRole(param);
        if (updateCount < 1) {
            throw new SaveOrUpdateException(ErrorCode.USER_UPDATE_ERROR);
        }
    }

    private RoleEntity getRoleEntity(RoleBo roleBo) {
        return RoleEntity.builder()
                .roleCode(roleBo.getRoleCode())
                .roleName(roleBo.getRoleName())
                .deleted(roleBo.getDeleted())
                .build();
    }
}
