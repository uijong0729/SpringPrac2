package com.example.demo.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *  프로톼입 스코프
 */
public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        // AnnotationConfigApplicationContext의 인수로 넘겨진 클래스는
        // @ComponentScan을 따로 붙이지 않아도 빈으로 등록된다.
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean bean1 = context.getBean(PrototypeBean.class);
        PrototypeBean bean2 = context.getBean(PrototypeBean.class);
        Assertions.assertThat(bean1).isNotSameAs(bean2);

        context.close();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("init");
        }

        // 프로토타입 스코프에서는 destroy()가 호출되지 않는다.
        @PreDestroy public void destroy(){
            System.out.println("destroy");
        }
    }
}
