package com.samuel.stockflow_auth_service.sevice;

import com.samuel.stockflow_auth_service.domain.User;
import com.samuel.stockflow_auth_service.dto.UserPatchDto;
import com.samuel.stockflow_auth_service.dto.UserRequestDto;
import com.samuel.stockflow_auth_service.dto.UserResponseDto;
import com.samuel.stockflow_auth_service.exception.ResourceAlreadyExistsException;
import com.samuel.stockflow_auth_service.exception.ResourceNotFoundException;
import com.samuel.stockflow_auth_service.mapper.UserMapper;
import com.samuel.stockflow_auth_service.repository.UserRepository;
import com.samuel.stockflow_auth_service.specifications.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    private User user(Integer id){
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "The user id haven't been found, you can try again"));
    }

    @Override
    public Page<UserResponseDto> getUsers(
            String name, String lastName, Integer id, Pageable pageable
    ){
        Specification<User> specification = UserSpecification.specification(
                name, lastName, id
        );

        return repository.findAll(specification, pageable)
                .map(UserMapper::toResponseDto);
    }

    @Override
    public UserResponseDto getUser(Integer id){
        User user = user(id);
        return UserMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto postUser(UserRequestDto dto){
        if(repository.existsUserByUserName(dto.getUserName())){
            throw new ResourceAlreadyExistsException(
                    "The user name already exist, please write other");
        }
        if(repository.existsUserByEmployeeId(dto.getEmployeeId())){
            throw new ResourceAlreadyExistsException(
                    "The employee id already exist, you can't have many users");
        }
        User user = UserMapper.toEntity(dto);
        user.setPassword(
                passwordEncoder.encode(dto.getPassword())
        );
        repository.save(user);
        return UserMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto putUser(Integer id, UserRequestDto dto){
        User user = user(id);
        UserMapper.toPostDto(user, dto);
        repository.save(user);
        return UserMapper.toResponseDto(user);
    }

    @Override
    public UserResponseDto patchUser(Integer id, UserPatchDto dto){
        User user = user(id);
        UserMapper.toPatchUser(user, dto);
        repository.save(user);
        return UserMapper.toResponseDto(user);
    }

    @Override
    public void deleteUser(Integer id){
        User user = user(id);
        repository.delete(user);
    }

}
