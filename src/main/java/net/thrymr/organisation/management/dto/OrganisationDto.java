package net.thrymr.organisation.management.dto;


import lombok.*;
import net.thrymr.organisation.management.entity.Address;
import net.thrymr.organisation.management.entity.Department;

import java.util.List;

@Getter
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrganisationDto {


    private Long organisationId;

    private String organisationName;
    private String description;

    private String contactNumber;

    private String email;
    private String aboutCompany;

    private AddressDto address;
    private List<DepartmentDto> departmentList;
    private FileDto fileUpload;



}
