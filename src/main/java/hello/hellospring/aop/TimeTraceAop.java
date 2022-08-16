package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // (7-2) 붙여줘야 AOP로 사용 가능하다.
public class TimeTraceAop {

    // (7-2) AOP를 적용할 대상을 설정할 수 있다. ..*(..)은 대상 패키지 하위의 모든 클래스, 매개변수를 가리킴.
    // 근데 TimeTraceAop 클래스를 SpringConfig를 통해 스프링 빈으로 등록하면 순환 참조? 순환 의존성? 오류가 발생한다. 그 이유는 다음과 같다.
    // 기존의 @Around("execution(* hello.hellospring..*(..))")는 해당 패키지 하위의 모든 메서드를 AOP의 적용대상으로 지정하므로 SpringConfig의 timeTraceAop() 역시 포함되어 있다.
    // 즉 @Around의 경로에 SpringConfig가 포함되어 있어 SpringConfig의 timeTraceAop()가 두번 스캔된 것.
    // 따라서 SpringConfig 파일을 통해 빈을 등록하려면 기존 @Around의 경로 지정에서 SpringConfig의 경로를 빼줘야 한다.

    // @Around("execution(* hello.hellospring..*(..))")
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); // (7-2) 로직이 실행되는 메서드의 이름을 출력한다.
        try {
            return joinPoint.proceed(); // (7-2) 로직을 적용할 대상 메서드를 실행한다. (예제의 join(), findMembers() 등)
        } finally {
            long end = System.currentTimeMillis();
            long timeMs = end - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
