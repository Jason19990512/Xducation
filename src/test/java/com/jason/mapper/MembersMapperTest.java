package com.jason.mapper;

import com.jason.pojo.Members;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MembersMapperTest {
    @Autowired
    MemberMapper memberMapper;

    @Test
    public void getAllMembers(){
        List<Members> allMembers = memberMapper.getAllMembers();
        for (Members member : allMembers) {
            System.out.println(member);
        }
    }

    @Test
    public void getOneMember(){
        Members oneMember = memberMapper.getOneMember(1);
        System.out.println(oneMember);
    }

    @Test
    public void addMember(){
        Members oneMember = memberMapper.getOneMember(1);
        oneMember.setMemacc("M00032");
        oneMember.setMemail("jpg1234@gmail.com");
        int flag = memberMapper.addMember(oneMember);

        if(flag > 0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失敗");
        }
    }

    @Test
    public void updateMember(){
        Members oneMember = memberMapper.getOneMember(35);
        oneMember.setMemacc("M00033");
        int flag = memberMapper.updateMember(oneMember);
        if(flag > 0){
            System.out.println("更新成功");
        }else{
            System.out.println("更新失敗");
        }

    }

    @Test
    public void deleteMember(){
        int flag = memberMapper.deleteMember(35);
        if(flag > 0){
            System.out.println("刪除成功");
        }else{
            System.out.println("刪除失敗");
        }
    }

    @Test
    public void checkMemacc(){
        Members member = memberMapper.checkMemacc("M00001");
        System.out.println(member);
        if(member != null ){
            System.out.println("帳號已存在");
        }else{
            System.out.println("帳號可創立");
        }
    }

    @Test
    public void checkMemail(){
        int flag = memberMapper.checkMemail("jpg1234@gmail.com");
        if(flag > 0){
            System.out.println("信箱已使用");
        }else{
            System.out.println("信箱可使用");
        }
    }
}
