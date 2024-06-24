package com.nexusnova.lifetravelapi.app.core.tours.domain.repositories;

import com.nexusnova.lifetravelapi.app.core.tours.domain.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query("select d from Department d where d.deleted = false and d.name like %:departmentName%")
    Optional<Department> findByName(@Param("departmentName") String departmentName);
}
