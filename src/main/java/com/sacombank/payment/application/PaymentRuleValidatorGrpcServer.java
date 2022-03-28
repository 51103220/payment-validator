package com.sacombank.payment.application;

import com.sacombank.payment.domain.PaymentRuleIn;
import com.sacombank.payment.domain.protobuf.PaymentRuleValidateRequest;
import com.sacombank.payment.domain.protobuf.PaymentRuleValidateResponse;
import com.sacombank.payment.domain.protobuf.PaymentRuleValidatorServiceGrpc.PaymentRuleValidatorServiceImplBase;
import com.sacombank.payment.domain.service.IPaymentRuleService;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import io.grpc.stub.StreamObserver;

@Component
public class PaymentRuleValidatorGrpcServer extends PaymentRuleValidatorServiceImplBase{
    private final IPaymentRuleService paymentRuleService;
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(PaymentRuleValidatorGrpcServer.class);

    public PaymentRuleValidatorGrpcServer 
        (IPaymentRuleService paymentRuleService) {
        this.paymentRuleService = paymentRuleService;
    }

    @Override
    public void validate(PaymentRuleValidateRequest request,
            StreamObserver<PaymentRuleValidateResponse> responseObserver) {
        logger.debug("requestId={} receive payment validator request" ,request.getRequestId());
        
        var ruleIn = new PaymentRuleIn();
        ruleIn.setRequestId(request.getRequestId());
        ruleIn.setAmount(request.getAmount());
        
        var ruleOut = paymentRuleService.validate(ruleIn);
        var response = PaymentRuleValidateResponse.newBuilder()
                .setRequestId(request.getRequestId())
                .setResponseCode(ruleOut.getResponseCode())
                .setDescription(ruleOut.getDescription())
                .setValid(ruleOut.getValid())
                .build()
                ;
                
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
