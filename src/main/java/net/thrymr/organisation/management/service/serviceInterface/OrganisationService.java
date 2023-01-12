package net.thrymr.organisation.management.service.serviceInterface;

import net.thrymr.organisation.management.dto.*;
import net.thrymr.organisation.management.service.OrderBy;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface OrganisationService {

    OrganisationDto saveOrganisation(OrganisationDto organisationDto);
    boolean deleteById(Long id);

    List<OrganisationDto> getAll();

    OrganisationDto findById(Long id) ;

    AddressDto updateAddress(AddressDto addressDto);

    boolean deleteAddressById(Long id);

    List<OrganisationDto> findByOrderByAsc(OrderBy order);

    List<OrganisationDto> getLengthSortedOrder(String field, OrderBy order);

    List<DepartmentDto> sendDepartmentList(Long id);

    AddressDto sendOrganisationAddress(Long id);


List<OrganisationDto> findAllOrganisationWithPagination(int offset, int pageSize);

    List<OrganisationDto> findAllBySearch(String search);
    public FileDto sendFileByOrganisationId(Long id);

    public BufferedImage sendFile(Long id) throws IOException;

}
