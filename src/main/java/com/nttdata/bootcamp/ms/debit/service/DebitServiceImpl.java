package com.nttdata.bootcamp.ms.debit.service;

import com.nttdata.bootcamp.ms.debit.entity.Debit;
import com.nttdata.bootcamp.ms.debit.repository.DebitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DebitServiceImpl implements DebitService{

    @Autowired
    private DebitRepository debitRepository;

    @Override
    public Flux<Debit> getAll() {
        return debitRepository.findAll();
    }

    @Override
    public Mono<Debit> geDebitById(Integer debitId) {
        return debitRepository.findById(debitId);
    }

    @Override
    public Mono<Debit> saveDebit(Debit debit) {
        return debitRepository.save(debit);
    }

    @Override
    public Mono<Debit> updateDebit(Debit debit) {
        return debitRepository.findById(debit.getId())
                .flatMap(newDebit -> {
                    newDebit.setId(debit.getId());
                    newDebit.setAvailableBalance(debit.getAvailableBalance());
                    newDebit.setCardNumber(debit.getCardNumber());
                    newDebit.setExpiryDate(debit.getExpiryDate());
                    newDebit.setAccountId(debit.getAccountId());
                    return debitRepository.save(newDebit);
        });
    }

    @Override
    public Mono<Debit> deleteDebit(Integer debitId) {
        return debitRepository.findById(debitId)
                .flatMap(debit -> debitRepository.delete(debit)
                        .then(Mono.just(debit)));
    }
    
    @Override
    public Mono<Debit> geDebitByIdCustomer(Integer customerId){
    	Debit deb = new Debit(null,null,null,null,null,null);
        return debitRepository.findAll()
                .filter(debit -> debit.getCustomerId() ==  customerId).next();
    }
}
