package net.thrymr.organisation.management.service;

import net.thrymr.organisation.management.dto.FileDto;
import net.thrymr.organisation.management.entity.FileUpload;
import net.thrymr.organisation.management.repository.FileRepository;
import net.thrymr.organisation.management.service.serviceInterface.FileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {


    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private ModelMapper modelMapper;

    public FileDto store(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        FileUpload fileAttachment = new FileUpload( fileName, multipartFile.getContentType(), multipartFile.getBytes());
        return modelMapper.map(fileRepository.save(fileAttachment), FileDto.class);
    }



}
