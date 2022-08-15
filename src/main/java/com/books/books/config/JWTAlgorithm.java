package com.books.books.config;

import com.auth0.jwt.algorithms.Algorithm;

public class JWTAlgorithm {
    private static final String secreteKey = "sdmjfpewojnfqep[c jf[qewpcjewq jfrp]QEWJFP]OQEWJFP " +
            "WSAJpjp}owjp}ojwpjpj FFEAJMNPFAEJFPOQEWJFPOEJmpkfpfjapeFKE[OWFKWE[OFKE[FKWEKFEWFKWEDQWDQWDodfwqweofwefOF" +
            "f+DSAFfewfewfPPff__WEKFEF";
    private static final Algorithm algorithm = Algorithm.HMAC256(secreteKey.getBytes());

    public static Algorithm getAlgorithm() {
        return algorithm;
    }
}
