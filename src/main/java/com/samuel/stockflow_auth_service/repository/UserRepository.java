package com.samuel.stockflow_auth_service.repository;

import com.samuel.stockflow_auth_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, Integer> , JpaSpecificationExecutor<User> {
    boolean existsUserByUserName(String userName);

    boolean existsUserByEmployeeId(Integer employeeId);
}
