package com.example.demo.validation.annotation;

import com.example.demo.validation.exception.UnsafeSourceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Aspect
@Component
public class SafeToGoAnnotationProcessor {
    private Set<String> safeCities;
    private ExpressionParser expressionParser;
    {
        safeCities = new LinkedHashSet<>();
        safeCities.add("HCMC");
        safeCities.add("HN");
        expressionParser = new SpelExpressionParser();
    }

    @Around("@annotation(safeToGo)")
    public Object checkCity(ProceedingJoinPoint joinPoint, SafeToGo safeToGo) throws Throwable {
        //??? We have to handle expression here
        String cityExp = safeToGo.city();
        Expression expression = expressionParser.parseExpression(cityExp);
        String city = (String) expression.getValue(joinPoint.getArgs());

        if (!safeCities.contains(city)) throw new UnsafeSourceException();
        return joinPoint.proceed();
    }
}
