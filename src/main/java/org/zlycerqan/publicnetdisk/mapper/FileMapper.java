package org.zlycerqan.publicnetdisk.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.zlycerqan.publicnetdisk.model.FileModel;

import java.util.List;

@Component
@Mapper
public interface FileMapper {

    @Insert("INSERT INTO file(filename, upload_time, size) VALUES(\"${filename}\", \"${uploadTime}\", ${size})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void add(FileModel fileModel);

    @Delete("DELETE FROM file WHERE id = ${id}")
    void delete(int id);

    @Select("SELECT EXISTS(SELECT 1 FROM file WHERE id = #{id})")
    boolean exists(int id);

    @Select("SELECT * FROM file")
    @Results({
            @Result(property = "id", column = "id", javaType = Integer.class),
            @Result(property = "filename", column = "filename", javaType = String.class),
            @Result(property = "uploadTime", column = "upload_time", javaType = String.class),
            @Result(property = "size", column = "size", javaType = Long.class)
    })
    List<FileModel> getAll();

    @Select("SELECT filename FROM file WHERE id = #{id}")
    String getFilenameById(int id);

}
