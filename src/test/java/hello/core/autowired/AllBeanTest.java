package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    @DisplayName("같은 타입의 빈이 필요할 때, 고객이 할인정책을 선택")
    void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIPS);
        int discountPrice1 = discountService.discount(member, 10000, "fixDiscountPolicy");
        assertThat(discountPrice1).isEqualTo(1000);
        int discountPrice2 = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(discountPrice2).isEqualTo(2000);

    }

    @RequiredArgsConstructor
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;


        public int discount(Member member, int i, String discountPolicyCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountPolicyCode);
            return discountPolicy.discount(member, i);
        }
    }
}

