package com.textanalyzer.controller;

import com.textanalyzer.service.TextAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @Autowired
    TextAnalyzerService textAnalyzerService;
    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        byte[] bytes = multipartFile.getBytes();
        Path path = Paths.get("C:\\Users\\Sizif\\Downloads\\RestSpringTokinaizer\\TextAnalyzer\\upload\\" + multipartFile.getOriginalFilename());
//        Charset utf8 = StandardCharsets.UTF_8;
        Files.write(path, bytes);
        return new ResponseEntity(multipartFile.getOriginalFilename(), HttpStatus.OK);
    }
    @GetMapping("/getPath")
    @ResponseBody
    public ResponseEntity<List<String>> getPath(){
        File dir = new File("upload/");
        String[] result = dir.list();
        List<String> list = new ArrayList<>();

        for(String s: result){
            list.add(s);
        }
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }
    @GetMapping("/words/{filename}")
    @ResponseBody
    public ResponseEntity<List> wordsCount(@PathVariable String filename) throws IOException {
        List list = textAnalyzerService.readFile("C:\\Users\\Sizif\\Downloads\\RestSpringTokinaizer\\TextAnalyzer\\upload\\" + filename+".txt");
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }
    @GetMapping("/brackets/{filename}")
    @ResponseBody
    public ResponseEntity<String> brackets(@PathVariable String filename) throws IOException {
        String s = textAnalyzerService.parsing("C:\\Users\\Sizif\\Downloads\\RestSpringTokinaizer\\TextAnalyzer\\upload\\" + filename+".txt");
        return new ResponseEntity<String>(s, HttpStatus.OK);
    }

}
