package com.samuel.stockflow_auth_service.controllers;

import com.samuel.stockflow_auth_service.dto.EmployeePatchDto;
import com.samuel.stockflow_auth_service.dto.EmployeeRequestDto;
import com.samuel.stockflow_auth_service.dto.EmployeeResponseDto;
import com.samuel.stockflow_auth_service.sevice.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<EmployeeResponseDto>> getEmployees(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName,
            Pageable pageable){
        return ResponseEntity.ok(employeeService.getEmployees(name, lastName, pageable));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<EmployeeResponseDto> getEmployee(
            @PathVariable Integer id){
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EmployeeResponseDto> postEmployee(
            @RequestBody EmployeeRequestDto dto){
        return ResponseEntity.ok(employeeService.postEmployee(dto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeResponseDto> putEmployee(
            @PathVariable Integer id,
            @RequestBody EmployeeRequestDto dto){
        return ResponseEntity.ok(employeeService.putEmployee(id, dto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<EmployeeResponseDto> patchEmployee(
             @PathVariable Integer id,
             @RequestBody EmployeePatchDto dto){
        return ResponseEntity.ok(employeeService.patchEmployee(id, dto));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteEmployee(
            @PathVariable Integer id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .build();
    }
}
