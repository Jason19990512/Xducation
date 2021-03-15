package com.jason.mapper;

import com.jason.pojo.Members;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberMapper {
    List<Members> getAllMembers();

    Members getOneMemberById(@Param("id") int id);

    Members getOneMemberByMemacc(@Param("memacc")String memacc);

    Members getOneMemberByMememail(@Param("memail") String memail);

    Integer addMember(Members members);

    Integer updateMember(Members members);

    Integer deleteMember(@Param("id")int id);

    Integer registerCheckMemacc(@Param("memacc")String memacc);

    Integer registerCheckMemail(@Param("memail")String memail);
}
