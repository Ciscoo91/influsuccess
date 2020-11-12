<template>
    <div>
        <div class="row justify-content-center">
            <div class="col-md-8 toastify-container">
                <h1 v-text="$t('register.title')" id="register-title">Registration</h1>

                <div class="alert alert-success" role="alert" v-if="success" v-html="$t('register.messages.success')">
                    <strong>Registration saved!</strong> Please check your email for confirmation.
                </div>

                <div class="alert alert-danger" role="alert" v-if="error" v-html="$t('register.messages.error.fail')">
                    <strong>Registration failed!</strong> Please try again later.
                </div>

                <div class="alert alert-danger" role="alert" v-if="errorUserExists" v-html="$t('register.messages.error.userexists')">
                    <strong>Login name already registered!</strong> Please choose another one.
                </div>

                <div class="alert alert-danger" role="alert" v-if="errorEmailExists" v-html="$t('register.messages.error.emailexists')">
                    <strong>Email is already in use!</strong> Please choose another one.
                </div>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-8">
                <form id="register-form" name="registerForm" role="form" v-on:submit.prevent="register()" v-if="!success" no-validate>
                    <div class="form-group">
                        <label class="form-control-label" for="role" v-text="$t('global.form[\'role\']')">I'am an ?</label>
                        <select type="text" class="form-control" v-model="$v.role.$model" id="role" name="role"
                                :class="{'valid': !$v.role.$invalid, 'invalid': $v.role.$invalid }" >
                            <option :value=Authority.ADVERTISER v-text="$t('register.authorities.advertiser')">Advertiser</option>
                            <option :value=Authority.INFLUENCER v-text="$t('register.authorities.influencer')">Influencer</option>
                        </select>
                        <div v-if="$v.role.$anyDirty && $v.role.$invalid">
                            <small class="form-text text-danger" v-if="!$v.role.required"
                                   v-text="$t('register.messages.validate.role.required')">
                                Your status is required.
                            </small>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-control-label" for="username" v-text="$t('global.form[\'username.label\']')">Username</label>
                        <input type="text" class="form-control" v-model="$v.registerAccount.login.$model" id="username" name="login"
                               :class="{'valid': !$v.registerAccount.login.$invalid, 'invalid': $v.registerAccount.login.$invalid }"
                               required minlength="1" maxlength="50" pattern="^[a-zA-Z0-9!#$&'*+=?^_`{|}~.-]+@?[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" v-bind:placeholder="$t('global.form[\'username.placeholder\']')">
                        <div v-if="$v.registerAccount.login.$anyDirty && $v.registerAccount.login.$invalid">
                            <small class="form-text text-danger" v-if="!$v.registerAccount.login.required"
                                   v-text="$t('register.messages.validate.login.required')">
                                Your username is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.login.minLength"
                                   v-text="$t('register.messages.validate.login.minlength')">
                                Your username is required to be at least 1 character.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.login.maxLength"
                                   v-text="$t('register.messages.validate.login.maxlength')">
                                Your username cannot be longer than 50 characters.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.login.pattern"
                                   v-text="$t('register.messages.validate.login.pattern')">
                                Your username can only contain letters and digits.
                            </small>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-control-label" for="lastName" v-text="$t('global.form[\'lastName.label\']')">Last Name</label>
                        <input type="text" class="form-control" v-model="$v.registerAccount.lastName.$model" id="lastName" name="lastName"
                               :class="{'valid': !$v.registerAccount.lastName.$invalid, 'invalid': $v.registerAccount.lastName.$invalid }"
                               required minlength="1"  v-bind:placeholder="$t('global.form[\'lastName.placeholder\']')">
                        <div v-if="$v.registerAccount.lastName.$anyDirty && $v.registerAccount.lastName.$invalid">
                            <small class="form-text text-danger" v-if="!$v.registerAccount.lastName.required"
                                   v-text="$t('register.messages.validate.firstName.required')">
                                Your last name is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.lastName.minLength"
                                   v-text="$t('register.messages.validate.lastName.minlength')">
                                Your last name is required to be at least 1 character.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.firstName.alpha"
                                   v-text="$t('register.messages.validate.lastName.alpha')">
                                Your last name can only contain letters.
                            </small>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-control-label" for="firstName" v-text="$t('global.form[\'firstName.label\']')">First Name</label>
                        <input type="text" class="form-control" v-model="$v.registerAccount.firstName.$model" id="firstName" name="firstName"
                               :class="{'valid': !$v.registerAccount.firstName.$invalid, 'invalid': $v.registerAccount.firstName.$invalid }"
                               required minlength="1"  v-bind:placeholder="$t('global.form[\'firstName.placeholder\']')">
                        <div v-if="$v.registerAccount.firstName.$anyDirty && $v.registerAccount.firstName.$invalid">
                            <small class="form-text text-danger" v-if="!$v.registerAccount.firstName.required"
                                   v-text="$t('register.messages.validate.firstName.required')">
                                Your first name is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.firstName.minLength"
                                   v-text="$t('register.messages.validate.firstName.minlength')">
                                Your first name is required to be at least 1 character.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.firstName.alpha"
                                   v-text="$t('register.messages.validate.firstName.alpha')">
                                Your first name can only contain letters.
                            </small>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-control-label" for="email" v-text="$t('global.form[\'email.label\']')">Email</label>
                        <input type="email" class="form-control" id="email" name="email"
                               :class="{'valid': !$v.registerAccount.email.$invalid, 'invalid': $v.registerAccount.email.$invalid }"
                               v-model="$v.registerAccount.email.$model" minlength=5 maxlength=254 email required  v-bind:placeholder="$t('global.form[\'email.placeholder\']')">
                        <div v-if="$v.registerAccount.email.$anyDirty && $v.registerAccount.email.$invalid">
                            <small class="form-text text-danger" v-if="!$v.registerAccount.email.required"
                                   v-text="$t('global.messages.validate.email.required')">
                                Your email is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.email.email"
                                   v-text="$t('global.messages.validate.email.invalid')">
                                Your email is invalid.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.email.minLength"
                                   v-text="$t('global.messages.validate.email.minlength')">
                                Your email is required to be at least 5 characters.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.email.maxLength"
                                   v-text="$t('global.messages.validate.email.maxlength')">
                                Your email cannot be longer than 100 characters.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="firstPassword" v-text="$t('global.form[\'newpassword.label\']')">New password</label>
                        <input type="password" class="form-control" id="firstPassword" name="password"
                               :class="{'valid': !$v.registerAccount.password.$invalid, 'invalid': $v.registerAccount.password.$invalid }"
                               v-model="$v.registerAccount.password.$model" minlength=4 maxlength=50 required v-bind:placeholder="$t('global.form[\'newpassword.placeholder\']')">
                        <div v-if="$v.registerAccount.password.$anyDirty && $v.registerAccount.password.$invalid">
                            <small class="form-text text-danger" v-if="!$v.registerAccount.password.required"
                                   v-text="$t('global.messages.validate.newpassword.required')">
                                Your password is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.password.minLength"
                                   v-text="$t('global.messages.validate.newpassword.minlength')">
                                Your password is required to be at least 4 characters.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.password.maxLength"
                                   v-text="$t('global.messages.validate.newpassword.maxlength')">
                                Your password cannot be longer than 50 characters.
                            </small>
                        </div>
                        <!--<jhi-password-strength-bar [passwordToCheck]="registerAccount.password"></jhi-password-strength-bar>-->
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="secondPassword" v-text="$t('global.form[\'confirmpassword.label\']')">New password confirmation</label>
                        <input type="password" class="form-control" id="secondPassword" name="confirmPasswordInput"
                               :class="{'valid': !$v.confirmPassword.$invalid, 'invalid': $v.confirmPassword.$invalid }"
                               v-model="$v.confirmPassword.$model" minlength=4 maxlength=50 required v-bind:placeholder="$t('global.form[\'confirmpassword.placeholder\']')">
                        <div v-if="$v.confirmPassword.$dirty && $v.confirmPassword.$invalid">
                            <small class="form-text text-danger" v-if="!$v.confirmPassword.required"
                                   v-text="$t('global.messages.validate.confirmpassword.required')">
                                Your confirmation password is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.confirmPassword.minLength"
                                   v-text="$t('global.messages.validate.confirmpassword.minlength')">
                                Your confirmation password is required to be at least 4 characters.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.confirmPassword.maxLength"
                                   v-text="$t('global.messages.validate.confirmpassword.maxlength')">
                                Your confirmation password cannot be longer than 50 characters.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.confirmPassword.sameAsPassword"
                                   v-text="$t('global.messages.error.dontmatch')">
                                The password and its confirmation do not match!
                            </small>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-control-label" for="birthday" v-text="$t('global.form[\'birthday.label\']')">Birthday</label>
                        <b-form-datepicker v-model="$v.registerAccount.userExtra.birthday.$model" id="birthday"
                                           :class="{'valid': !$v.registerAccount.userExtra.birthday.$invalid, 'invalid': $v.registerAccount.userExtra.birthday.$invalid }"
                                           required v-bind:placeholder="$t('global.form[\'birthday.placeholder\']')"></b-form-datepicker>
                        <div v-if="$v.registerAccount.userExtra.birthday.$anyDirty && $v.registerAccount.userExtra.birthday.$invalid">
                            <small class="form-text text-danger" v-if="!$v.registerAccount.userExtra.birthday.required"
                                   v-text="$t('register.messages.validate.birthday.required')">
                                Country is required.
                            </small>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="form-control-label" for="country" v-text="$t('global.form[\'country.label\']')">Country</label>
                        <input type="text" class="form-control" v-model="$v.registerAccount.userExtra.country.$model" id="country" name="country"
                               :class="{'valid': !$v.registerAccount.userExtra.country.$invalid, 'invalid': $v.registerAccount.userExtra.country.$invalid }"
                               required minlength="1"  v-bind:placeholder="$t('global.form[\'country.placeholder\']')">
                        <div v-if="$v.registerAccount.userExtra.country.$anyDirty && $v.registerAccount.userExtra.country.$invalid">
                            <small class="form-text text-danger" v-if="!$v.registerAccount.userExtra.country.required"
                                   v-text="$t('register.messages.validate.country.required')">
                                Country is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.userExtra.country.minLength"
                                   v-text="$t('register.messages.validate.country.minlength')">
                                Country required to be at least 1 character.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.userExtra.country.alpha"
                                   v-text="$t('register.messages.validate.country.alpha')">
                                Country can only contain letters.
                            </small>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="form-control-label" for="phone" v-text="$t('global.form[\'phone.label\']')"> Phone </label>
                            <vue-tel-input  id = "phone" name="phone"
                                            :class="{'valid': !$v.registerAccount.userExtra.phone.$invalid, 'invalid': $v.registerAccount.userExtra.phone.$invalid }"
                                            v-model="$v.registerAccount.userExtra.phone.$model"
                                            v-bind:placeholder="$t('global.form[\'phone.placeholder\']')"
                            >
                            </vue-tel-input>
                        <div v-if="$v.registerAccount.userExtra.phone.$anyDirty && $v.registerAccount.userExtra.phone.$invalid">

                            <small class="form-text text-danger" v-if="!$v.registerAccount.userExtra.phone.numeric"
                                   v-text="$t('register.messages.validate.phone.numeric')">
                                Your phone can only contain digits
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.userExtra.phone.minLength"
                                   v-text="$t('register.messages.validate.phone.minlength')">
                                Your phone must contain 10 digits.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.registerAccount.userExtra.phone.maxLength"
                                   v-text="$t('register.messages.validate.phone.maxlength')">
                                Your phone must contain 10 digits.
                            </small>
                    </div>
                    </div>
                    <button type="submit" :disabled="$v.$invalid " class="btn btn-primary" v-text="$t('register.form.button')">Register</button>
                </form>
                <p></p>
                <div class="alert alert-warning">
                    <span v-text="$t('global.messages.info.authenticated.prefix')">Already register ?</span>
                    <a class="alert-link" v-on:click="openLogin()" v-text="$t('global.messages.info.authenticated.link')">sign in</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script lang='ts' src='./register.component.ts'>
</script>
