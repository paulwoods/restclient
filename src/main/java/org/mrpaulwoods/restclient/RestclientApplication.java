package org.mrpaulwoods.restclient;

import org.mrpaulwoods.restclient.service.AstroInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class RestclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestclientApplication.class, args);
    }

    @Bean
    public AstroInterface astroInterface() {
        WebClient webClient = WebClient.create("http://api.open-notify.org");
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(AstroInterface.class);
    }
}

