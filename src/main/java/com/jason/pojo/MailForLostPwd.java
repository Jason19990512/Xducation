package com.jason.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailForLostPwd {
    private String memail;
    private String sid;
    private Long expTime;

    @Override
    public String toString() {
        return "MailForLostPwd{" +
                "memail='" + memail + '\'' +
                ", sid='" + sid + '\'' +
                ", expTime=" + expTime +
                '}';
    }
}
