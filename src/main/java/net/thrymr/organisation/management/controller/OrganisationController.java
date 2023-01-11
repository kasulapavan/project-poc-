package net.thrymr.organisation.management.controller;

import net.thrymr.organisation.management.dto.*;

import net.thrymr.organisation.management.service.OrderBy;
import net.thrymr.organisation.management.service.serviceInterface.DepartmentService;
import net.thrymr.organisation.management.service.serviceInterface.FileService;
import net.thrymr.organisation.management.service.serviceInterface.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@CrossOrigin("*")

@RestController
@RequestMapping("/api/organisation")
public class OrganisationController {
    @Autowired
    public OrganisationService organisationService;

    @Autowired
    public DepartmentService departmentService;

    @Autowired
    public FileService fileService;
    @PostMapping(path = "/save")
    public OrganisationDto save(@RequestBody OrganisationDto organisationDto){
        return organisationService.saveOrganisation(organisationDto);
    }

    @PutMapping(path = "/update")
    public OrganisationDto update(@RequestBody OrganisationDto organisationDto){
        return organisationService.saveOrganisation(organisationDto);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteOrganisation(@PathVariable("id") Long id){
        return organisationService.deleteById(id);
    }
    @GetMapping(path = "/get-all")
    public List<OrganisationDto> getAll(){
        return organisationService.getAll();
    }
    @GetMapping(path = "/find-by-id/{id}")
    public OrganisationDto findById(@PathVariable("id") Long id) {
        return organisationService.findById(id);
    }
    @PutMapping(path = "/update-address")
    public AddressDto saveAddress(@RequestBody AddressDto addressDto){
        return organisationService.updateAddress(addressDto);
    }
    @DeleteMapping(path = "/delete-Address/{id}")
    public boolean deleteAddressById(@PathVariable("id") Long id){
        return organisationService.deleteAddressById(id);
    }
    @GetMapping("find-order-by/{orderBy}")
    public List<OrganisationDto> findByOrder(@PathVariable("orderBy") OrderBy order){
        return organisationService.findByOrderByAsc(order);
    }
    @GetMapping("order-by/{field}/{request}")
    public List<OrganisationDto> findOrderByLength(@PathVariable("field") String field,  @PathVariable("request") OrderBy order){
        return organisationService.getLengthSortedOrder(field,order);
    }
    @GetMapping("send-dept-details/{id}")
    public List<DepartmentDto> sendDeptList(@PathVariable Long id){
        return departmentService.sendDepartmentList(id);
    }
    @PutMapping(path = "/update-department/{id}")
    public DepartmentDto updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDto){
        return departmentService.updateDepartment(id, departmentDto);
    }
    @DeleteMapping(path = "/department-delete/{id}")
    public boolean deleteDepartment(@PathVariable("id") Long id){
        return departmentService.deleteById(id);
    }
    @GetMapping(path = "/get-all-department")
    public List<DepartmentDto> getAllDepartments(){
        return departmentService.getAll();
    }
    @PostMapping(path = "/save-all-department/{id}")
    public List<DepartmentDto>   saveListDepartments(@PathVariable Long id, @RequestBody List<DepartmentDto> departmentDto) {
        return departmentService.saveListDepartment(id, departmentDto);
    }
    @GetMapping(path = "/get-address/{id}")
    public AddressDto sendOrganisationAddress(@PathVariable Long id){
        return organisationService.sendOrganisationAddress(id);
    }

    @GetMapping(path = "/get-total-departments")
    public Map<String, Long> getDepartmentsTotal(){
        return departmentService.getDepartmentsTotal();
    }
    @GetMapping(path = "/get-team-size-between/{start}/{end}")
    public List<DepartmentDto> findAllByTeamSizeBetween(@PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return departmentService.findAllByTeamSizeBetween(start, end);
    }

    @GetMapping(path = "get-page-wise/{offset}/{pageSize}")
    public List<OrganisationDto> findAllOrganisationWithPagination(@PathVariable("offset") int offSet,@PathVariable("pageSize") int pageSize){
        return organisationService.findAllOrganisationWithPagination(offSet, pageSize);
    }
@GetMapping(path = "get-organisation/{keyword}")
    public List<OrganisationDto> getOrganisation(@PathVariable("keyword") String keyword){
        return organisationService.findAllBySearch(keyword);
}

    @PostMapping("/save-file")
    public FileDto saveFile(@RequestParam MultipartFile file) throws IOException {
        return fileService.store(file);
    }



    @GetMapping("/send-file-org-by/{id}")
    public FileDto sendFile(@PathVariable("id") Long id){
        return organisationService.sendFileByOrganisationId(id);
    }


}