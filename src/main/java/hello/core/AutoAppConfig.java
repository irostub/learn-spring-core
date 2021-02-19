package hello.core;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(
        //기존 AppConfig 또한 하나의 component 이기 때문에 AutoAppConfig 와 Configuration 이 겹치므로 충돌이 일어난다.
        //이것을 해결하기 위해서 ComponentScan 의 Filter 를 사용하여 AppConfig 는 무시하도록 한다.
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
