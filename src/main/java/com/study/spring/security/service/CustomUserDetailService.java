package com.study.spring.security.service;

import com.study.spring.account.service.AccountService;
import com.study.spring.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        Account account = accountService.get(loginId);

        User user = new User(account.getLoingId(),
                account.getPassword(),
                AuthorityUtils.createAuthorityList("ROLE_ADMIN"));

        return user;
    }
}