package org.zlycerqan.publicnetdisk.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zlycerqan.publicnetdisk.configuration.RepositoryConfiguration;
import org.zlycerqan.publicnetdisk.mapper.FileMapper;
import org.zlycerqan.publicnetdisk.model.FileModel;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service("FileService")
public class FileServiceImp implements FileService {

    @Resource
    private RepositoryConfiguration repositoryConfiguration;

    @Resource
    private FileMapper fileMapper;

    enum SystemType {
        WINDOWS,
        UNIX
    }

    SystemType systemType;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private String convertPath(String filename) {
        if (systemType == SystemType.WINDOWS) {
            return repositoryConfiguration.getPath() + "\\" + filename;
        } else {
            return repositoryConfiguration.getPath() + "/" + filename;
        }
    }

    public FileServiceImp() {
        if (System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")) {
            systemType = SystemType.WINDOWS;
        } else {
            systemType = SystemType.UNIX;
        }
    }

    @Override
    public boolean uploadFile(MultipartFile file) {
        FileModel fileModel = new FileModel();
        fileModel.setFilename(file.getOriginalFilename());
        fileModel.setUploadTime(simpleDateFormat.format(new Date()));
        fileModel.setSize(file.getSize());
        fileMapper.add(fileModel);
        try {
            file.transferTo(new File(convertPath(String.valueOf(fileModel.getId()))));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteFile(int id) {
        if (fileMapper.exists(id)) {
            fileMapper.delete(id);
            return new File(getFilePath(id)).delete();
        } else {
            return false;
        }
    }

    @Override
    public List<FileModel> getFileList() {
        return fileMapper.getAll();
    }

    @Override
    public String getFilePath(int id) {
        return convertPath(String.valueOf(id));
    }

    @Override
    public String getFilename(int id) {
        return fileMapper.getFilenameById(id);
    }

}
