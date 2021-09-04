package org.zlycerqan.publicnetdisk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FileModel {

    int id;

    private String filename;

    private String uploadTime;

    private long size;

}
