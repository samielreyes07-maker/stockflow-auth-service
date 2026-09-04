package com.samuel.stockflow_auth_service.mapper;

import com.samuel.stockflow_auth_service.domain.User;
import com.samuel.stockflow_auth_service.dto.UserPatchDto;
import com.samuel.stockflow_auth_service.dto.UserRequestDto;
import com.samuel.stockflow_auth_service.dto.UserResponseDto;

public class UserMapper {
    public static UserResponseDto toResponseDto(User user){
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setRole(user.getRole());
        dto.setEmployeeId(user.getEmployeeId());
        return dto;
    }

    public static User toEntity(UserRequestDto dto){
        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmployeeId(dto.getEmployeeId());
        return user;
    }

    public static void toPostDto(User user, UserRequestDto dto){
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmployeeId(dto.getEmployeeId());
    }

    public static void toPatchUser(User user, UserPatchDto dto){
        dto.getUserName()
                .ifPresent(user::setUserName);
        dto.getEmployeeId()
                .ifPresent(user::setEmployeeId);
        dto.getPassword()
                .ifPresent(user::setPassword);
        dto.getRole()
                .ifPresent(user::setRole);
    }
}
