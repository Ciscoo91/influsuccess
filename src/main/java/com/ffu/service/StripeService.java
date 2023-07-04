package com.ffu.service;


import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Value;
import com.ffu.service.dto.PaymentRequestDTO;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value("${stripe.keys.secret}")
    private String API_SECRET_KEY;

    @PostConstruct
    public void init(){
        Stripe.apiKey = API_SECRET_KEY;
    }

    public String charge(PaymentRequestDTO paymentRequestDTO) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", paymentRequestDTO.getAmount());
        chargeParams.put("currency", PaymentRequestDTO.Currency.USD);
        chargeParams.put("source", paymentRequestDTO.getToken());

        Charge charge = Charge.create(chargeParams);
        return charge.getId();
    }
}
