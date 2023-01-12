package net.thrymr.organisation.management.service;

import net.thrymr.organisation.management.dto.FileDto;
import net.thrymr.organisation.management.dto.MyCustomException;
import net.thrymr.organisation.management.entity.FileUpload;
import net.thrymr.organisation.management.entity.Organisation;
import net.thrymr.organisation.management.repository.FileRepository;
import net.thrymr.organisation.management.repository.OrganisationRepository;
import net.thrymr.organisation.management.service.serviceInterface.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.io.IOException;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {


    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private ModelMapper modelMapper;

    public FileDto store(MultipartFile multipartFile) throws IOException {
        FileUpload fileUpload = new FileUpload();
        String fileName = multipartFile.getOriginalFilename();
        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/files/")
                .toUriString();


        FileUpload fileAttachment = new FileUpload( fileName, multipartFile.getContentType(),fileDownloadUri, multipartFile.getBytes());
        return modelMapper.map(fileRepository.save(fileAttachment), FileDto.class);
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long id) {
        Optional<Organisation> organisationOptional = organisationRepository.findById(id);
        FileUpload fileUpload = null;
        if (organisationOptional.isPresent()) {
            fileUpload = organisationOptional.get().getFileUpload();
            if (fileUpload != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileUpload.getFileName() + "\"")
                        .body(fileUpload.getData());
            } else throw new MyCustomException("No file on this organisation");
        } else throw new MyCustomException(" organisation Not ");

    }
}
