package net.thrymr.organisation.management.repository;

import net.thrymr.organisation.management.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

List<Department> findAllByTeamSizeBetween(Integer start, Integer end);


}

