package com.ffu.service.dto;


import com.stripe.param.PaymentMethodCreateParams;

public class PaymentRequestDTO {
    public enum Currency{
        EUR, USD
    }

    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private PaymentMethodCreateParams.Token token;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getStripeEmail() {
        return stripeEmail;
    }

    public void setStripeEmail(String stripeEmail) {
        this.stripeEmail = stripeEmail;
    }

    public PaymentMethodCreateParams.Token getToken() {
        return token;
    }

    public void setToken(PaymentMethodCreateParams.Token token) {
        this.token = token;
    }
}
