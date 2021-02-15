package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberServiceImpl bean1 = ac.getBean(MemberServiceImpl.class);
        OrderServiceImpl bean2 = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepositoryBean = ac.getBean(MemberRepository.class);

        MemberRepository memberRepository1 = bean1.getMemberRepository();
        MemberRepository memberRepository2 = bean2.getMemberRepository();

        assertThat(memberRepository1).isSameAs(memberRepositoryBean);
        assertThat(memberRepository2).isSameAs(memberRepositoryBean);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        //찍어보면 알겠지만 AppConfig를 상속받은 가상클래스를 만들어서 작동한다.
        //가상클래스에서는 스프링 컨테이너에 이미 있는 빈은 그대로 리턴하고 없으면 기존 AppConfig 클래스의 로직을 실행하여 새로 만든다.
        //이로써 싱글톤을 보장해주는 것이다.
        System.out.println("bean = " + bean.getClass());
    }
}
