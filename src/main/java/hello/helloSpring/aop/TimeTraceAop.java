package hello.helloSpring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * AOP
 * 회원가입, 회원 조회 등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다.
 * 시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
 * 핵심 관심 사항을 깔끔하게 유지할 수 있다.
 * 변경이 필요하면 이 로직만 변경하면 된다.
 *
 */
@Aspect
@Component
public class TimeTraceAop {

    // @Around = 타겟팅, hello.helloSpring 하위패키지에 다 적용해
    @Around("execution(* hello.helloSpring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
