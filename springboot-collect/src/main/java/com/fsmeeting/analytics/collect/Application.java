package com.fsmeeting.analytics.collect;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
@MapperScan("com.fsmeeting.analytics.collect.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public EmbeddedServletContainerFactory tomcatEmbeddedServletContainerFactory() throws IOException {
        TomcatEmbeddedServletContainerFactory tomcatFactory = new TomcatEmbeddedServletContainerFactory();
        tomcatFactory.setPort(8081);
        tomcatFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
            @Override
            public void customize(Connector connector) {
                connector.setPort(80);
                Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
                //设置最大连接数
                protocol.setMaxConnections(10000);
                //设置最大线程数
                protocol.setMaxThreads(1);
                protocol.setMinSpareThreads(1);
                protocol.setConnectionTimeout(5000);
            }
        });
        return tomcatFactory;
    }

}
