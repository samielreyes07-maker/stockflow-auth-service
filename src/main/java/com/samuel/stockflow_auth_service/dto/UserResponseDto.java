package com.samuel.stockflow_auth_service.dto;

import com.samuel.stockflow_auth_service.domain.UserRole;

public class UserResponseDto {
    private Integer id;
    private String userName;
    private UserRole role;
    private Integer employeeId;

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
