package com.jason.service.Impl;

import com.jason.mapper.MemberMapper;
import com.jason.pojo.Members;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Members member = memberMapper.getOneMemberByMemacc(username);
        if(member == null)
            throw new UsernameNotFoundException(username + " not found");
        UserDetails userDetails = User.builder()
                .username(member.getMemacc())
                .password("{noop}" + member.getMempwd())
                .roles(member.getRoleList()).build();
        return userDetails;
    }
}
