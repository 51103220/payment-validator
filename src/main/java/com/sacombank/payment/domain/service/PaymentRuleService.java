package com.sacombank.payment.domain.service;


import com.sacombank.payment.domain.PaymentRuleIn;
import com.sacombank.payment.domain.PaymentRuleOut;

import org.apache.logging.log4j.LogManager;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Metrics;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.extension.annotations.WithSpan;

public class PaymentRuleService implements IPaymentRuleService {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PaymentRuleService.class);

    public PaymentRuleService() {
        
    }

    @Timed(value = "rule.payment.validate", description = "Time to validate a payment transaction")
    @WithSpan
    @Override
    public PaymentRuleOut validate(PaymentRuleIn request) {
        logger.debug("requestId={} validating rule", request.getRequestId());
        
        //call rule engine
        var response = new PaymentRuleOut();

        response.setValid(true);
        response.setResponseCode("00");
        response.setDescription("validated");
        return  response;
    }
}
