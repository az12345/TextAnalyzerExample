package com.textanalyzer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file")MultipartFile multipartFile){
        try {
            byte[] bytes =multipartFile.getBytes();
            Path path = Paths.get("C:\\Users\\Sizif\\Downloads\\RestSpringTokinaizer\\TextAnalyzer" + multipartFile.getOriginalFilename());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(multipartFile.getOriginalFilename(), HttpStatus.OK);
    }
}
