package net.thrymr.organisation.management.service.serviceInterface;
import net.thrymr.organisation.management.dto.DepartmentDto;
import net.thrymr.organisation.management.dto.OrganisationDto;
import net.thrymr.organisation.management.entity.Organisation;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    String saveDepartment(Long id, DepartmentDto departmentDto);
    List<DepartmentDto>   saveListDepartment(Long id, List<DepartmentDto> departmentDto);

    boolean deleteById(Long id);
    List<DepartmentDto> getAll();
    DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto);

 Map<String, Long> getDepartmentsTotal();

 List<DepartmentDto> findAllByTeamSizeBetween(Integer start, Integer end);

    List<DepartmentDto>  sendDepartmentList(Long id);
}