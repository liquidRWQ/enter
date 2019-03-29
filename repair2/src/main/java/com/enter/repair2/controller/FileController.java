package com.enter.repair2.controller;

import com.enter.repair2.DTO.DownloadFileDTO;
import com.enter.repair2.exception.FileException;
import com.enter.repair2.result.ResultBean;
import com.enter.repair2.utils.MyFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * @className FileController
 * @auther Liquid
 * @description
 * @date 2018/11/22
 */
@Slf4j
@RestController
public class FileController {
    @PostMapping("/uploadToFeedback")
    public ResultBean uploadToFeedback(HttpServletRequest httpServletRequest, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
    /**
     * @auther Liquid
     * 
     * @param [httpServletRequest, file]
     * 
     * @description
     * 
     * @date 2018/11/22 
     */
        String serverPath = MyFileUtil.setImgToServer(httpServletRequest, file, "feedback");
        return new ResultBean<String>(serverPath);

    }

    @PostMapping("/uploadToOrder")
    public ResultBean uploadToFault(HttpServletRequest httpServletRequest, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        /**
         * @auther Liquid
         *
         * @param [httpServletRequest, file]
         *
         * @description
         *
         * @date 2018/11/22
         */
        String serverPath = MyFileUtil.setImgToServer(httpServletRequest, file, "order");
        return new ResultBean<String>(serverPath);

    }

    @PostMapping("/uploadToAdvertisement")
    public ResultBean uploadToAdvertisement(HttpServletRequest httpServletRequest, @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        /**
         * @auther Liquid
         *
         * @param [httpServletRequest, file]
         *
         * @description
         *
         * @date 2018/11/22
         */
        String serverPath = MyFileUtil.setImgToServer(httpServletRequest, file, "advertisement");
        return new ResultBean<String>(serverPath);

    }

    @PostMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(DownloadFileDTO downloadFileDTO, HttpServletRequest httpServletRequest) throws Exception {
        httpServletRequest.getSession().getServletContext().getRealPath("路径");
        String filePath = System.getProperty("user.home") + "/files/" + downloadFileDTO.getFileName();
        //依据文件路径构建File对象
        File file = new File(filePath);
        //创建响应头对象，设置响应信息
        HttpHeaders headers = new HttpHeaders();
        try {
            //对文件名进行重新编码，防止在响应头中出现中文乱码
            String headerFileName = new String(downloadFileDTO.getFileName().getBytes("UTF-8"), "ISO-8859-1");
            //设置响应内容处理方式为附件,并指定文件名
            headers.setContentDispositionFormData("attachment", headerFileName);
            //设置响应头类型为application/octet-stream,表示是一个流类型
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //将文件转换成字节数组
            byte[] body = FileUtils.readFileToByteArray(file);
            //创建ResponseEntity对象（封装文件字节数组、响应头、响应状态码）
            ResponseEntity<byte[]> entity = new ResponseEntity<>(body, headers, HttpStatus.CREATED);
            //最后将整个ResponseEntity对象返回给DispatcherServlet
            return entity;
        } catch (Exception e) {
            throw new FileException("文件下载失败:"+e.toString());
        }

    }
}
