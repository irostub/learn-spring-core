package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 빈 생명주기 콜백에 대해서
 * implements InitializingBean, DisposableBean 를 사용해서
 * 스프링에서 오버라이드 하여 생명주기를 콜백받는 방식은 옛날 방식이다.
 * 너무 스프링에 의존적이므로, 그리고 코드를 고칠 수 없는 외부 라이브러리 사용 시에 이 방법으로 생명주기를 관리할 방법이 없다.
 */
public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 : url = " + url);
        /*
        afterPropertiesSet 에서 실행합니다. 생명주기 콜백함수
        connect();
        call("초기화 연결 메세지");
        */
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        System.out.println("connect url = " + url);
    }

    public void call(String message) {
        System.out.println("call url : " + url + " message = " + message);
    }

    public void disconnect() {
        System.out.println("close : " + url);
    }

    //InitalizingBean 인터페이스로부터,
    //@Override 고전방식
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    //DisposableBean 인터페이스로부터
    //@Override 고전방식
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
