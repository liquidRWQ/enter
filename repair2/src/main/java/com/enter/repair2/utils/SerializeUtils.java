package com.enter.repair2.utils;

import com.enter.repair2.exception.CheckedException;

import java.io.*;

/**
 * @author Liquid
 * @类名： SerializeUtils
 * @描述： java对象序列化工具类
 * @date 2018/12/28
 */
public class SerializeUtils {

    /**
     * @param object 要序列化的java对象
     * @return byte[] 序列化后的字节数组
     * @throws CheckedException 检查异常
     * @author Liquid
     * @描述： 序列化java对象
     * @date 2018/12/28
     */
    public static byte[] serialize(Object object) throws CheckedException {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            // 序列化
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
        } catch (IOException e) {
            throw new CheckedException("对象序列化失败" + e.toString());
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                throw new CheckedException("关闭流失败" + e.toString());
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    throw new CheckedException("关闭流失败" + e.toString());
                }
            }

        }
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return bytes;
    }

    /**
     * @param bytes 反序列化的字节数组
     * @return java.lang.Object 反序列化后的java对象
     * @throws CheckedException 检查异常
     * @author Liquid
     * @描述： java对象的反序列化
     * @date 2018/12/28
     */
    public static Object deserialize(byte[] bytes) throws CheckedException {

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Object object = null;
        ObjectInputStream objectInputStream = null;
        try {
            // 反序列化
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new CheckedException("对象反序列化失败" + e.toString());
        } finally {
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {
                throw new CheckedException("关闭流失败" + e.toString());
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    throw new CheckedException("关闭流失败" + e.toString());
                }
            }
        }
        return object;
    }
}
