package com.enter.repair2.DTO;

import lombok.Data;

import java.io.File;

/**
* 类名： UploadFileDTO
*
*@author Liquid
*
* 描述：
*
*@date   2018/12/27
*/
@Data
public class UploadFileDTO {
    private String fileName;

    private File file;
}
