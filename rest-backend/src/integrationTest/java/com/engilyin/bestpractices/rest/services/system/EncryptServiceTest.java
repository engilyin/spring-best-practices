package com.engilyin.bestpractices.rest.services.system;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * This class could be used to generate password for the test user. The passwords are stored in encrypted form in the DB
 */
@Slf4j
class EncryptServiceTest {

    @Test
    void testEncrypt() {
        log.info("Encripted Password: {}", new EncryptService().encrypt("1"));
    }
}
