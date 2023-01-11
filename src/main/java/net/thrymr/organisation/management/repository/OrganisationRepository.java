package net.thrymr.organisation.management.repository;


import net.thrymr.organisation.management.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    List<Organisation> findAllByOrderByOrganisationNameAsc();
    List<Organisation> findAllByOrderByOrganisationNameDesc();
List<Organisation> findAllBySearchContainingIgnoreCase(String search);

//List<Organisation> findAllByOrderByOrganisationIdAndDepartmentListDepartmentIdAsc();


}