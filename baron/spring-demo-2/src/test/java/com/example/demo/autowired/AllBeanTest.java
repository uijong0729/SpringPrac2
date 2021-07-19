package com.example.demo.autowired;

import com.example.demo.AutoAppConfig;
import com.example.demo.discount.DiscountPolicy;
import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {

    @Test
    void findAllBean() {
        // 둘 다 등록
        // AutoAppConfig와,  DiscountService안의 모든 빈이 등록된다.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                AutoAppConfig.class, DiscountService.class);

        // 이후 비즈니스 로직
        DiscountService bean = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int fixDiscountPrice = bean.discount(member, 10000, "fixDiscountPolicy");

        assertThat(bean).isInstanceOf(DiscountService.class);
        assertThat(fixDiscountPrice).isEqualTo(1000);

        int rateDiscountPrice = bean.discount(member, 20000, "rateDiscountPolicy");
        assertThat(bean).isInstanceOf(DiscountService.class);
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            // AutoAppConfig와,  DiscountService안의 모든 빈에 대하여, 빈의 이름을 키로 Map에 등록된다.
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println(policyMap);
            System.out.println(policies);
        }

        /**+
         *
         * @param member 멤버
         * @param price 가격
         * @param beanName 스프링 빈의 이름
         * @return 할인가격
         */
        public int discount(Member member, int price, String beanName) {
            DiscountPolicy policy = policyMap.get(beanName);
            return policy.discount(member, price);
        }
    }

}
