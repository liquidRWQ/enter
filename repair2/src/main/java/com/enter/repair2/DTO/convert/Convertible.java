package com.enter.repair2.DTO.convert;

/**
 * @className DTOConvert
 * @auther Liquid
 * @description
 * @date 2018/12/22
 */
public interface Convertible<O, T> {
    O convertToDO(T t);

    T convertToDTO(O o);
}
