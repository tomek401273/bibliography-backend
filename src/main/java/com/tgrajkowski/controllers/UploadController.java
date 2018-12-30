package com.tgrajkowski.controllers;

import com.tgrajkowski.model.UploadStatus;
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

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public UploadStatus uploadFile(@RequestParam("file")MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws Exception {
//        Resource resource = new ClassPathResource("../classes/temp/");
//        String temp = resource.getURI().getPath();
        String temp = "temp/"+multipartFile.getOriginalFilename();

        try (InputStream inputStream = multipartFile.getInputStream()){

//            System.out.println(temp);
//            temp = URLEncoder.encode(temp, "UTF-8");
//            temp = temp + multipartFile.getOriginalFilename();
//            temp = temp.replaceAll(" ", "%20");
//            System.out.println(temp);

            Files.copy(inputStream, Paths.get(temp), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new UploadStatus(false, "File Upload fail", e.getMessage());
        }
        return new UploadStatus(true, "File upload success");
    }
}
