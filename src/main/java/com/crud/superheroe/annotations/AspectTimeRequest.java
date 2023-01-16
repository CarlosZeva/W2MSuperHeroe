package com.crud.superheroe.annotations;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AspectTimeRequest {

    @Around("@annotation(com.crud.superheroe.annotations.TimeRequest)")
    public Object traceTimeExecution(ProceedingJoinPoint joint) throws Throwable {

        long inicio = System.currentTimeMillis();
        Object resultado = null;
        Throwable throwable = null;
        try {
            resultado = joint.proceed();

        } catch ( Throwable t ) {
            throwable = t;
        }
        Object ejecutar = joint.proceed();
        long tiempoEjecucion = System.currentTimeMillis() - inicio;
        log.info("Se ejecutó " + joint.getSignature() + " en " + tiempoEjecucion +"ms");
        if ( throwable == null ) return resultado;
        throw throwable;

    }
}
