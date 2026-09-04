package com.samuel.stockflow_auth_service.mapper;

import com.samuel.stockflow_auth_service.domain.Employee;
import com.samuel.stockflow_auth_service.dto.EmployeePatchDto;
import com.samuel.stockflow_auth_service.dto.EmployeeRequestDto;
import com.samuel.stockflow_auth_service.dto.EmployeeResponseDto;

public class EmployeeMapper {

    public static EmployeeResponseDto toResponseDto(Employee employee){
        EmployeeResponseDto dto = new EmployeeResponseDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setLastName(employee.getLastName());
        return dto;
    }

    public static Employee toEntity(EmployeeRequestDto dto){
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setLastName(dto.getLastName());
        return employee;
    }

    public static void toPutEmployee(Employee employee ,EmployeeRequestDto dto){
        employee.setName(dto.getName());
        employee.setLastName(dto.getLastName());
    }

    public static void toPatchEmployee(Employee employee , EmployeePatchDto dto){
        dto.getName()
                .ifPresent(employee::setName);
        dto.getLastName()
                .ifPresent(employee::setLastName);
    }
}
