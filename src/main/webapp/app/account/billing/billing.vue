<template>
    <div id="checkout" class="container mt-5">
        <section class="py-5" v-if="step == 0">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-8 col-12 my-auto mx-auto">
                        <h1>
                            Stripe Recurring Subscription
                        </h1>
                        <p class="lead mb-4">
                            Please fill the form below to complete the payment
                        </p>
                        <h5 class="mb-2">Choose your payment plan</h5>
                        <p class="text-muted">
                            60% OFF when you upgrade to annual plan.
                        </p>
                        <div class="py-2">
                            <div class="custom-control custom-radio">
                                <input class="custom-control-input" id="monthly-plan" name="premium-plan" type="radio"
                                    value="monthly-subscription"/>
                                <label class="custom-control-label" for="monthly-plan">
                                    <strong>Monthly $9.99</strong><br/>
                                    <small class="text-muted">
                                        Pay $9.99 every month and get access to all premium features.
                                    </small>
                                </label>
                            </div>
                            <div class="custom-control custom-radio mt-3">
                                <input checked="" class="custom-control-input" id="annually-plan" name="premium-plan"
                                    type="radio" value="annual-subscription"/>
                                <label class="custom-control-label" for="annually-plan">
                                    <strong>Yearly $49.99</strong>
                                    <span class="badge badge-primary ml-1">60% OFF</span>
                                    <br/>
                                    <small class="text-muted">
                                        Pay $49.99 every year and get access to all premium features.
                                    </small>
                                </label>
                            </div>
                        </div>
                        <form action="#" id="payment-form" method="post">
                            <input id="api-key" type="hidden" th:value="${stripePublicKey}">
                            <div class="form-group">
                                <label class="font-weight-medium" for="card-element">
                                    Enter credit or debit card below
                                </label>
                                <div class="w-100" id="card-element">
                                    <!-- A Stripe Element will be inserted here. -->
                                </div>
                            </div>
                            <div class="form-group">
                                <input class="form-control" id="email" name="email"
                                    placeholder="Email Address" type="email" required>
                            </div>
                            <div class="form-group">
                                <input class="form-control" id="coupon" name="coupon"
                                    placeholder="Coupon code (optional)" type="text">
                            </div>
                            <!-- Used to display Element errors. -->
                            <div class="text-danger w-100" id="card-errors" role="alert"></div>
                            <div class="form-group pt-2">
                                <button class="btn btn-primary btn-block" id="submitButton" type="submit">
                                    Pay With Your Card
                                </button>
                                <div class="small text-muted mt-2">
                                    Pay securely with Stripe. By clicking the button above, you agree
                                    to our <a target="_blank" href="#">Terms of Service</a>,
                                    <a target="_blank" href="#">Privacy</a> and
                                    <a target="_blank" href="#">Refund</a> policies.

                                </div>
                            </div>


                        </form>
                        <p class="mt-5 text-muted">
                            <small>An example project by <a th:href="@{https://attacomsian.com}" target="_blank">Atta</a>.
                            </small>
                        </p>
                    </div>
                </div>
                <button @click="nextStep" class="btn btn-primary">next</button>
            </div>
        </section>
        <div v-if="step == 1">
            You came from step 1 (0 in computer language)
            <button @click="prevStep" class="btn btn-primary">prev</button><button @click="nextStep" class="btn btn-primary">next</button>
        </div>
    </div>
</template>
<script src="https://js.stripe.com/v3/"></script>
<script lang="ts" src="./billing.component.ts"></script>