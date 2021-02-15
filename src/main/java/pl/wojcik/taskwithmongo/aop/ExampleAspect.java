package pl.wojcik.taskwithmongo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class ExampleAspect {

    @Pointcut("execution(* pl.wojcik.taskwithmongo.bootstrap.DBInitializer.*(..))")
    private void anyDBInitializerMethod() {
    }

    @Before("anyDBInitializerMethod()")
    public void beforeAnyPublicMethod(JoinPoint joinPoint) {
        log.info("ASPECT :: before :: " + joinPoint.getSignature().getName());
    }

    @After("anyDBInitializerMethod()")
    public void afterAnyPublicMethod(JoinPoint joinPoint) {
        log.info("ASPECT :: after :: " + joinPoint.getSignature().getName());
    }

    @Around("execution(* pl.wojcik.taskwithmongo.controller.ApiController.*(..))")
    public Object aroundControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object proceed = joinPoint.proceed();
        log.info(" :: TIME :: " + (System.nanoTime() - start));
        return proceed;
    }
}
