package com.sacombank.payment.domain;

public class PaymentRuleIn {
    private String requestId;
    private String amount;
    
    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
    
}
