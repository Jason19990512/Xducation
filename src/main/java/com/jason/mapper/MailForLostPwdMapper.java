package com.jason.mapper;

import com.jason.pojo.MailForLostPwd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MailForLostPwdMapper {

    MailForLostPwd findLostPwdByMemail(@Param("memail") String memail);

    Integer deleteLostPwdByMemail(@Param("memail") String memail);

    Integer saveLostPwdByMemail(MailForLostPwd mailForLostPwd);
}
