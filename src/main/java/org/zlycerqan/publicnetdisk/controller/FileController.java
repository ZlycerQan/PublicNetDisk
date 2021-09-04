package org.zlycerqan.publicnetdisk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zlycerqan.publicnetdisk.model.FileModel;
import org.zlycerqan.publicnetdisk.model.ResponseModel;
import org.zlycerqan.publicnetdisk.service.FileService;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class FileController {

    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/api/get_file_list")
    public ResponseModel<List<FileModel>> getFileList() {
        return new ResponseModel<>(200, "OK", fileService.getFileList());
    }

    @DeleteMapping("/api/delete_file")
    public ResponseModel<Boolean> deleteFile(@RequestParam(value = "id") int id) {
        if (fileService.deleteFile(id)) {
            return new ResponseModel<>(200, "OK", true);
        } else {
            return new ResponseModel<>(500, "Internal Server Error", false);
        }
    }

    @PostMapping("/api/upload_file")
    public ResponseModel<Boolean> uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
        if (fileService.uploadFile(file)) {
            return new ResponseModel<>(200, "OK", true);
        } else {
            return new ResponseModel<>(500, "Internal Server Error", false);
        }
    }

    @GetMapping("/api/download_file/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String id) throws IOException {
        int idn;
        try {
            idn = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            return ResponseEntity.notFound().build();
        }
        if (!String.valueOf(idn).equals(id)) {
            return ResponseEntity.notFound().build();
        }
        File tmp = new File(fileService.getFilePath(idn));
        if (!tmp.exists()) {
            return ResponseEntity.notFound().build();
        }
        FileSystemResource fileSystemResource = new FileSystemResource(tmp);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment;filename*=UTF-8''" + URLEncoder.encode(fileService.getFilename(idn), StandardCharsets.UTF_8));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(fileSystemResource.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new InputStreamResource(fileSystemResource.getInputStream()));
    }
}
