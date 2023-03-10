package net.thrymr.organisation.management.dto;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {

    private  Long id;
    private String fileName;


    private String fileType;

    String fileDownloadUri;
    @Lob
    private byte[] data;



    public FileDto(String fileName, String fileType,String fileDownloadUri, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
      this.fileDownloadUri=fileDownloadUri;
        this.data = data;
    }
}
