package net.thrymr.organisation.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUpload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    String fileDownloadUri;
    @Lob
    private byte[] data;
    public FileUpload(String fileName, String fileType,   String fileDownloadUri, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileDownloadUri=fileDownloadUri;
        this.data = data;
    }

}