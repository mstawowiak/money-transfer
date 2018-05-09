package com.github.mstawowiak.money.transfer.application.transaction;

import java.math.BigDecimal;
import javax.validation.constraints.Max;

public class TransactionRequest {

    @Max(28)
    public String sourceAccountId;

    @Max(28)
    public String destinationAccountId;

    public BigDecimal amount;

    public String currencyCode;

}
