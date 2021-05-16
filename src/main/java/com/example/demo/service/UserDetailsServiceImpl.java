package com.example.demo.service;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.SiteUser;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	private final UserRepository userRepository;
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException{	
		// ユーザー名が空か判定
        if (StringUtils.isEmpty(username)) {
            // 空の場合、UsernameNotFoundExceptionをthrowする
            throw new UsernameNotFoundException("Not found: " + username);
        }
        // ユーザー名に紐づく情報を取得
        SiteUser loginUser = userRepository.getOne(username);
        if (loginUser == null) {
            // ユーザー情報が空の場合、UsernameNotFoundExceptionをthrowする
            throw new UsernameNotFoundException("Not found: " + username);
        }
        return createUserDetails(loginUser);
    }
	public User createUserDetails(SiteUser user) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
}
