package com.king.controller;

import com.king.domain.SysLog;
import com.king.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date visitTime; // 访问时间
    private Class clazz;// 访问的类
    private Method method; // 访问的方法


    // 前置通知
    // 获取开始时间，执行的类是哪一个，执行的是哪一个方法
    public void doBefore(JoinPoint jp) throws NoSuchMethodException, SecurityException  {
        visitTime = new Date();     //当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass();      //具体要访问的类
        String methodName = jp.getSignature().getName();    //获取访问方法的名称
        Object[] args = jp.getArgs();   //获取访问方法的参数

        if (args == null || args.length == 0){
            method = clazz.getMethod(methodName);   //只能获取无参的方法
        }else {
            Class[] classArgs = new Class[args.length];
            // 有参数，就将args中所有元素遍历，获取对应的Class,装入到一个Class[]
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName, classArgs);    //获取有参的方法
        }
    }

    // 后置通知
    public void doAfter(JoinPoint jp){
        long executionTime = new Date().getTime() - visitTime.getTime();     //访问时长

        String url = "";

        // 获取URL
        if (clazz != null && method != null && clazz != LogAop.class){
            // 获取类上的@RequestMapping
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (clazzAnnotation != null){
                String[] classValue = clazzAnnotation.value();

                // 获取方法上的@RequestMapping
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null){
                    String[] methodValue = methodAnnotation.value();

                    url = classValue[0] + methodValue[0];

                    // 获取 ip 地址
                    // 在web.xml中添加<listener>RequestContextListener
                    String ip = request.getRemoteAddr();

                    // 获取当前操作的用户
                    // 可以通过securityContext获取，也可以从request.getSession中获取
                    SecurityContext context = SecurityContextHolder.getContext(); // 从上下文获取当前登录的用户
                    // request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    // 将日志信息封装到SysLog对象
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(executionTime); // 执行时长
                    sysLog.setUrl(url);
                    sysLog.setIp(ip);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime); // 开始访问的时间
                    sysLog.setMethod("[方法名]" + clazz.getName() + "[.]" + method.getName());

                    // 调用service完成操作
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}