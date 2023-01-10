package net.thrymr.organisation.management.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long organisationId;

    @Column(columnDefinition = "varchar(20)")
    private String organisationName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "varchar(10)")

    private String contactNumber;

    private String email;
    @Column(columnDefinition = "TEXT")
    private String aboutCompany;

    @OneToOne(cascade = {CascadeType.ALL})
    private Address address;

    @OneToMany(targetEntity = Department.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "organisation_id", referencedColumnName = "organisationId")
    private List<Department> departmentList;
    @Column(columnDefinition = "TEXT")
    private String search;
}
