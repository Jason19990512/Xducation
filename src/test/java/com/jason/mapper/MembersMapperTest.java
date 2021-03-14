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
    public void getOneMemberById(){
        Members oneMember = memberMapper.getOneMemberById(1);
        System.out.println(oneMember);
    }

    @Test
    public void getOneMemberByMememail(){
        Members oneMember = memberMapper.getOneMemberById(1);
        System.out.println(oneMember);
        Members anothermember = memberMapper.getOneMemberByMememail(oneMember.getMemail());
        System.out.println(anothermember);
    }

    @Test
    public void addMember(){
        Members oneMember = memberMapper.getOneMemberById(1);
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
        Members oneMember = memberMapper.getOneMemberById(35);
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
    public void loginCheckMemacc(){
        Members member = memberMapper.loginCheckMemacc("M00001");
        System.out.println(member);
        if(member != null ){
            System.out.println("帳號已存在");
        }else{
            System.out.println("帳號可創立");
        }
    }

    @Test
    public void registerCheckMemacc(){
        int flag = memberMapper.registerCheckMemacc("M00001");
        if(flag > 0){
            System.out.println("帳號已存在");
        }else{
            System.out.println("帳號可創立");
        }
    }

    @Test
    public void registerCheckMemail(){
        int flag = memberMapper.registerCheckMemail("davidseafood@gmail.com");
        if(flag > 0){
            System.out.println("信箱已使用");
        }else{
            System.out.println("信箱可使用");
        }
    }
}
