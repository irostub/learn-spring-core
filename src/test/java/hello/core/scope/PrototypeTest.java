package hello.core.scope;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {


    @Test
    @DisplayName("프로토타입 빈은 항상 다른 객체를 반환하고 소멸주기도 없는가?")
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        System.out.println("bean1 = " + bean1);
        System.out.println("bean2 = " + bean2);
        assertThat(bean1).isNotSameAs(bean2);
        bean1.close();
        bean2.close();
    }

    @Test
    @DisplayName("싱글톤 클라이언트에 프로토타입 빈을 사용")
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonClient.class, PrototypeBean.class);

        SingletonClient bean1 = ac.getBean(SingletonClient.class);
        SingletonClient bean2 = ac.getBean(SingletonClient.class);

        assertThat(bean1).isSameAs(bean2);
        assertThat(bean1.prototypeBeanProvider.get()).isNotSameAs(bean2.prototypeBeanProvider.get());
    }

    @RequiredArgsConstructor
    static class SingletonClient {
        @Autowired
        //private ObjectProvider<PrototypeBean> prototypeBean; ObjectProvider 를 쓸 경우 스프링에 의존하는 대신 기능이 더 많음. getObject() 로 프토로타입 빈 꺼내온다. DL 이라고 하고 dependency lookup 이다.
        private Provider<PrototypeBean> prototypeBeanProvider; //javax 에서 java 표준임을 확인할 수 있고 java 표준도 Provider 를 지원한다는 것을 알 수 있다. 진짜 get() 기능만 있는 깔끔함.

        int logic() {
            int count = prototypeBeanProvider.get().increaseCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count;

        int increaseCount() {
            count += 1;
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("prototype.init" + this);
        }

        @PreDestroy
        public void close() {
            System.out.println("prototype.close");
        }
    }
}
