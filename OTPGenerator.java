package com.orderservice.ctrl;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class OTPGenerator {

    private static final int MIN_OTP_VALUE = 1000;
    private static final int MAX_OTP_VALUE = 9999;
    private static final SecureRandom secureRandom;

    static {
        SecureRandom tempSecureRandom = null;
        try {
            tempSecureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle exception as appropriate
        }
        secureRandom = tempSecureRandom;
    }

    public static int generateOTP() {
        return secureRandom.nextInt(MAX_OTP_VALUE - MIN_OTP_VALUE + 1) + MIN_OTP_VALUE;
    }

    public static void main(String[] args) {
        int otp = generateOTP();
        System.out.println("Generated OTP: " + otp);
    }
}

