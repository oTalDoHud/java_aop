package com.java.aop.aspect;

import com.java.aop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Aspect
@Component
@AllArgsConstructor
public class ProcessMonitor {

    private static final Logger LOG = Logger.getLogger(ProcessMonitor.class);

    private final UserRepository userRepository;

    @Pointcut("@annotation(monitor)")
    public void callAt(Monitor monitor) {
    }

    @Around("callAt(monitor)")
    public Object monitoringMethod(ProceedingJoinPoint pjp, Monitor monitor) throws Throwable {

        var start = System.currentTimeMillis();
        var retorno = pjp.proceed();
        var totalTime = System.currentTimeMillis() - start;

        if (monitor.isMonitoring()) {
            MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            Method method = methodSignature.getMethod();

            var object = new LinkedHashMap<>();
            object.put("path", methodSignature.getDeclaringTypeName());
            object.put("class", methodSignature.getDeclaringType().getSimpleName());
            object.put("method", methodSignature.getMethod().getName());
//            object.put("args", new ArrayList<>());
            var args = new ArrayList<>();

            if (method.getParameterCount() > 0) {
                Arrays.stream(method.getParameters()).iterator().forEachRemaining(parameter -> {
                    var parameterObject = new LinkedHashMap<>();
                    parameterObject.put("type", parameter.getType());
                    parameterObject.put("value", pjp.getArgs()[Arrays.asList(method.getParameters()).indexOf(parameter)]);
                    ((List) args).add(parameterObject);
                });
            }

            LOG.info("Method execution time: " + totalTime + "ms");
            LOG.info("Class and method: " + object);
            LOG.info("Arguments: " + args);
        }

        return retorno;
    }
}
