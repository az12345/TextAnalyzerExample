package com.textanalyzer.controller;

import com.textanalyzer.service.TextAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private TextAnalyzerService textAnalyzerService;
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @PostMapping(value = "/parsing", params = "words")
    public String parsingTex(@RequestParam("path")String path, Model model) throws IOException {
        List<String> list=textAnalyzerService.readFile(path);
        model.addAttribute("list",list);
        return "index";
    }

    @PostMapping(value = "/parsing", params = "brackets")
    public String parsingBrackets(@RequestParam("path")String path,Model model) throws IOException {
        String s=textAnalyzerService.parsing(path);
        model.addAttribute("s",s);
        return "index";
    }
}
