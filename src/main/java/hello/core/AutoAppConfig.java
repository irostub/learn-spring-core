package hello.core;


import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(
        //기존 AppConfig 또한 하나의 component 이기 때문에 AutoAppConfig 와 Configuration 이 겹치므로 충돌이 일어난다.
        //이것을 해결하기 위해서 ComponentScan 의 Filter 를 사용하여 AppConfig 는 무시하도록 한다.
        excludeFilters = @ComponentScan.Filter(type= FilterType.ANNOTATION, classes = Configuration.class)
        /*,
        //해당 패키지에서부터 시작하여 컴포넌트를 스캔
        basePackages="hello.core.member"
        ,
        //여기서부터 찾아가기 시작
        basePackageClasses = AutoAppConfig.class
        */
        //아무것도 설정하지 않을 경우 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.
        //관례로 볼 때 @SpringBootApplication 이 프로그램의 시작점이며 root 에 보편적으로 위치하는데,
        //이로 볼 때 configuration 은 그와 같은 디렉터리 위치에 두는게 권장된다
)
public class AutoAppConfig {

    //01:31:27.304 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Overriding bean definition for bean 'memoryMemberRepository' with a different definition: replacing [Generic bean: class [hello.core.member.MemoryMemberRepository]; scope=singleton; abstract=false; lazyInit=null; autowireMode=0; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=null; factoryMethodName=null; initMethodName=null; destroyMethodName=null; defined in file [/Users/sdm/Documents/github/learn-spring-core/out/production/classes/hello/core/member/MemoryMemberRepository.class]] with [Root bean: class [null]; scope=; abstract=false; lazyInit=null; autowireMode=3; dependencyCheck=0; autowireCandidate=true; primary=false; factoryBeanName=autoAppConfig; factoryMethodName=memberRepository; initMethodName=null; destroyMethodName=(inferred); defined in hello.core.AutoAppConfig]
    //위와 같은 로그가 뜨는데 자동 빈 등록과 현재 @Bean 으로 수동 등록 하였을 때 수동등록 빈을 덮어씌워버린 것
    //이는 의도하지 않은 경우 큰 버그로 돌아올 수 있음. 때문에 springboot 로 실행 시엔 원초부터 오류가 나버리게함.
    /*@Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
