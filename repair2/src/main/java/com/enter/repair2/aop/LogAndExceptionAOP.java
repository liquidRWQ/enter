package com.enter.repair2.aop;

import com.enter.repair2.exception.CheckedException;
import com.enter.repair2.exception.UnCheckedException;
import com.enter.repair2.result.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @className ControllerAOP
 * @auther Liquid
 * @description
 * @date 2018/11/11
 */
@Order(1)
@Component
@Aspect
@Slf4j
public class LogAndExceptionAOP {

    @Pointcut("execution(public com.enter.repair2.result.ResultBean *(..))")
    public void resultBean() {
    }

    @Around("resultBean()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;
        try {
            result = (ResultBean<?>) pjp.proceed();
            log.info("[当前线程: {}]   [切点: {}]  [方法运行时间: {}ms]",Thread.currentThread().getName(),pjp.getSignature() ,(System.currentTimeMillis() - startTime));
        } catch (Throwable e) {
            result = handlerException(pjp, e);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();
        // 已知异常
        if (e instanceof UnCheckedException){
            result.setMsg(e.getLocalizedMessage());
            result.setCode(ResultBean.NORMAL_FAIL);
        } else if (e instanceof CheckedException){
            log.warn("发生检查异常！{ 当前线程: {}] [切点: {}][异常信息: {} }",Thread.currentThread().getName(),pjp.getSignature() ,e.toString());
            result.setMsg(e.getLocalizedMessage());
            result.setCode(ResultBean.CHECKEDEXCEPTION_FAIL);
        }
        else {
            log.error("发生未知异常！{ 当前线程: {}]  [切点: {}]  [异常信息: {}-{} }",Thread.currentThread().getName(),pjp.getSignature() , e.toString(),e.getLocalizedMessage());
            // 未知的异常，应该格外注意，可以发送邮件通知等
            result.setMsg(e.toString());
            result.setCode(ResultBean.CHECKEDEXCEPTION_FAIL);
        }

        return result;
    }
}
