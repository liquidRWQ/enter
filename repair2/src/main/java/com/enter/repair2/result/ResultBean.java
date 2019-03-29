package com.enter.repair2.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @className ResultBean
 * @auther Liquid
 * @description
 * @date 2018/11/11
 */
@Data
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = -8362519075967287843L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int NORMAL_FAIL = 1;

    public static final int CHECKEDEXCEPTION_FAIL = 2;

    public static final int NO_PERMISSION = 3;

    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = CHECKEDEXCEPTION_FAIL;
    }
}
