package com.nttdata.bootcamp.ms.debit.controller;

import com.nttdata.bootcamp.ms.debit.entity.Debit;
import com.nttdata.bootcamp.ms.debit.service.DebitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@WebFluxTest (DebitController.class)
public class DebitControllerTest {

    @MockBean
    private DebitService debitService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void getAll(){

        List<Integer> lists = new ArrayList<Integer>() {{ add(3); add(1); add(4); }};

        Flux<Debit> debitFlux = Flux.just(new Debit(1, "3403-3904-3535-3775", "30/08/2025", 150000F,
                lists, 1), new Debit(2, "3403-3904-3535-3776", "30/08/2025", 150000F,
                lists, 2));

        when(debitService.getAll()).thenReturn(debitFlux);

        Flux<Debit> responseBody = webTestClient.get()
                .uri("/debit")
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Debit.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new Debit(1, "3403-3904-3535-3775", "30/08/2025", 150000F,
                        lists, 1))
                .expectNext(new Debit(2, "3403-3904-3535-3776", "30/08/2025", 150000F,
                        lists, 2))
                .verifyComplete();
    }

    @Test
    void getDebitById(){

        List<Integer> lists = new ArrayList<Integer>() {{ add(3); add(1); add(4); }};
        Debit debit = new Debit(1, "3403-3904-3535-3775", "30/08/2025", 150000F,
                lists, 1);

        Mono<Debit> debitMono = Mono.just(new Debit(1, "3403-3904-3535-3775", "30/08/2025", 150000F,
                lists, 1));

        when(debitService.geDebitById(debit.getId())).thenReturn(debitMono);

        webTestClient.get()
                .uri("/debit/" + debit.getId())
                .accept(MediaType.APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Debit.class)
                .getResponseBody();

        StepVerifier.create(debitMono)
                .expectNext(debit)
                .verifyComplete();

    }

    @Test
    void saveDebit(){

        List<Integer> lists = new ArrayList<Integer>() {{ add(3); add(1); add(4); }};
        Debit debit = new Debit(1, "3403-3904-3535-3775", "30/08/2025", 150000F,
                lists, 1);

        Mono<Debit> debitMono = Mono.just(new Debit(1, "3403-3904-3535-3775", "30/08/2025", 150000F,
                lists, 1));

        when(debitService.saveDebit(debit)).thenReturn(debitMono);

        webTestClient.post()
                .uri("/debit")
                .accept(MediaType.APPLICATION_NDJSON)
                .body(debitMono, Debit.class)
                .exchange()
                .expectStatus().isCreated();

        StepVerifier.create(debitMono)
                .expectNext(debit)
                .verifyComplete();

    }

    @Test
    void updateDebit(){

        List<Integer> lists = new ArrayList<Integer>() {{ add(3); add(1); add(4); }};
        Debit debit = new Debit(1, "3403-3904-3535-3775", "30/08/2025", 150000F,
                lists, 1);

        Mono<Debit> debitMono = Mono.just(new Debit(1, "3403-3904-3535-3775", "30/08/2025", 150000F,
                lists, 1));

        when(debitService.updateDebit(debit)).thenReturn(debitMono);

        webTestClient.put()
                .uri("/debit")
                .accept(MediaType.APPLICATION_NDJSON)
                .body(debitMono, Debit.class)
                .exchange()
                .expectStatus().isOk();

        StepVerifier.create(debitMono)
                .expectNext(debit)
                .verifyComplete();

    }

    @Test
    void deleteDebit(){
        given(debitService.deleteDebit(any())).willReturn(Mono.empty());
        webTestClient.delete()
                .uri("/debit/1")
                .exchange()
                .expectStatus().isOk();
    }
}
