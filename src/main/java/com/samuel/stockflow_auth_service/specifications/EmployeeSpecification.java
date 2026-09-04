package com.samuel.stockflow_auth_service.specifications;

import com.samuel.stockflow_auth_service.domain.Employee;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {
    public static Specification<Employee> hasName(String name){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<Employee> hasLastName(String lastName){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("lastName"), lastName);
    }

    public static Specification<Employee> specification(
            String name, String lastName
    ){
        Specification<Employee> specification =
                (root, query, criteriaBuilder) -> null;

        if (name != null){
            specification = specification.and(
                    hasLastName(name)
            );
        }

        if(lastName != null){
            specification = specification.and(
                    hasLastName(lastName)
            );
        }

        return specification;
    }
}
