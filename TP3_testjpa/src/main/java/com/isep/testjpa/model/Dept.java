package com.isep.testjpa.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptno;

    @Column(name = "dname")
    private String dname;

    @Column(name = "loc")
    private String loc;
}

