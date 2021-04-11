package com.jason.mapper;

import com.jason.pojo.Video;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface VideoMapper {

    public List<Video> getAllVideo();

    public Video findVideoByPrimaryKey(String videono);

    public void insertVideo(Video videoVO);

    public void updateVideo(Video videoVO);

    public void delete(String videono);



    public List<Video> getVideoByCondition(Map map);
}
