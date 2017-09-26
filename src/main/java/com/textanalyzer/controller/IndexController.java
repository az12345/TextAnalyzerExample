package com.textanalyzer.controller;

import com.textanalyzer.service.TextAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private TextAnalyzerService textAnalyzerService;
    @GetMapping("/")
    public String index(Model model){
        File dir = new File("upload/");
        String[] fileName = dir.list();
        List<String> list = new ArrayList<>();
        for(String s: fileName){
            list.add(s);
        }
        model.addAttribute("listFiles", list);
        return "index";
    }

    @PostMapping(value = "/parsing", params = "words")
    public String parsingTex(@RequestParam("path")String path, Model model) throws IOException {
        List<String> list=textAnalyzerService.readFile("C:\\Users\\Sizif\\Downloads\\RestSpringTokinaizer\\TextAnalyzer\\upload\\"+path+".txt");
        model.addAttribute("list",list);
        return "index";
    }

    @PostMapping(value = "/parsing", params = "brackets")
    public String parsingBrackets(@RequestParam("path")String path,Model model) throws IOException {
        String s=textAnalyzerService.parsing("C:\\Users\\Sizif\\Downloads\\RestSpringTokinaizer\\TextAnalyzer\\upload\\"+path+".txt");
        model.addAttribute("s",s);
        return "index";
    }
    @GetMapping("/words/{filename}")
    @ResponseBody
    public String wordsCount(@PathVariable String filename, Model model) throws IOException {
        List list = textAnalyzerService.readFile("C:\\Users\\Sizif\\Downloads\\RestSpringTokinaizer\\TextAnalyzer\\upload\\" + filename+".txt");
//        model.addAttribute("words", list);
//        return "index";
        return String.valueOf(list);
    }
    @GetMapping("/brackets/{filename}")
//    @ResponseBody
    public String brackets(@PathVariable String filename, Model model) throws IOException {
        String s = textAnalyzerService.parsing("C:\\Users\\Sizif\\Downloads\\RestSpringTokinaizer\\TextAnalyzer\\upload\\" + filename+".txt");
        model.addAttribute("brackets", s);
        return "index";
//        return s;
    }


}
