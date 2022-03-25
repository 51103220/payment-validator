package com.sacombank.payment.domain.service;

import com.sacombank.payment.domain.PaymentRuleIn;
import com.sacombank.payment.domain.PaymentRuleOut;

public interface IPaymentRuleService {
    public PaymentRuleOut validate(PaymentRuleIn request);
}
