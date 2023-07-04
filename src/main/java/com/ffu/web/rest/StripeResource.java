package com.ffu.web.rest;


import com.ffu.service.StripeService;

import com.ffu.service.dto.PaymentRequestDTO;
import com.ffu.service.utils.CreateCheckoutSessionRequest;
import com.stripe.exception.StripeException;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;


@RestController
@RequestMapping("/api")
public class StripeResource {

    @Autowired
    StripeService stripeService;

    @PostMapping
    public ResponseEntity<String> completePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) throws StripeException {
        String chargeId = stripeService.charge(paymentRequestDTO);
        return chargeId!=null? new ResponseEntity<String>(chargeId, HttpStatus.OK): new ResponseEntity<String>("Please check the credit card details entered", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public String handleError(StripeException ex) {
        return ex.getMessage();
    }

    @PostMapping("/create-cehckout-session")
    public ResponseEntity<Json> createCheckoutSession(PaymentRequestDTO paymentRequestDTO) {
        CreateCheckoutSessionRequest req = gson.fromJson(request.body(), CreateCheckoutSessionRequest.class);

        // See https://stripe.com/docs/api/checkout/sessions/create
        // for additional parameters to pass.
        // {CHECKOUT_SESSION_ID} is a string literal; do not change it!
        // the actual Session ID is returned in the query parameter when your customer
        // is redirected to the success page.
        SessionCreateParams params = new SessionCreateParams.Builder()
            .setSuccessUrl("https://example.com/success.html?session_id={CHECKOUT_SESSION_ID}")
            .setCancelUrl("https://example.com/canceled.html")
            .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
            .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
            .addLineItem(new SessionCreateParams.LineItem.Builder()
                // For metered billing, do not pass quantity
                .setQuantity(1L)
                .setPrice(req.getPriceId())
                .build()
            )
            .build();

        try {
            Session session = Session.create(params);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("sessionId", session.getId());
            return gson.toJson(responseData);
        } catch(Exception e) {
            Map<String, Object> messageData = new HashMap<>();
            messageData.put("message", e.getMessage());
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("error", messageData);
            response.status(400);
            return gson.toJson(responseData);
        }
    }
}
