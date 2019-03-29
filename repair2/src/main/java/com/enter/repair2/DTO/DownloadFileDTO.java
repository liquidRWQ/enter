package com.enter.repair2.DTO;

import lombok.Data;

import java.io.File;

/**
 * @className DownloadFileDTO
 * @auther Liquid
 * @description
 * @date 2018/12/27
 */
@Data
public class DownloadFileDTO {

    private String fileName;

    private File file;
}
