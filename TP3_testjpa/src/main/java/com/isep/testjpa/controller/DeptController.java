package com.isep.testjpa.controller;

import com.isep.testjpa.model.Dept;
import com.isep.testjpa.repository.DeptRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")

public class DeptController {

    private final DeptRepository deptRepository;

    public DeptController(DeptRepository deptRepository) {
        this.deptRepository = deptRepository;
    }


    @GetMapping
    public List<Dept> getAllDepartments() {
        return deptRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Dept> getDepartmentById(@PathVariable Long id) {
        return deptRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dept> updateDepartment(@PathVariable Long id, @RequestBody Dept updatedDept) {
        return deptRepository.findById(id)
                .map(dept -> {
                    dept.setDname(updatedDept.getDname());
                    dept.setLoc(updatedDept.getLoc());
                    Dept saved = deptRepository.save(dept);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        return deptRepository.findById(id)
                .map(dept -> {
                    deptRepository.delete(dept);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
