package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    //웹어플리케이션은 고객 요청이 많다.
    //근데 이렇게 계속 요청마다 객체를 만들어내야하는가?
    //일단 이 테스트에선 정말 요청마다 새로운 객체가 만들어지는지 눈으로 직접 확인해본다.
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //조회: 호출할 때마다 객체를 생성하는가?
        MemberService memberService1 = appConfig.memberService();
        //조회: 호출할 때마다 객체를 생성하는가?
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른가?
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void SingletonServiceTest() {
        //new SingletonService() 는 불가능. private 생성자 때문, 오직 getter로 접근가능
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        assertThat(instance1).isEqualTo(instance2); //값을 기준으로 같은지 확인
        assertThat(instance1).isSameAs(instance2); //참초를 기준으로 같은지 확인
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = annotationConfigApplicationContext.getBean(MemberService.class);
        MemberService memberService2 = annotationConfigApplicationContext.getBean(MemberService.class);

        //참조값이 다른가?
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        assertThat(memberService1).isSameAs(memberService2);
    }
}
