package com.example.demo.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

import static org.assertj.core.api.Assertions.assertThat;

public class CombineSingletonAndPrototypeScopeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        bean1.count++;
        assertThat(bean1.count).isEqualTo(1);

        PrototypeBean bean2 = context.getBean(PrototypeBean.class);
        bean2.count++;
        assertThat(bean1.count).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean bean = context.getBean(ClientBean.class);
        int result = bean.logic();
        assertThat(result).isEqualTo(1);

        // ClientBean의 멤버변수인 PrototypeBean는 프로토타입 스코프와는 상관없이
        // ClientBean의 참조형 변수(포인터)이므로 동일한 인스턴스이다.
        ClientBean bean2 = context.getBean(ClientBean.class);
        int result2 = bean2.logic();
        assertThat(result2).isEqualTo(2);
    }

    @Scope("prototype")
    static class PrototypeBean{
        public int count = 0;

        @PostConstruct
        public void init(){
            System.out.println("prototype init" + this);
        }
    }

    /**
     * record는 생성자가 1개이므로 @Autowired를 생략해도 된다.
     */
    @Scope("singleton")
    record ClientBean(PrototypeBean prototypeBean) {

        public int logic() {
            return ++prototypeBean.count;
        }
    }
}
