package net.thrymr.organisation.management.service;

import net.thrymr.organisation.management.dto.*;
import net.thrymr.organisation.management.entity.Address;
import net.thrymr.organisation.management.entity.Department;
import net.thrymr.organisation.management.entity.FileUpload;
import net.thrymr.organisation.management.entity.Organisation;
import net.thrymr.organisation.management.repository.AddressRepository;
import net.thrymr.organisation.management.repository.FileRepository;
import net.thrymr.organisation.management.repository.OrganisationRepository;
import net.thrymr.organisation.management.service.serviceInterface.OrganisationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganisationServiceImplementation implements OrganisationService {
    @Autowired
    public OrganisationRepository organisationRepository;

    @Autowired
    public AddressRepository addressRepository;

    @Autowired
    public FileRepository fileRepository;


    @Autowired
    public ModelMapper modelMapper;

    @Override
    public OrganisationDto saveOrganisation(OrganisationDto organisationDto) {
        Organisation organisation = modelMapper.map(organisationDto, Organisation.class);
        organisation.setSearch(organisation.getOrganisationId() + organisation.getOrganisationName() + organisation.getAboutCompany() + organisation.getContactNumber() + organisation.getDescription() + organisation.getEmail() + organisation.getAddress() + organisation.getDepartmentList());
        Optional<FileUpload> fileUploadOptional = fileRepository.findById(organisationDto.getFileUpload().getId());
            organisation.setFileUpload(fileUploadOptional.get());
        return modelMapper.map(organisationRepository.save(organisation), OrganisationDto.class);
    }

    @Override
    public List<OrganisationDto> findAllOrganisationWithPagination(int offset, int pageSize) {
        Page<Organisation> organisations = organisationRepository.findAll(PageRequest.of(offset, pageSize));
        return organisations.stream().map(organisation -> modelMapper.map(organisation, OrganisationDto.class)).toList();

    }

    @Override
    public List<OrganisationDto> findAllBySearch(String keyword) {

        List<Organisation> organisations = organisationRepository.findAllBySearchContainingIgnoreCase(keyword);
        return organisations.stream().map(organisation -> modelMapper.map(organisation, OrganisationDto.class)).toList();
    }


     @Override
     public boolean deleteById(Long id) {
         try {
             organisationRepository.deleteById(id);
             return true;
         } catch (Exception exception) {
             exception.printStackTrace();
             return false;
         }
     }

    @Override
    public List<OrganisationDto> getAll() {
        List<Organisation> organisations = organisationRepository.findAll();

        return organisations.stream().map(organisation -> modelMapper.map(organisation, OrganisationDto.class)).toList();
    }

//    @Override
//    public ApiResponse findById(Long id)  {
//        Optional<Organisation> organisation = organisationRepository.findById(id);
//        return organisation.map(value -> new ApiResponse(200, " success", modelMapper.map(value, OrganisationDto.class))).orElseGet(() -> new ApiResponse(500, " id is not found", null));
//        }


    @Override
    public AddressDto updateAddress(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        return modelMapper.map(addressRepository.save(address), AddressDto.class);

    }

    public boolean deleteAddressById(Long addressId) {
        try {
            addressRepository.deleteById(addressId);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OrganisationDto> findByOrderByAsc(OrderBy order) {
        List<Organisation> organisationList = new ArrayList<Organisation>();
        if (order.equals(OrderBy.ASC)) {
            organisationList = organisationRepository.findAllByOrderByOrganisationNameAsc();
        } else if (order.equals(OrderBy.DESC)) {
            organisationList = organisationRepository.findAllByOrderByOrganisationNameDesc();
        }
        return organisationList.stream().map(organisation -> modelMapper.map(organisation, OrganisationDto.class)).toList();
    }

    @Override
    public List<OrganisationDto> getLengthSortedOrder(String field, OrderBy order) {

        List<Organisation> organisationList = new ArrayList<Organisation>();

        if (order.equals(OrderBy.ASC)) {
            organisationList = organisationRepository.findAll(Sort.by(Sort.Direction.ASC, field));
        } else if (order.equals(OrderBy.DESC)) {
            organisationList = organisationRepository.findAll(Sort.by(Sort.Direction.DESC, field));
        }
        return organisationList.stream().map(organisation -> modelMapper.map(organisation, OrganisationDto.class)).toList();
    }

    @Override
    public OrganisationDto findById(Long id) {
        Optional<Organisation> organisationOptional = organisationRepository.findById(id);
        Organisation organisation1 = organisationOptional.orElseThrow(() -> new MyCustomException("Organisation not found"));
        return modelMapper.map(organisation1, OrganisationDto.class);

    }

    @Override
    public List<DepartmentDto> sendDepartmentList(Long id) {
        Optional<Organisation> organisationOptional = organisationRepository.findById(id);
        List<Department> departments = null;

        if (organisationOptional.isPresent()) {
            departments = organisationOptional.get().getDepartmentList();

            return departments.stream().map(department -> modelMapper.map(department, DepartmentDto.class)).toList();
        } else throw new MyCustomException("Organisation not found");
    }


    public AddressDto sendOrganisationAddress(Long id) {
        Optional<Organisation> organisationOptional = organisationRepository.findById(id);
        Address address = null;
        if (organisationOptional.isPresent()) {
            address = organisationOptional.get().getAddress();
            return modelMapper.map(address, AddressDto.class);
        } else throw new MyCustomException("Organisation not found");
    }

    public FileDto sendFileByOrganisationId(Long id){
        Optional<Organisation> organisationOptional = organisationRepository.findById(id);
        FileUpload fileUpload = null;
        if(organisationOptional.isPresent()) {
            fileUpload = organisationOptional.get().getFileUpload();
            if (fileUpload != null) {
                return modelMapper.map(fileUpload, FileDto.class);
            }
            else throw new MyCustomException("No file on this organisation");
        }
        else throw new MyCustomException("Organisation not found");
    }

    public BufferedImage sendFile(Long id) throws IOException {
        Optional<Organisation> organisationOptional = organisationRepository.findById(id);
        FileUpload fileUpload = null;
        byte[] bytes = null;
        if(organisationOptional.isPresent()) {
            fileUpload = organisationOptional.get().getFileUpload();
            if (fileUpload != null) {
                bytes= fileUpload.getData();
                return ImageIO.read(new ByteArrayInputStream(bytes));
            }
            else return null;
        }
        else return null;

    }

//Entity to Dto & Dto to Entity

    private Address dtoToModel(AddressDto addressesDto) {
        Address addresses = new Address();
        if (addressesDto.getAddressId() != null && addressesDto.getAddressId() != 0) {
            addresses.setAddressId(addressesDto.getAddressId());
        }
        addresses.setFullAddress(addressesDto.getFullAddress());
        addresses.setLandmark(addressesDto.getLandmark());
        addresses.setFloorNo(addressesDto.getFloorNo());

        return addresses;
    }

    //
    public AddressDto modelToDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(address.getAddressId());
        addressDto.setFullAddress(address.getFullAddress());
        addressDto.setLandmark(address.getLandmark());
        return addressDto;
    }

    //
//
//]

//
//
//
//
//    public Organisation dtoToEntity(OrganisationDto organisationDto){
//        Organisation organisation1 = new Organisation();
//        if(organisationDto != null) {
//            organisation1.setOrganisationId(organisationDto.getOrganisationId());
//        }
//        organisation1.setOrganisationName(organisationDto.getOrganisationName());
//        organisation1.setDescription(organisationDto.getDescription());
//        organisation1.setContactNumber(organisationDto.getContactNumber());
//        organisation1.setAboutCompany(organisationDto.getAboutCompany());
//        organisation1.setEmail(organisationDto.getEmail());
//        organisation1.setAddress(dtoToModel(organisationDto.getAddress()));
//        DepartmentServiceImplementation dept = new DepartmentServiceImplementation();
//        organisation1.setDepartmentList(dept.dtoToDeptEntity(organisationDto.getDepartmentList()));
//
//return organisation1;
//    }
//
    public List<OrganisationDto> entityToDto(List<Organisation> organisations) {

        List<OrganisationDto> organisationDtos1 = new ArrayList<>();
        for (Organisation organisation : organisations) {

            OrganisationDto organisation1 = new OrganisationDto();
            organisation1.setOrganisationId(organisation.getOrganisationId());
            organisation1.setOrganisationName(organisation.getOrganisationName());
            organisation1.setDescription(organisation.getDescription());
            organisation1.setContactNumber(organisation.getContactNumber());
            organisation1.setAboutCompany(organisation.getAboutCompany());
            organisation1.setEmail(organisation.getEmail());
            organisation1.setAddress(modelToDto(organisation.getAddress()));
            DepartmentServiceImplementation dept = new DepartmentServiceImplementation();
            organisation1.setDepartmentList(dept.deptEntityToDto(organisation.getDepartmentList()));
            organisationDtos1.add(organisation1);
        }

        return organisationDtos1;
    }
}
