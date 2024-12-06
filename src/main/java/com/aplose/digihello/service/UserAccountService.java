package com.aplose.digihello.service;

import com.aplose.digihello.model.UserAccount;
import com.aplose.digihello.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        UserAccount user1 = new UserAccount("user1", "passUser1", "ROLE_USER");
        userAccountRepository.save(user1);
        UserAccount user2 = new UserAccount("user2", passwordEncoder.encode("passUser2"), "ROLE_USER");
        userAccountRepository.save(user2);
        UserAccount admin1 = new UserAccount("admin1", "passAdmin1", "ROLE_ADMIN");
        userAccountRepository.save(admin1);
        UserAccount admin2 = new UserAccount("admin2", passwordEncoder.encode("passAdmin2"), "ROLE_ADMIN");
        userAccountRepository.save(admin2);
    }
}
