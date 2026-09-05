package com.samuel.stockflow_auth_service.dto;

import com.samuel.stockflow_auth_service.domain.UserRole;

public class UserRequestDto {
    private String userName;
    private String password;
    private UserRole role;
    private Integer employeeId;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

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
