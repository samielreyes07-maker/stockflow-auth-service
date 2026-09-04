package com.samuel.stockflow_auth_service.sevice;

import com.samuel.stockflow_auth_service.dto.EmployeePatchDto;
import com.samuel.stockflow_auth_service.dto.EmployeeRequestDto;
import com.samuel.stockflow_auth_service.dto.EmployeeResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {
    public Page<EmployeeResponseDto> getEmployees(String name, String lastName, Pageable pageable);
    public EmployeeResponseDto getEmployee(Integer id);
    public EmployeeResponseDto postEmployee(EmployeeRequestDto dto);
    public EmployeeResponseDto putEmployee(Integer id, EmployeeRequestDto dto);
    public EmployeeResponseDto patchEmployee(Integer id, EmployeePatchDto dto);
    public void deleteEmployee(Integer id);
}
