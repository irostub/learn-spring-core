package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    @DisplayName("Autowired Null 처리 옵션 테스트")
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        //required false 로 필수가 아님을 설정(인자가 없으면 호출 자체가 안됨)
        @Autowired(required = false)
        public void setBean1(Member m) {
            System.out.println("m = " + m);
        }

        //널을 허용
        @Autowired
        public void setBean2(@Nullable Member m) {
            System.out.println("m = " + m);
        }

        //Optional 은 java8 부터 지원하는 것으로 null 일 수 있는 필드를 감싸도록 적용가능
        @Autowired
        public void setBean3(Optional<Member> m) {
            System.out.println("m = " + m);
        }
    }
}
