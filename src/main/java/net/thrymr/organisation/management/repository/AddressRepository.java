package net.thrymr.organisation.management.repository;

import net.thrymr.organisation.management.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
