package com.samuel.stockflow_auth_service.service;

import com.samuel.stockflow_auth_service.domain.User;
import com.samuel.stockflow_auth_service.domain.UserRole;
import com.samuel.stockflow_auth_service.dto.UserPatchDto;
import com.samuel.stockflow_auth_service.dto.UserRequestDto;
import com.samuel.stockflow_auth_service.dto.UserResponseDto;
import com.samuel.stockflow_auth_service.exception.ResourceNotFoundException;
import com.samuel.stockflow_auth_service.repository.UserRepository;
import com.samuel.stockflow_auth_service.sevice.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl service;

    //post test
    @Test
    void postUser_shouldSaveEmployee_andReturnDt(){
        UserRequestDto dto = new UserRequestDto();
        dto.setUserName("Samuel");
        dto.setPassword("1234");
        dto.setRole(UserRole.ADMIN);
        dto.setEmployeeId(1);

        User user = new User();
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setEmployeeId(dto.getEmployeeId());
        user.setId(2);

        when(passwordEncoder.encode(dto.getPassword()))
                .thenReturn("encrypted password");

        when(repository.save(any(User.class)))
                .thenReturn(user);
        UserResponseDto result = service.postUser(dto);
        assertNotNull(result);
        verify(repository).save(any(User.class));

    }

    //get test
    @Test
    void getUser_shouldThrowException_whenUserExists(){
        Integer id = 1;
        User user = new User();
        user.setUserName("Samuel");
        user.setPassword("1234");
        user.setRole(UserRole.ADMIN);
        user.setEmployeeId(1);
        user.setId(id);

        when(repository.findById(id))
                .thenReturn(Optional.of(user));

        UserResponseDto result = service.getUser(id);
        assertNotNull(result);
        assertEquals("Samuel", user.getUserName());
        assertEquals("1234", user.getPassword());
        verify(repository).findById(id);
    }

    @Test
    void getUser_shouldThrowException_whenUserDoesNotExists(){
        Integer id = 1;

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                ()-> service.getUser(id)
        );

        verify(repository).findById(id);
    }

    @Test
    void getUsers_shouldThrowException_whenUserExists(){
        Pageable pageable = PageRequest.of(0, 10);
        Integer id = 1;
        User user = new User();
        user.setUserName("Samuel");
        user.setPassword("1234");
        user.setRole(UserRole.ADMIN);
        user.setEmployeeId(1);
        user.setId(id);

        Page<User> userPage = new PageImpl<>(List.of(user));

        when(repository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(userPage);

        Page<UserResponseDto> result = service.getUsers(
                null,
                null,
                null,
                pageable
        );

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(repository).findAll(any(Specification.class), eq(pageable));
    }

    //put test
    @Test
    void putUser_shouldThrowException_whenUserExists(){
        Integer id = 1;
        User user = new User();
        user.setUserName("Samuel");
        user.setPassword("1234");
        user.setRole(UserRole.ADMIN);
        user.setEmployeeId(1);
        user.setId(id);

        UserRequestDto dto = new UserRequestDto();

        when(repository.findById(id))
                .thenReturn(Optional.of(user));
        when(repository.save(any(User.class)))
                .thenReturn(user);

        UserResponseDto result = service.putUser(id, dto);
        assertNotNull(result);
        verify(repository).findById(id);
        verify(repository).save(user);
    }

    @Test
    void putUser_shouldThrowException_whenUserDoesntExists(){
        Integer id = 1;
        UserRequestDto dto = new UserRequestDto();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                ()-> service.putUser(id, dto)
        );

        verify(repository).findById(id);
        verify(repository, never()).save(any(User.class));
    }

    //patch test
    @Test
    void patchUser_shouldThrowException_whenUserExists(){
        Integer id = 1;
        User user = new User();
        user.setUserName("Samuel");
        user.setPassword("1234");
        user.setRole(UserRole.ADMIN);
        user.setEmployeeId(1);
        user.setId(id);

        UserPatchDto dto = new UserPatchDto();

        when(repository.findById(id))
                .thenReturn(Optional.of(user));
        when(repository.save(any(User.class)))
                .thenReturn(user);

        UserResponseDto result = service.patchUser(id, dto);
        assertNotNull(result);
        verify(repository).findById(id);
        verify(repository).save(user);
    }

    @Test
    void patchUser_shouldThrowException_whenUserDoesntExists(){
        Integer id = 1;
        UserPatchDto dto = new UserPatchDto();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                ()-> service.patchUser(id, dto)
        );
        verify(repository).findById(id);
        verify(repository, never()).save(any(User.class));
    }

    //delete test
    @Test
    void deleteUser_shouldThrowException_whenUserExists(){
        Integer id = 1;
        User user = new User();
        user.setUserName("Samuel");
        user.setPassword("1234");
        user.setRole(UserRole.ADMIN);
        user.setEmployeeId(1);
        user.setId(id);

        when(repository.findById(id))
                .thenReturn(Optional.of(user));
        service.deleteUser(id);
        verify(repository).findById(id);
        verify(repository).delete(user);
    }

    @Test
    void deleteUser_shouldThrowException_whenUserDoesntExists(){
        Integer id = 1;

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                ()-> service.deleteUser(id)
        );

        verify(repository).findById(id);
    }

}
