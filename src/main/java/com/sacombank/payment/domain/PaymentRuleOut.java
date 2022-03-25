package com.sacombank.payment.domain;

public class PaymentRuleOut {
    private String responseCode;
    private String Description;
    private Boolean Valid;
    public String getResponseCode() {
        return responseCode;
    }
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public Boolean getValid() {
        return Valid;
    }
    public void setValid(Boolean valid) {
        Valid = valid;
    }
}
