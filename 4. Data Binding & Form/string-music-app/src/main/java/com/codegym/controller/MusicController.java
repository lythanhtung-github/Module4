package com.codegym.controller;

import com.codegym.model.Music;
import com.codegym.model.MusicForm;
import com.codegym.service.IMusicService;
import com.codegym.service.MusicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("")
public class MusicController {
    @Value("${file-upload}")
    private String fileUpload;
    private final IMusicService musicService = new MusicService();
    private final List<String> listType = Arrays.asList("Au-My", "Thieu-Nhi", "Viet", "Tre");

    @GetMapping("")
    public String index(Model model) {
        List<Music> musicList = musicService.findAll();
        model.addAttribute("musicList", musicList);
        return "/index";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("musicForm", new MusicForm());
        modelAndView.addObject("listType", listType);
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute MusicForm musicForm) {
        MultipartFile multipartFile = musicForm.getPath();
        String fileName = multipartFile.getOriginalFilename();
        if (!fileName.endsWith(".mp3")) {
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("message", "Định dạng file nhạc không đúng. Đuôi phải là '.mp3'");
            modelAndView.addObject("musicForm", new MusicForm());
            modelAndView.addObject("listType", listType);
            return modelAndView;
        }
        try {
//            musicForm.getPath().transferTo(new File(fileUpload + fileName));
            FileCopyUtils.copy(musicForm.getPath().getBytes(), new File(fileUpload + fileName));
            //Phương thức transferTo(OutputStream) đọc tất cả các bytes từ InputStream hiện tại và ghi vào OutputStream
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Music music = new Music(musicForm.getName(), musicForm.getSinger(), musicForm.getType(), fileName);
        musicService.save(music);
        ModelAndView modelAndView = new ModelAndView("/index");
        List<Music> musicList = musicService.findAll();
        modelAndView.addObject("musicList", musicList);
        modelAndView.addObject("message", "Successfully !");
        return modelAndView;
    }
}
