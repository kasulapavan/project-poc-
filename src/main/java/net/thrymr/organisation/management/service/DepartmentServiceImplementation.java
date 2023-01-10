package net.thrymr.organisation.management.service;


import net.thrymr.organisation.management.dto.MyCustomException;
import net.thrymr.organisation.management.dto.DepartmentDto;


import net.thrymr.organisation.management.entity.Department;
import net.thrymr.organisation.management.entity.Organisation;
import net.thrymr.organisation.management.repository.DepartmentRepository;
import net.thrymr.organisation.management.repository.OrganisationRepository;
import net.thrymr.organisation.management.service.serviceInterface.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;

@Service
public class DepartmentServiceImplementation implements DepartmentService {

    @Autowired

    public DepartmentRepository departmentRepository;

    @Autowired
    public OrganisationRepository organisationRepository;

    @Autowired
    public ModelMapper modelMapper;


    public String saveDepartment(Long id, DepartmentDto departmentDto) {
        Department department = new Department();
        Optional<Organisation> organisation = organisationRepository.findById(id);
        if (organisation.isPresent()) {
            department.setDepartmentId(departmentDto.getDepartmentId());
            department.setDepartmentName(departmentDto.getDepartmentName());
            department.setHodName(departmentDto.getHodName());
            department.setTeamSize(departmentDto.getTeamSize());
            department.setResponsibilities(departmentDto.getResponsibilities());
            Organisation organisation1 = organisation.get();
            organisation1.getDepartmentList().add(department);
            organisationRepository.save(organisation1);
            return "modelMapper.map(department.)";
        } else {
            return "organisation is not found";
        }
    }




    @Override
    public List<DepartmentDto> saveListDepartment(Long id, List<DepartmentDto> departmentDto) {
        Optional<Organisation> organisation = organisationRepository.findById(id);
        List<Department> departments = null;
        if (organisation.isPresent()) {
            departments = new ArrayList<Department>();

            for (DepartmentDto departmentDto1 : departmentDto) {
                departments.add(new Department(departmentDto1.getDepartmentId(), departmentDto1.getDepartmentName(), departmentDto1.getHodName(), departmentDto1.getTeamSize(), departmentDto1.getResponsibilities()));
            }
            Organisation organisation1 = organisation.get();
            organisation1.getDepartmentList().addAll(departments);
            organisationRepository.save(organisation1);
            departments = organisation.get().getDepartmentList();
        }
        return departments.stream().map(department -> modelMapper.map(department, DepartmentDto.class)).toList();
    }

    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDao) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        Department department = null;
        if (departmentOptional.isPresent()) {
            department = modelMapper.map(departmentDao, Department.class);
        }
        return modelMapper.map(departmentRepository.save(department), DepartmentDto.class);
    }


    @Override
    public boolean deleteById(Long id) {
        try {
            departmentRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DepartmentDto> getAll() {
        List<Department> departments = departmentRepository.findAll();
        return deptEntityToDto(departments);

    }

    public Map<String, Long> getDepartmentsTotal() {
        List<Organisation> organisationList = organisationRepository.findAll();
        return organisationList.stream().collect(Collectors.groupingBy(Organisation::getOrganisationName,
                Collectors.flatMapping(organisation -> organisation.getDepartmentList().stream().map(Department::getDepartmentId), Collectors.counting())));
    }

    public List<DepartmentDto> findAllByTeamSizeBetween(Integer start, Integer end) {
        List<Department> departmentList = departmentRepository.findAllByTeamSizeBetween(start, end);
        return deptEntityToDto(departmentList);
    }

    @Override
    public List<DepartmentDto> sendDepartmentList(Long id) {
        Optional<Organisation> organisationOptional = organisationRepository.findById(id);
        List<Department> departments = null;
        if(organisationOptional.isPresent()){

            departments = organisationOptional.get().getDepartmentList();
            return deptEntityToDto(departments);
        }
        else throw  new MyCustomException("Organisation not found");
        }


    public List<Department> dtoToDeptEntity(List<DepartmentDto> departmentDtos) {
        List<Department> departmentList = new ArrayList<Department>();
        for (DepartmentDto department : departmentDtos) {
            Department department1 = new Department();
            department1.setDepartmentId(department.getDepartmentId());
            department1.setDepartmentName(department.getDepartmentName());
            department1.setHodName(department.getHodName());
            department1.setResponsibilities(department.getResponsibilities());
            department1.setTeamSize(department.getTeamSize());
            departmentList.add(department1);
        }
        return departmentList;

    }



    public List<DepartmentDto> deptEntityToDto(List<Department> departmentList) {
        List<DepartmentDto> departmentDtos = new ArrayList<DepartmentDto>();
        for (Department department : departmentList) {
            DepartmentDto department1 = new DepartmentDto();
            department1.setDepartmentId(department.getDepartmentId());
            department1.setDepartmentName(department.getDepartmentName());
            department1.setHodName(department.getHodName());
            department1.setResponsibilities(department.getResponsibilities());
            department1.setTeamSize(department.getTeamSize());
            departmentDtos.add(department1);
        }
        return departmentDtos;
    }
}