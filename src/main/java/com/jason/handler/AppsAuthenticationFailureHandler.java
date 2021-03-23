package com.jason.handler;

import com.jason.controller.IndexController;
import com.jason.controller.RegisterController;
import com.jason.mapper.MemberMapper;
import com.jason.pojo.Members;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import sun.awt.Win32FontManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AppsAuthenticationFailureHandler implements AuthenticationFailureHandler {

    Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    MemberMapper memberMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {



        String memacc = request.getParameter("memacc");   // 取得登入帳號
        logger.info(memacc);
        if(memacc != null){
            logger.info(memberMapper);

            new IndexController().setCurrentUser(memacc);
            response.sendRedirect( "/" );
        }
    }

}
