package com.enter.repair2.utils;

import com.enter.repair2.exception.UnCheckedException;
import com.enter.repair2.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 类名： MyFileUtil
 *
 * @author Liquid
 * <p>
 * 描述： 文件上传类
 * @date 2018/12/27
 */
@Slf4j
public class MyFileUtil {

    /**
     * @param httpServletRequest
     * @param file
     * @param folderName         描述：将图片上传到服务器
     * @return java.lang.String
     * @author Liquid
     * @date 2018/12/27
     */
    public static String setImgToServer(HttpServletRequest httpServletRequest, MultipartFile file, String folderName) {

        String fileName = file.getOriginalFilename();
        String type = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toUpperCase();
        String admitType = "GIF|PNG|JPG|JPEG";
        log.debug("图片初始名称为：" + fileName + " 类型为：" + type);
        if (admitType.contains(type)) {
            // 项目在容器中实际发布运行的根路径
            String realPath = httpServletRequest.getSession().getServletContext().getRealPath("/");
            // 自定义的文件名称
            String trueFolderName = IdUtils.getRandomStringId();
            String trueFileName = IdUtils.getRandomStringId();
            // 设置存放图片文件的路径
            String path = realPath + "uploads/" + folderName + "/" + trueFolderName;
            log.debug("图片放置位置为：" + path);
            File serverFile = new File(path);
            if (!serverFile.exists()) {
                if (serverFile.mkdirs()) {
                    throw new UnCheckedException("上传图片异常: 创建空文件夹失败 ");
                }
            }

            String serverPath = path + "/" + trueFileName + "." + type;
            log.debug("图片绝地路径位置为：" + path);
            try {
                file.transferTo(new File(serverPath));
            } catch (IOException e) {
                throw new UnCheckedException("上传文件异常: " + e.toString());
            }
            return serverPath;
        } else {
            throw new UserException("不是我们想要的文件类型,请按要求重新上传");
        }

    }

    public static String setImgsToServer(HttpServletRequest httpServletRequest, MultipartFile[] files, String folderName) {
        StringBuilder path = new StringBuilder();
        StringBuilder paths = new StringBuilder();
        log.info("执行图片上传");
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            String type = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase()) || "JPEG".equals(type.toUpperCase())) {
                // 项目在容器中实际发布运行的根路径
                String realPath = httpServletRequest.getSession().getServletContext().getRealPath("/");
                // 自定义的文件夹和文件名称
                String trueFolderName = IdUtils.getRandomStringId();
                String trueFileName = IdUtils.getRandomStringId();
                // 设置存放图片文件的路径
                path.append(realPath)
                        .append("uploads/")
                        .append(folderName)
                        .append("/")
                        .append(trueFolderName);
                File serverFile = new File(path.toString());
                if (!serverFile.exists()) {
                    if (serverFile.mkdirs()) {
                        throw new UnCheckedException("上传文件异常: 创建空文件夹失败 ");
                    }
                }
                path.append("/")
                        .append(".")
                        .append(type)
                        .append(trueFileName);
                try {
                    file.transferTo(new File(path.toString()));
                } catch (IOException e) {
                    throw new UnCheckedException("上传文件异常: " + e.toString());
                }
                paths.append(path)
                        .append(";");
                path.delete(0, path.length());
            } else {
                throw new UserException("不是我们想要的文件类型,请按要求重新上传");
            }

        }
        return paths.toString();
    }

}
