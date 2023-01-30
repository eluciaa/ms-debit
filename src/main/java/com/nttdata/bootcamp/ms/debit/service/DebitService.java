package com.nttdata.bootcamp.ms.debit.service;

import com.nttdata.bootcamp.ms.debit.entity.Debit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DebitService {

    Flux<Debit> getAll();
    Mono<Debit> geDebitById(Integer debitId);
    Mono<Debit> saveDebit(Debit debit);
    Mono<Debit> updateDebit(Debit debit);
    Mono<Debit> deleteDebit(Integer debitId);
}
