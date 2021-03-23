package com.jason.utils;

import com.jason.pojo.Members;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.List;

public  class MemberAuthorityUtils {
    private static final List<GrantedAuthority> TEACHER_ROLES = AuthorityUtils
            .createAuthorityList("S", "T");
    //利用Spring提供的AuthorityUtils中createAuthorityList將該群組加入相關roles
    //以便用一個List變數就儲存所有roles
    private static final List<GrantedAuthority> STUDENT_ROLES = AuthorityUtils
            .createAuthorityList("S");

    public static Collection<? extends GrantedAuthority> createAuthorities(
            Members member) {
        String roleList = member.getRoleList();
        if (roleList.contains("T")) { //角色清單中含有T，即有老師之role
            return TEACHER_ROLES;
        }
        return STUDENT_ROLES; //否則則為一般學生
    }
}
