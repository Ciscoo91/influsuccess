package com.ffu.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String INFLUENCER = "ROLE_INFLUENCER";

    public static final String ADVERTISER = "ROLE_ADVERTISER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
