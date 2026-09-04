package com.samuel.stockflow_auth_service.controllers;

import ch.qos.logback.core.joran.spi.HttpUtil;
import com.samuel.stockflow_auth_service.dto.UserPatchDto;
import com.samuel.stockflow_auth_service.dto.UserRequestDto;
import com.samuel.stockflow_auth_service.dto.UserResponseDto;
import com.samuel.stockflow_auth_service.sevice.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<UserResponseDto>> getUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Integer id,
            Pageable pageable
    ){
        return ResponseEntity.ok(userService.getUsers(name, lastName, id, pageable));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Integer id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponseDto> postUser(
            @RequestBody UserRequestDto dto){
        return ResponseEntity.ok(userService.postUser(dto));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public ResponseEntity<UserResponseDto> putUser(
            @PathVariable Integer id,
            @RequestBody UserRequestDto dto){
        return ResponseEntity.ok(userService.putUser(id, dto));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PATCH)
    public ResponseEntity<UserResponseDto> patchUser(
            @PathVariable Integer id,
            @RequestBody UserPatchDto dto){
        return ResponseEntity.ok(userService.patchUser(id, dto));
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(
            @PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
