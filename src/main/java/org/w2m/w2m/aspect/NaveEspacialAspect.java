package org.w2m.w2m.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NaveEspacialAspect {
	private static final Logger logger = LoggerFactory.getLogger(NaveEspacialAspect.class);
	
	@Before("execution(* org.w2m.w2m.service.NaveEspacialService.consultarNaveId(..)) && args(id,..)")
    public void logNegativoId(JoinPoint joinPoint, Long id) {
        if (id < 0) {
            logger.warn("Se solicitó una nave con un ID negativo: " + id);
        }
    }
}
