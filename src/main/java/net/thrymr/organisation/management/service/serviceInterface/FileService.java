package net.thrymr.organisation.management.service.serviceInterface;

import net.thrymr.organisation.management.dto.FileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    FileDto store(MultipartFile multipartFile)throws IOException;

    ResponseEntity<byte[]> getImage(Long id);
}
