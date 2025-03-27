package com.isep.testjpa.controller;

import com.isep.testjpa.model.Emp;
import com.isep.testjpa.repository.EmpRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmpController {

    private final EmpRepository empRepository;

    public EmpController(EmpRepository empRepository) {
        this.empRepository = empRepository;
    }

    @GetMapping
    public List<Emp> getAllEmployees() {
        return empRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emp> getEmployeeById(@PathVariable Long id) {
        return empRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emp> updateEmployee(@PathVariable Long id, @RequestBody Emp updatedEmp) {
        return empRepository.findById(id)
                .map(emp -> {
                    emp.setEname(updatedEmp.getEname());
                    emp.setEfirst(updatedEmp.getEfirst());
                    emp.setJob(updatedEmp.getJob());
                    emp.setMgr(updatedEmp.getMgr());
                    emp.setSal(updatedEmp.getSal());
                    Emp saved = empRepository.save(emp);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return empRepository.findById(id)
                .map(emp -> {
                    empRepository.delete(emp);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

