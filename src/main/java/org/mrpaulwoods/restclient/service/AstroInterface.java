package org.mrpaulwoods.restclient.service;

import org.mrpaulwoods.restclient.json.AstroResponse;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface AstroInterface {
    @GetExchange("/astros.json")
    Mono<AstroResponse> getAstroResponse();
}
