package net.thrymr.organisation.management.dto;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DepartmentDto {


    private Long departmentId;

    private String departmentName;

    private String hodName;

    private Integer teamSize;

    private String responsibilities;


}
