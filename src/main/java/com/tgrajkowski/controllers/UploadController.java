package com.tgrajkowski.controllers;

import com.tgrajkowski.model.UploadStatus;
import com.tgrajkowski.service.ReadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping(value = "/upload")
@CrossOrigin(origins = "*")
public class UploadController {
    @Autowired
    private ReadFileService readFileService;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public UploadStatus uploadFile(@RequestParam("file")MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws Exception {
        try {
            readFileService.readFile(multipartFile);
        } catch (IOException e) {
            return new UploadStatus(false, "File Upload fail", e.getMessage());
        }
        return new UploadStatus(true, "File upload success");
    }
}
