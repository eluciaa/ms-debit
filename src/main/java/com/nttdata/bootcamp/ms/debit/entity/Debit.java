package com.nttdata.bootcamp.ms.debit.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "debit")
@Data
@AllArgsConstructor
@Generated
public class Debit {

    @Id
    private Integer id;
    private String cardNumber;
    private String expiryDate;
    private Float availableBalance;
    private List<Integer> accountId;
    private String customerId;

}
