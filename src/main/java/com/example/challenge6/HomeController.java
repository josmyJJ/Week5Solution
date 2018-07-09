package com.example.challenge6;

import com.cloudinary.utils.ObjectUtils;
import com.example.challenge6.config.CloudinaryConfig;
import com.example.challenge6.model.Car;
import com.example.challenge6.model.Type;
import com.example.challenge6.repository.CarRepository;
import com.example.challenge6.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    CarRepository memeRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listActors(Model model) {
        model.addAttribute("memes", memeRepository.findAll());
        model.addAttribute("categories", typeRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String newMeme(Model model) {
        model.addAttribute("meme", new Car());
        model.addAttribute("categories", typeRepository.findAll());
        return "form";
    }

    @GetMapping("/addCat")
    public String newType(Model model){
        model.addAttribute("type", new Type());
        return "category";
    }

    @PostMapping("/add")
    public String processMeme(@Valid @ModelAttribute("meme") Car meme,
                              @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            System.out.println("File is empty...");
            return "redirect:/add";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            meme.setPictureUrl(uploadResult.get("url").toString());

            memeRepository.save(meme);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/add";
        }
        return "redirect:/";
    }

    @PostMapping("/addCat")
    public String processCategory(@Valid @ModelAttribute("type") Type type){
        typeRepository.save(type);
        return "redirect:/";
    }



    @RequestMapping("/detail/{id}")
    public String showMeme(@PathVariable("id") long id, Model model) {
        model.addAttribute("meme", memeRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateMeme(@PathVariable("id") long id, Model model) {
        model.addAttribute("meme", memeRepository.findById(id));
        return "form";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMeme(@PathVariable("id") long id) {
        memeRepository.deleteById(id);
        return "redirect:/";
    }

}
