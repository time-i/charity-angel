//package com.whu.charityangel.common.aspect;
//
//
//import com.whu.charityangel.common.ResponseMsg;
//import com.whu.charityangel.common.log.SalixLog;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//
//
//@Component
//@Aspect
//public class AppLogAspect {
//    private Logger logger = LogManager.getLogger(AppLogAspect.class);
//    // 保证每个线程都有一个单独的实例
//    private ThreadLocal<Long> timeThreadLocal = new ThreadLocal<>();
//    // 保存日志句柄
//    private ThreadLocal<SalixLog> salixLogThreadLocal = new ThreadLocal<>();
//
//    @Pointcut("execution(* com.whu.charityangel.controller.*.*(..))")
//    public void pointcut() {
//    }
//
//    @Before("pointcut()")
//    public void doBefore(JoinPoint joinPoint) {
//        timeThreadLocal.set(System.currentTimeMillis());
//        ServletRequestAttributes attributes = (ServletRequestAttributes)
//                RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//
//
//        // 打印请求相关参数
//        logger.error("========================================== Start ==========================================");
//        // 打印请求 url
//        logger.error("URL            : {}", request.getRequestURL().toString());
//        // 打印 Http method
//        logger.error("HTTP Method    : {}", request.getMethod());
//        // 打印调用 controller 的全路径以及执行方法
//        logger.error("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
//        // 打印请求的 IP
//        logger.error("IP             : {}", request.getRemoteAddr());
//        // 打印请求入参
////        logger.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));
//
//
//        Class clz = joinPoint.getSignature().getDeclaringType();
//        SalixLog salixLog = null;
//        try {
//            Object obj = clz.newInstance();
//            Method method = obj.getClass().getMethod("initSalixLog");
//            salixLog = (SalixLog) method.invoke(obj);
//        } catch (Exception e) {
//            logger.error("initSalixLog error:", e);
//        }
//        //记录请求的内容
//        Object[] objects = joinPoint.getArgs();
//        Object param = new Object();
//        if(objects.length > 0) {
//            param = objects[0];
//        }
//
//        if(salixLog != null) {
//            salixLogThreadLocal.set(salixLog);
//            salixLog.add("url", request.getRequestURI()).
//                    add("method", request.getMethod()).
//                    add("param", param.toString());
//            logger.info(salixLog.toString());
//        } else {
//            logger.info("url="+request.getRequestURI()
//                    + "||method=" + request.getMethod()
//                    + "||param=" + param.toString());
//        }
//    }
//
//    @AfterThrowing(pointcut = "pointcut()", throwing="ex")
//    public void doAfterThrowing(Throwable ex) {
//        SalixLog salixLog = salixLogThreadLocal.get();
//        if(salixLog != null) {
//            salixLog.add("exception", ex.getMessage())
//                    .add("cost", System.currentTimeMillis() - timeThreadLocal.get());
//            logger.warn(salixLog.toString(), ex);
//        } else {
//            logger.warn("ret="+ ex.getStackTrace().toString() +"||"
//                    + "cost=" + (System.currentTimeMillis() - timeThreadLocal.get()));
//        }
//        salixLogThreadLocal.remove();
//    }
//
//    @AfterReturning(returning = "responseMsg", pointcut = "pointcut()")
//    public void doAfterReturning(ResponseMsg responseMsg) {
//        SalixLog salixLog = salixLogThreadLocal.get();
//
//        if(salixLog != null) {
//            salixLog.add("ret", responseMsg.toString())
//                    .add("cost", System.currentTimeMillis() - timeThreadLocal.get());
//            logger.info(salixLog.toString());
//        } else {
//            logger.info("ret="+ responseMsg.toString() +"||"
//                    + "cost=" + (System.currentTimeMillis() - timeThreadLocal.get()));
//        }
//        salixLogThreadLocal.remove();
//    }
//}
