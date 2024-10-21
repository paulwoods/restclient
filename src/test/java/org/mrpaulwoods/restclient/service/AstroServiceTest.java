package org.mrpaulwoods.restclient.service;

import org.junit.jupiter.api.Test;
import org.mrpaulwoods.restclient.json.AstroResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AstroServiceTest {

    @Autowired
    private AstroService service;

    @Test
    void getPeopleInSpace() {
        String response = service.getPeopleInSpace();
        assertTrue(response.contains("people"));
        System.out.println(response);
    }

    @Test
    void getAstroResponseSync() {
        AstroResponse response = service.getAstroResponseSync();
        System.out.println(response);
        assertNotNull(response);
        assertEquals("success", response.message());
        assertTrue(response.number() >= 0);
        assertEquals(response.number(), response.people().size());
    }

    @Test
    void getAstroResponseAsync() {
        AstroResponse response = service.getAstroResponseAsync().block(Duration.ofSeconds(2));
        System.out.println(response);
        assertNotNull(response);
        assertEquals("success", response.message());
        assertTrue(response.number() >= 0);
        assertEquals(response.number(), response.people().size());
    }

    @Test
    void getAstroResponseAsyncStepVerifier() {
        service.getAstroResponseAsync()
                .as(StepVerifier::create)
                .assertNext(response -> {
                    System.out.println(response);
                    assertNotNull(response);
                    assertEquals("success", response.message());
                    assertTrue(response.number() >= 0);
                    assertEquals(response.number(), response.people().size());
                })
                .verifyComplete();
    }

    @Test
    void getAstroResponseFromInterface(@Autowired AstroInterface astroInterface) {
        AstroResponse response = astroInterface.getAstroResponse().block(Duration.ofSeconds(2));
        System.out.println(response);
        assertNotNull(response);
        assertEquals("success", response.message());
        assertTrue(response.number() >= 0);
        assertEquals(response.number(), response.people().size());
    }

}
