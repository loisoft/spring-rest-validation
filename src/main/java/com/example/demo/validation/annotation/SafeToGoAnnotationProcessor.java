package com.example.demo.validation.annotation;

import com.example.demo.validation.exception.UnsafeSourceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Aspect
@Component
public class SafeToGoAnnotationProcessor {
    private Set<String> safeCities;

    {
        safeCities = new LinkedHashSet<>();
        safeCities.add("HCM");
        safeCities.add("HN");
    }

    @Around("@annotation(safeToGo)")
    public Object checkCity(ProceedingJoinPoint joinPoint, SafeToGo safeToGo) throws Throwable {
        String city = safeToGo.city();
        if (!safeCities.contains(city)) throw new UnsafeSourceException();
        return joinPoint.proceed();
    }
}
