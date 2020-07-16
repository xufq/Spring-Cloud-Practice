package com.xufq.userserver.service;

import com.xufq.practicecore.constants.Constants;
import com.xufq.practicecore.utils.UUIDUtil;
import com.xufq.practicecore.utils.UserInfoUtil;
import com.xufq.userserver.bo.AddressDeleteBo;
import com.xufq.userserver.bo.AddressSaveBo;
import com.xufq.userserver.bo.AddressUpdateBo;
import com.xufq.userserver.dao.AddressDao;
import com.xufq.userserver.dao.UserDao;
import com.xufq.userserver.entity.AddressEntity;
import com.xufq.userserver.entity.UserEntity;
import com.xufq.userserver.exception.ErrorCode;
import com.xufq.userserver.exception.NotFoundResourceException;
import com.xufq.userserver.exception.SaveOrUpdateException;
import com.xufq.userserver.vo.AddressVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class AddressService {
    private final AddressDao addressDao;

    private final UserDao userDao;

    public AddressService(AddressDao addressDao, UserDao userDao) {
        this.addressDao = addressDao;
        this.userDao = userDao;
    }

    public String save(AddressSaveBo record) {
        UserEntity user = userDao.getUser(UserEntity.builder().uuid(record.getUserUuid()).build());
        if (Objects.isNull(user)) {
            throw new NotFoundResourceException(ErrorCode.USER_NOT_FOUND);
        }
        AddressEntity entity = AddressEntity.builder()
                .address(record.getAddress())
                .addressUuid(UUIDUtil.getUUID())
                .createUser(UserInfoUtil.getUserInfo().getUuid())
                .createDate(new Date())
                .modifyUser(UserInfoUtil.getUserInfo().getUuid())
                .deleted(Constants.UNDELETED)
                .phone(record.getPhone())
                .userUuid(record.getUserUuid())
                .version(1)
                .build();
        int saveCount = addressDao.insert(entity);
        if (saveCount < 1) {
            throw new SaveOrUpdateException(ErrorCode.ADDRESS_SAVE_ERROR);
        }
        return entity.getAddressUuid();
    }

    public AddressVo selectByAddressUuid(String addressUuid) {
        AddressEntity entity = addressDao.selectByAddressUuid(addressUuid);
        if (Objects.isNull(entity)) {
            throw new NotFoundResourceException(ErrorCode.ADDRESS_NOT_FOUND);
        }
        AddressVo result = AddressVo.builder().build();
        BeanUtils.copyProperties(entity, result);
        return result;
    }

    public List<AddressVo> selectByUserUuid(String userUuid) {
        UserEntity user = userDao.getUser(UserEntity.builder().uuid(userUuid).build());
        if (Objects.isNull(user)) {
            throw new NotFoundResourceException(ErrorCode.USER_NOT_FOUND);
        }
        List<AddressEntity> list = addressDao.selectByUserUuid(userUuid);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        return list.stream().map(address -> {
            return AddressVo.builder()
                    .address(address.getAddress())
                    .addressUuid(address.getAddressUuid())
                    .createDate(address.getCreateDate())
                    .createUser(address.getCreateUser())
                    .deleted(address.getDeleted())
                    .modifyDate(address.getModifyDate())
                    .modifyUser(address.getModifyUser())
                    .phone(address.getPhone())
                    .userUuid(address.getUserUuid())
                    .version(address.getVersion())
                    .build();
        }).collect(Collectors.toList());
    }

    public void update(AddressUpdateBo record) {
        AddressEntity address = addressDao.selectByAddressUuid(record.getAddressUuid());
        if (Objects.isNull(address)) {
            throw new NotFoundResourceException(ErrorCode.ADDRESS_NOT_FOUND);
        }
        AddressEntity entity = AddressEntity.builder()
                .userUuid(record.getUserUuid())
                .phone(record.getPhone())
                .modifyUser(UserInfoUtil.getUserInfo().getUuid())
                .version(record.getVersion())
                .address(record.getAddress())
                .build();
        int updateCount = addressDao.update(entity);
        if (updateCount < 1) {
            throw new SaveOrUpdateException(ErrorCode.ADDRESS_UPDATE_ERROR);
        }
    }

    public void delete(AddressDeleteBo deleteBo) {
        AddressEntity address = addressDao.selectByAddressUuid(deleteBo.getAddressUuid());
        if (Objects.isNull(address)) {
            throw new NotFoundResourceException(ErrorCode.ADDRESS_NOT_FOUND);
        }
        AddressEntity entity = AddressEntity.builder()
                .deleted(Constants.DELETED)
                .modifyUser(UserInfoUtil.getUserInfo().getUuid())
                .version(deleteBo.getVersion())
                .build();
        int deleteCount = addressDao.update(entity);
        if (deleteCount < 1) {
            throw new SaveOrUpdateException(ErrorCode.ADDRESS_UPDATE_ERROR);
        }
    }
}
