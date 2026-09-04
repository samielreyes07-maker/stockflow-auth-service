package com.samuel.stockflow_auth_service.sevice;

import com.samuel.stockflow_auth_service.dto.UserPatchDto;
import com.samuel.stockflow_auth_service.dto.UserRequestDto;
import com.samuel.stockflow_auth_service.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    public Page<UserResponseDto> getUsers(
            String name, String lastName, Integer id, Pageable pageable
    );
    public UserResponseDto postUser(UserRequestDto dto);
    public UserResponseDto putUser(Integer id, UserRequestDto dto);
    public UserResponseDto getUser(Integer id);
    public UserResponseDto patchUser(Integer id, UserPatchDto dto);
    public void deleteUser(Integer id);
}
