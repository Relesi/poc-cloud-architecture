//package com.relesi.cloudarchitecture.api.utils;
//
//import org.junit.Test;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import static org.junit.Assert.assertNull;
//import static org.junit.Assert.assertTrue;
//
//public class PasswordUtilsTest {
//
//    private static final String PASSWORD = "123456";
//    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//    @Test
//    public void testNullPassword() throws Exception {
//        assertNull(PasswordUtils.generateBCrypt(null));
//    }
//
//    @Test
//    public void testGeneratePasswordHash() throws Exception {
//        String hash = PasswordUtils.generateBCrypt(PASSWORD);
//        assertTrue(bCryptPasswordEncoder.matches(PASSWORD, hash));
//
//    }
//}
