package org.zlycerqan.publicnetdisk.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseModel<T> {

    int code;

    String message;

    T data;
    
}
