package com.nttdata.bootcamp.ms.debit.repository;

import com.nttdata.bootcamp.ms.debit.entity.Debit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitRepository extends ReactiveMongoRepository<Debit,Integer> {
}
