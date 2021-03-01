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

    Members getOneMember(@Param("id") int id);

    int addMember(Members members);

    int updateMember(Members members);

    int deleteMember(@Param("id")int id);

    int checkMemacc(@Param("memacc")String memacc);

    int checkMemail(@Param("memail")String memail);
}
