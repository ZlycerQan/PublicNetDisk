package org.zlycerqan.publicnetdisk.service;

import org.springframework.web.multipart.MultipartFile;
import org.zlycerqan.publicnetdisk.model.FileModel;

import java.io.IOException;
import java.util.List;

public interface FileService {

    boolean uploadFile(MultipartFile file) throws IOException;

    boolean deleteFile(int id);

    List<FileModel> getFileList();

    String getFilePath(int id);

    String getFilename(int id);

}
