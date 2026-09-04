package com.samuel.stockflow_auth_service.dto;

import com.samuel.stockflow_auth_service.domain.UserRole;

public class UserRequestDto {
    private String userName;
    private String password;
    private UserRole role;
    private Integer employeeId;

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public UserRole getRole() {
        return role;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }
}
