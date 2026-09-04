package com.samuel.stockflow_auth_service.dto;

import com.samuel.stockflow_auth_service.domain.UserRole;

import java.util.Optional;

public class UserPatchDto {
    private Optional<String> userName = Optional.empty();
    private Optional<String> password = Optional.empty();
    private Optional<UserRole> role = Optional.empty();
    private Optional<Integer> employeeId = Optional.empty();

    public Optional<String> getUserName() {
        return userName;
    }

    public Optional<String> getPassword() {
        return password;
    }

    public Optional<UserRole> getRole() {
        return role;
    }

    public Optional<Integer> getEmployeeId() {
        return employeeId;
    }
}
