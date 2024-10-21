package org.mrpaulwoods.restclient.service;

import org.mrpaulwoods.restclient.json.AstroResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * The `AstroService` class provides services to handle astronomical data.
 *
 * <p>This class uses `RestTemplate` from Spring framework to make HTTP requests.
 * It is annotated with `@Service` which indicates that it is a Spring service component.
 * The `AstroService` class encapsulates the logic to interact with an external astronomy API.
 * </p>
 *
 * <p> Example usage:
 * </p>
 * <pre>{@code
 * @Service
 * public class AstroService {
 *
 *     private final RestTemplate restTemplate;
 *
 *     public AstroService(RestTemplateBuilder restTemplateBuilder) {
 *         this.restTemplate = restTemplateBuilder.build();
 *     }
 *
 *     // Method implementations
 * }
 * }</pre>
 *
 * <p> Dependencies injected:
 * <ul>
 *   <li>{@link RestTemplateBuilder} - A builder for the RestTemplate instance.</li>
 * </ul>
 * </p>
 *
 * @see RestTemplate
 * @see RestTemplateBuilder
 */
@Service
public class AstroService {

    private final RestTemplate template;
    private final WebClient client;

    /**
     * Constructs an instance of `AstroService` with the given `RestTemplateBuilder`.
     *
     * @param restTemplateBuilder a builder for `RestTemplate`, used to create an instance of `RestTemplate`.
     */
    public AstroService(RestTemplateBuilder restTemplateBuilder) {
        this.template = restTemplateBuilder.build();
        this.client = WebClient.create("http://api.open-notify.org");
    }

    public String getPeopleInSpace() {
        return template.getForObject("http://api.open-notify.org/astros.json",
                String.class);
    }

    public AstroResponse getAstroResponseSync() {
        return template.getForObject("http://api.open-notify.org/astros.json",
                AstroResponse.class);
    }

    public Mono<AstroResponse> getAstroResponseAsync() {
        return client.get()
                .uri("/astros.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AstroResponse.class)
                .log();
    }

}
