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


public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    private Integer floorNo;
    @Column(columnDefinition = "varchar(50)")

    private String  landmark;

    @Column(columnDefinition = "TEXT")

    private String fullAddress;

}