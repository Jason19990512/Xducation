package com.jason.config;

import com.jason.controller.RegisterController;
import com.jason.mapper.MemberMapper;
import com.jason.pojo.Members;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

//開啟SpringSecurity
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberMapper memberMapper;

    Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // csrf 是一種攻擊網頁的方式，@EnableWebSecurity生效後會自動保護，保護PATCH，POST，PUT和DELETE方法，若要使用相關方法需要使csrf失效
        http.csrf().disable();

        // 設定頁面可以被哪一種角色訪問
        // 首頁可以被任何角色訪問 "/" -> 所有的請求
        http.authorizeRequests().antMatchers("/","/register/**","/log/**").permitAll();
//                .antMatchers("/level2/**").hasRole("vip2")
//                .antMatchers("/level3/**").hasRole("vip3");


        //開啟自動配置登入功能
        //沒有登入即沒有角色會被導到登入頁面
//        //  /login請求來到登錄頁(預設的)
        http.formLogin().loginPage("/log/toLogin") //指定登入頁面
                        .loginProcessingUrl("/log/login") //提交參數的表單
                        .usernameParameter("memacc")
                        .passwordParameter("mempwd");




        // 登出後導到首頁 --> 這樣loginController 不用在處理跳出後的跳轉
        http.logout().logoutSuccessUrl("/");


    }

    //定義認證規則
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 什麼帳號 擁有什麼角色

        List<Members> allMembers = memberMapper.getAllMembers();

        for (Members members : allMembers){
            String[] roleList = members.getRoleList().split(",");
            auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser(members.getMemacc()).password(new BCryptPasswordEncoder().encode(members.getMempwd())).roles(roleList);
        }

    }
}
