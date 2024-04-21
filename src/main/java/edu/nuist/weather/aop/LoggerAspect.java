package edu.nuist.weather.aop;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import edu.nuist.weather.annotation.LogAnnotation;
import edu.nuist.weather.dto.RequestLogDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@Aspect
public class LoggerAspect {

    @Resource
    private HttpServletRequest request;

    private final String REQUEST_ID_KEY = "request_unique_id";

    // 定义切点, 注解加在哪里哪里就是切点
    @Pointcut("@annotation(edu.nuist.weather.annotation.LogAnnotation)")
    public void declarePointCut() {}

    // 前置通知
    @Before("declarePointCut()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        printRequestLog(methodSignature, joinPoint.getArgs());
    }

    // 异常通知
    @AfterThrowing(value = "declarePointCut()", throwing = "ex")
    public void doAfterThrowingAdvice(Throwable ex) {
        printExceptionLog(ex);
    }

    // 打印请求日志
    public void printRequestLog(MethodSignature methodSignature, Object[] argObs) {
        Method method = methodSignature.getMethod();
        // 得到注解类
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        RequestLogDTO logVo = new RequestLogDTO();

        // 设置请求唯一ID
        logVo.setId(IdUtil.fastUUID());
        request.setAttribute(REQUEST_ID_KEY, logVo.getId());

        logVo.setOperator(logAnnotation.operator());
        logVo.setUri(request.getRequestURI());
        logVo.setName(request.getMethod());
        List<Object> args = new ArrayList<>();

        // 过滤掉一些不能转为json字符串的参数
        Arrays.stream(argObs).forEach(e -> {
            if (e instanceof MultipartFile || e instanceof HttpServletRequest
                    || e instanceof HttpServletResponse || e instanceof BindingResult) {
                return;
            }

            args.add(e);
        });

        logVo.setArgs(args.toArray());
        logVo.setPath(methodSignature.getDeclaringTypeName() + "." + method.getName());
        logVo.setReferer(request.getHeader("referer"));
        logVo.setRemoteAddr(request.getRemoteAddr());
        logVo.setUserAgent(request.getHeader("user-agent"));

        log.info(JSON.toJSONString(logVo));
    }

    // 打印异常日志
    public void printExceptionLog(Throwable ex) {
        JSONObject logVo = new JSONObject();
        logVo.put("id", request.getAttribute(REQUEST_ID_KEY));
        log.error(JSON.toJSONString(logVo), ex);
    }

}
