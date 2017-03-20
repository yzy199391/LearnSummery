package runit;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * Created by hasee on 2017/3/9.
 */
public class RunAfterReturningAdvice implements AfterReturningAdvice{

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("打印完成_在advice执行之后！");
    }
}
