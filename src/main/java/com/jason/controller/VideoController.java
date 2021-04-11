package com.jason.controller;

import com.jason.mapper.VideoMapper;
import com.jason.pojo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("video")
public class VideoController {

    @Autowired
    VideoMapper videoMapper;
    @RequestMapping("videoReader")
    public void videoReader(HttpServletRequest req, HttpServletResponse res, @RequestParam("videono") String videono) throws IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("video/mp4");
        ServletOutputStream out = res.getOutputStream();
        Video video = videoMapper.findVideoByPrimaryKey(videono);

        BufferedInputStream in = new BufferedInputStream(new FileInputStream("C:/Users/jpg74/IdeaProjects/Xducation/src/main" + video.getVideopath()));
        byte[] buf = new byte[30 * 1024 * 1024]; // 8K Buffer
        int len;
        while ((len = in.read(buf)) != -1) {
            out.write(buf, 0, len);
            out.flush();
        }
        in.close();
    }
}
