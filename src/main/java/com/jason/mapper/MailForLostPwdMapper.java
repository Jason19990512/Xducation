package com.jason.mapper;

import com.jason.pojo.MailForLostPwd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MailForLostPwdMapper {

    MailForLostPwd findLostPwdByMemail(@Param("memail") String memail);

    Integer deleteLostPwdByMemail(@Param("memail") String memail);

    Integer saveLostPwdByMemail(MailForLostPwd mailForLostPwd);
}
