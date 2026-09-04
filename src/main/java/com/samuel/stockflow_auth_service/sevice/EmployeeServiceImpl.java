package com.samuel.stockflow_auth_service.sevice;

import com.samuel.stockflow_auth_service.domain.Employee;
import com.samuel.stockflow_auth_service.dto.EmployeePatchDto;
import com.samuel.stockflow_auth_service.dto.EmployeeRequestDto;
import com.samuel.stockflow_auth_service.dto.EmployeeResponseDto;
import com.samuel.stockflow_auth_service.exception.ResourceNotFoundException;
import com.samuel.stockflow_auth_service.mapper.EmployeeMapper;
import com.samuel.stockflow_auth_service.repository.EmployeeRepository;
import com.samuel.stockflow_auth_service.specifications.EmployeeSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    private Employee employee(Integer id){
        return repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "The employee id haven't been found, you can try again"
                ));
    }

    @Override
    public Page<EmployeeResponseDto> getEmployees(
            String name, String lastName, Pageable pageable
    ){
        Specification<Employee> specification = EmployeeSpecification
                .specification(
                name, lastName
        );
        return repository.findAll(specification, pageable)
                .map(EmployeeMapper::toResponseDto);

    }

    @Override
    public EmployeeResponseDto getEmployee(Integer id){
        Employee employee = employee(id);
        return EmployeeMapper.toResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto postEmployee(EmployeeRequestDto dto){
        Employee employee = EmployeeMapper.toEntity(dto);
        repository.save(employee);
        return EmployeeMapper.toResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto putEmployee(Integer id, EmployeeRequestDto dto){
        Employee employee = employee(id);
        EmployeeMapper.toPutEmployee(employee, dto);
        repository.save(employee);
        return EmployeeMapper.toResponseDto(employee);
    }

    @Override
    public EmployeeResponseDto patchEmployee(Integer id, EmployeePatchDto dto){
        Employee employee = employee(id);
        EmployeeMapper.toPatchEmployee(employee, dto);
        repository.save(employee);
        return EmployeeMapper.toResponseDto(employee);
    }

    @Override
    public void deleteEmployee(Integer id){
        Employee employee = employee(id);
        repository.delete(employee);
    }
}
