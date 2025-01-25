package com.engilyin.bestpractices.rest.services.system;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean check(String checkPassword, String realPassword) {
        return BCrypt.checkpw(checkPassword, realPassword);
    }
}
