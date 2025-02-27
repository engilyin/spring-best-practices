/*
Copyright 2022 engilyin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.engilyin.bestpractices.rest.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * This test could be used to generate the JWT secret for the configuration.
 * Just run it and copy-paste from the output
 */
@Slf4j
public class JwtTest {

    @Test
    void testSecretGeneration() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // or HS384 or HS512
        String secret = Base64.getEncoder().encodeToString(key.getEncoded());

        log.info("Secret: {}", secret);
    }
}
