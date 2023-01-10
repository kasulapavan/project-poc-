package net.thrymr.organisation.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @Column(columnDefinition = "varchar(20)")
    private String departmentName;
    @Column(columnDefinition = "varchar(20)")

    private String hodName;

    private Integer teamSize;

    @Column(columnDefinition = "TEXT")
    private String responsibilities;

}