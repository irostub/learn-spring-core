package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA bean1 = ac.getBean(BeanA.class);
        assertThat(bean1).isNotNull();
        assertThrows(NoSuchBeanDefinitionException.class, () -> {
            BeanB bean2 = ac.getBean(BeanB.class);
        });
    }

    @Configuration
    @ComponentScan(includeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = MyIncludeComponent.class)
            ,excludeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{
    }

}
