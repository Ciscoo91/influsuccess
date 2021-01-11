package com.ffu.web.rest;


import com.ffu.service.StripeService;
import com.stripe.model.Coupon;
import com.sun.mail.iap.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StripeResource {
    @Value("${stripe.keys.public}")
    private String API_PUBLIC_KEY;

    private StripeService stripeService;

    public StripeResource(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    /*========== REST APIs for Handling Payments ===================*/

    @PostMapping("/create-subscription")
    public @ResponseBody
    Response createSubscription(String email, String token, String plan, String coupon) {
        //validate data
        if (token == null || plan.isEmpty()) {
            return new Response("Stripe payment token is missing. Please, try again later.", false);
        }

        //create customer first
        String customerId = stripeService.createCustomer(email, token);

        if (customerId == null) {
            return new Response("An error occurred while trying to create a customer.", false);
        }

        //create subscription
        String subscriptionId = stripeService.createSubscription(customerId, plan, coupon);
        if (subscriptionId == null) {
            return new Response("An error occurred while trying to create a subscription.", false);
        }

        // Ideally you should store customerId and subscriptionId along with customer object here.
        // These values are required to update or cancel the subscription at later stage.

        return new Response("Success! Your subscription id is " + subscriptionId, true);
    }

    @PostMapping("/cancel-subscription")
    public @ResponseBody
    Response cancelSubscription(String subscriptionId) {
        boolean status = stripeService.cancelSubscription(subscriptionId);
        if (!status) {
            return new Response("Failed to cancel the subscription. Please, try later.", false );
        }
        return new Response("Subscription cancelled successfully.", true);
    }

    @PostMapping("/coupon-validator")
    public @ResponseBody
    Response couponValidator(String code) {
        Coupon coupon = stripeService.retrieveCoupon(code);
        if (coupon != null && coupon.getValid()) {
            String details = (coupon.getPercentOff() == null ? "$" + (coupon.getAmountOff() / 100) : coupon.getPercentOff() + "%") +
                " OFF " + coupon.getDuration();
            return new Response(details, true);
        } else {
            return new Response("This coupon code is not available. This may be because it has expired or has " +
                "already been applied to your account.", false);
        }
    }

    @PostMapping("/create-charge")
    public @ResponseBody
    Response createCharge(String email, String token) {
        //validate data
        if (token == null) {
            return new Response("Stripe payment token is missing. Please, try again later.", false);
        }

        //create charge
        String chargeId = stripeService.createCharge(email, token, 999); //$9.99 USD
        if (chargeId == null) {
            return new Response("An error occurred while trying to create a charge.", false);
        }

        // You may want to store charge id along with order information

        return new Response("Success! Your charge id is " + chargeId, true);
    }
}