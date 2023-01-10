package net.thrymr.organisation.management.repository;

import net.thrymr.organisation.management.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileUpload, Long> {


}
