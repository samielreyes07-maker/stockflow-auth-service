package com.samuel.stockflow_auth_service.service;

import com.samuel.stockflow_auth_service.domain.Employee;
import com.samuel.stockflow_auth_service.dto.EmployeePatchDto;
import com.samuel.stockflow_auth_service.dto.EmployeeRequestDto;
import com.samuel.stockflow_auth_service.dto.EmployeeResponseDto;
import com.samuel.stockflow_auth_service.exception.ResourceNotFoundException;
import com.samuel.stockflow_auth_service.repository.EmployeeRepository;
import com.samuel.stockflow_auth_service.sevice.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl service;

    //post test
    @Test
    void postEmployee_shouldSaveEmployee_andReturnDt(){
        EmployeeRequestDto dto = new EmployeeRequestDto();
        dto.setName("Samuel");
        dto.setLastName("Reyes");

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Samuel");
        employee.setLastName("Reyes");

        when(repository.save(any(Employee.class)))
                .thenReturn(employee);

        EmployeeResponseDto result = service.postEmployee(dto);
        assertNotNull(result);
        verify(repository).save(any(Employee.class));
    }


    //get test
    @Test
    void getEmployee_shouldThrowException_whenEmployeeDoesNotExist(){
        Integer id = 1;

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                ()-> service.getEmployee(id)
        );

        verify(repository).findById(id);
    }

    @Test
    void getEmployees_shouldReturnPageOfEmployees(){
        Pageable pageable = PageRequest.of(0, 10);
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Samuel");
        employee.setLastName("Reyes");

        Page<Employee> employeePage = new PageImpl<>(List.of(employee));

        when(repository.findAll(any(Specification.class), any(Pageable.class)))
                .thenReturn(employeePage);

        Page<EmployeeResponseDto> result = service.getEmployees(
                null,
                null,
                pageable
        );

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(repository).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void getEmployee_shouldReturnEmployee_whenEmployeeExists(){
        Integer id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Samuel");
        employee.setLastName("Reyes");

        when(repository.findById(id))
                .thenReturn(Optional.of(employee));

        EmployeeResponseDto result = service.getEmployee(id);
        assertNotNull(employee);
        assertEquals("Samuel", result.getName());
        assertEquals("Reyes", result.getLastName());
        verify(repository).findById(id);
    }

    //put test
    @Test
    void putEmployee_shouldUpdateEmployee_whenEmployeeExists(){
        Integer id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Samuel");
        employee.setLastName("Reyes");

        EmployeeRequestDto dto = new EmployeeRequestDto();

        when(repository.findById(id))
                .thenReturn(Optional.of(employee));
        when(repository.save(any(Employee.class)))
                .thenReturn(employee);

        EmployeeResponseDto result =
                service.putEmployee(id, dto);

        assertNotNull(employee);
        verify(repository).findById(id);
        verify(repository).save(employee);
    }

    @Test
    void putEmployee_shouldThrowException_whenEmployeeDoesNotExist(){
        Integer id = 1;

        EmployeeRequestDto dto = new EmployeeRequestDto();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                ()-> service.putEmployee(id, dto)
        );

        verify(repository).findById(id);
        verify(repository, never()).save(any(Employee.class));
    }

    //patch test
    @Test
    void patchEmployee_shouldUpdateEmployee_whenEmployeeExists(){
        Integer id = 1;
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Samuel");
        employee.setLastName("Reyes");

        EmployeePatchDto dto = new EmployeePatchDto();

        when(repository.findById(id))
                .thenReturn(Optional.of(employee));

        when(repository.save(any(Employee.class)))
                .thenReturn(employee);

        EmployeeResponseDto result = service.patchEmployee(id, dto);

        assertNotNull(result);
        verify(repository).findById(id);
        verify(repository).save(employee);
    }

    @Test
    void patchEmployee_shouldThrowException_whenEmployeeDoesNotExist(){
        Integer id = 1;
        EmployeePatchDto dto = new EmployeePatchDto();

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                ()-> service.patchEmployee(id, dto)
        );

        verify(repository).findById(id);
        verify(repository, never()).save(any(Employee.class));
    }

    //Delete test
    @Test
    void deleteEmployee_shouldUpdateEmployee_whenEmployeeExists(){
        Integer id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Samuel");
        employee.setLastName("Reyes");

        when(repository.findById(id))
                .thenReturn(Optional.of(employee));

        service.deleteEmployee(id);
        verify(repository).findById(id);
        verify(repository).delete(employee);
    }

    @Test
    void deleteEmployee_shouldThrowException_whenEmployeeDoesNotExist(){
        Integer id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName("Samuel");
        employee.setLastName("Reyes");

        when(repository.findById(id))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                ()-> service.deleteEmployee(id)
        );

        verify(repository).findById(id);
        verify(repository, never()).delete(any(Employee.class));
    }

}
