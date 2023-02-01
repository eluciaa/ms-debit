package com.nttdata.bootcamp.ms.debit.controller;

import com.nttdata.bootcamp.ms.debit.entity.Debit;
import com.nttdata.bootcamp.ms.debit.service.DebitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/debit")
public class DebitController {

    @Autowired
    private DebitService debitService;

    /**
     *
     * Obtener la información de todas las tarjetas debito
     * @return
     */
    @GetMapping
    public Flux<Debit> getAll(){
        return debitService.getAll();
    }

    /**
     *
     * Obtene tarjetas debito por Id
     * @param id
     * @return
     */
    @GetMapping ("/{id}")
    public Mono<ResponseEntity<Debit>> getDebitById(@PathVariable Integer id){
        return debitService.geDebitById(id).map(response -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(response))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    /**
     * Registrar debit card
     * @param debit
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Debit> saveCDebit(@RequestBody Debit debit){
        return debitService.saveDebit(debit);
    }

    @PutMapping
    public Mono<Debit> updateDebit(@RequestBody Debit debit){
        return debitService.updateDebit(debit);
    }

    @DeleteMapping("/{id}")
    public Mono<Debit> deleteCredit(@PathVariable Integer id){
        return debitService.deleteDebit(id);
    }
    
    @GetMapping ("/customer/{id}")
    public Mono<Debit> getDebitByIdConsumer(@PathVariable Integer id){
        return debitService.geDebitByIdCustomer(id);
    }

}
