package hello.core.lifecycle;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanLifeCycleTest {

    @Test
    @DisplayName("라이프사이클 테스트")
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient bean = ac.getBean(NetworkClient.class);
        ac.close(); //스프링 컨테이너 종료
    }

    private static class LifeCycleConfig {

        //생명주기 방식 두번째 어노테이션 프로퍼티로 initMethod 와 destroyMethod 를 사용하며 값은 해당 메서드 이름을 문자열로 넣어준다.
        @Bean
        //(initMethod = "init", destroyMethod = "close") 또 버려짐...세번째 방법 @PostConstruct @PreDestroy 어노테이션을 사용하기 때문
        //세번째 방법은 편하고 좋고 표준인데, 외부 라이브러리엔 붙일 수 없으므로 외부 라이브러리의 생성과 소멸을 적용하려면 두번째 방법을 쓰면된다.(initMethod 와 destroyMethod)
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("hello network client setting");
            return networkClient;
        }
    }
}
