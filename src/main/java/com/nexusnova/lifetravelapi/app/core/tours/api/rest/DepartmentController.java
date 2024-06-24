package com.nexusnova.lifetravelapi.app.core.tours.api.rest;

import com.nexusnova.lifetravelapi.app.core.tours.domain.model.Department;
import com.nexusnova.lifetravelapi.app.core.tours.domain.services.RegionQueryService;
import com.nexusnova.lifetravelapi.app.core.tours.mapper.DepartmentMapper;
import com.nexusnova.lifetravelapi.app.core.tours.resources.details.DepartmentDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departments")
@Tag(name = "Department Controller")
@CrossOrigin
public class DepartmentController {
    private final RegionQueryService regionQueryService;
    private final DepartmentMapper departmentMapper;
    public DepartmentController(RegionQueryService regionQueryService,
                                DepartmentMapper departmentMapper) {
        this.regionQueryService = regionQueryService;
        this.departmentMapper = departmentMapper;
    }
    @GetMapping("")
    @Operation(summary = "Get All Departments Name")
    public List<DepartmentDetail> getAllDepartmentsName() {
        List<Department> departments = regionQueryService.handle();
        return departmentMapper.toDetail(departments);
    }
}