package com.example.demo.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeScopeWithSingletonScopeTest {
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

        ClientBean bean2 = context.getBean(ClientBean.class);
        int result2 = bean2.logic();
        // ObjectProvider 를 통해 주입받은 빈은 스코프가 prototype 인 경우 새로 생성해서 주입받는다.
        assertThat(result2).isEqualTo(1);
    }

    @Scope("prototype")
    static class PrototypeBean{
        public int count = 0;
        @PostConstruct
        public void init(){
            System.out.println("prototype init" + this);
        }
    }

    @Scope("singleton")
    static class ClientBean {
        @Autowired
        private Provider<PrototypeBean> prototypeBeanObjectProvider;

        public int logic() {
            PrototypeBean object = prototypeBeanObjectProvider.get();
            return ++object.count;
        }
    }
}
