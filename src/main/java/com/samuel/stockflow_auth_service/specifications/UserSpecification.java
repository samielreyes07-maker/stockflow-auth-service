package com.samuel.stockflow_auth_service.specifications;

import com.samuel.stockflow_auth_service.domain.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> hasName(String name){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<User> hasLastName(String lastName){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("lastName"), lastName);
    }

    public static Specification<User> hasEmployeeId(Integer id){
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("employeeId"), id);
    }

    public static Specification<User> specification(
            String name, String lastLame, Integer id
    ){
        Specification<User> specification =
                (root, query, criteriaBuilder) ->
                        null;

        if (name != null){
            specification = specification.and(
                    hasName(name)
            );
        }
        if (lastLame != null){
            specification = specification.and(
                    hasLastName(lastLame)
            );
        }
        if (id != null){
            specification = specification.and(
                    hasEmployeeId(id)
            );
        }

        return specification;
    }
}
