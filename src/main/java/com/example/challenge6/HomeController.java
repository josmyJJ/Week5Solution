package com.example.challenge6;

import com.cloudinary.utils.ObjectUtils;
import com.example.challenge6.config.CloudinaryConfig;
import com.example.challenge6.model.Instructor;
import com.example.challenge6.model.Department;
import com.example.challenge6.repository.InstructorRepository;
import com.example.challenge6.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listActors(Model model) {
        model.addAttribute("instructors", instructorRepository.findAll());
        model.addAttribute("categories", departmentRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String newMeme(Model model) {
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("categories", departmentRepository.findAll());
        return "form";
    }

    @GetMapping("/addCat")
    public String newType(Model model){
        model.addAttribute("type", new Department());
        return "category";
    }

    @PostMapping("/add")
    public String processMeme(@Valid @ModelAttribute("instructor") Instructor meme,
                              @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            System.out.println("File is empty...");
            return "redirect:/add";
        }
        try {
            Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            meme.setPictureUrl(uploadResult.get("url").toString());

            instructorRepository.save(meme);
        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:/add";
        }
        return "redirect:/";
    }

    @PostMapping("/addCat")
    public String processCategory(@Valid @ModelAttribute("type") Department department){
        departmentRepository.save(department);
        return "redirect:/";
    }


    @RequestMapping("/detail/{id}")
    public String showMeme(@PathVariable("id") long id, Model model) {
        model.addAttribute("instructor", instructorRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateMeme(@PathVariable("id") long id, Model model) {
        model.addAttribute("instructor", instructorRepository.findById(id));
        return "form";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMeme(@PathVariable("id") long id) {
        instructorRepository.deleteById(id);
        return "redirect:/";
    }

}
