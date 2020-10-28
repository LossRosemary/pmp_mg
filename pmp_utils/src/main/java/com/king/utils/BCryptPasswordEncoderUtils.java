package com.king.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String encode = bCryptPasswordEncoder.encode("queue");
        //123: $2a$10$BAcJImzsJUp8hYY3j1w/z.bbhc1JH9vpoUIAGCiu08EzFRVdc1lXG
        //king:$2a$10$CfUmqFp7k/L1MToyd2fxB.Ugms01/LAvrcc/UCkOL3gdmemYXQpNG
        //queue:$2a$10$t8xbcbHLk1TrOsY.lN3Cj.AyeJA4imZqesi98L5EIAiPOH2k8iQC.
        System.out.println(encode);


    }
}
