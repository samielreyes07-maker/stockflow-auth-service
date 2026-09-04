package com.samuel.stockflow_auth_service.dto;

import java.util.Optional;

public class EmployeePatchDto {
    private Optional<String> name = Optional.empty();
    private Optional<String> lastName = Optional.empty();

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getLastName() {
        return lastName;
    }

    public void setLastName(Optional<String> lastName) {
        this.lastName = lastName;
    }
}
