package com.jason.listener;

import com.jason.controller.IndexController;
import com.jason.controller.RegisterController;
import com.jason.mapper.MemberMapper;
import com.jason.pojo.Members;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    Logger logger = Logger.getLogger(RegisterController.class);

    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        String userName    = (String)event.getAuthentication().getPrincipal();
        String credentials = (String)event.getAuthentication().getCredentials();

        logger.debug("Failed login using USERNAME [" + userName + "]");
        logger.debug("Failed login using PASSWORD [" + credentials + "]");
    }
}
