package com.tgrajkowski.controllers;

import com.tgrajkowski.model.ReturnMainObject;
import com.tgrajkowski.model.UploadStatus;
import com.tgrajkowski.service.file.CheckFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@RestController
@RequestMapping(value = "/upload")
@CrossOrigin(origins = "*")
public class UploadController {
    @Autowired
    private CheckFileService checkFileService;

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ReturnMainObject uploadFile(@RequestParam("file")MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws Exception {
        System.out.println(multipartFile.getOriginalFilename());
        ReturnMainObject returnMainObject = new ReturnMainObject();
        try {
            returnMainObject = checkFileService.readFile(multipartFile);
        } catch (IOException e) {
//            return new UploadStatus(false, "File Upload fail", e.getMessage());
        }
//        return new UploadStatus(true, message);
        return returnMainObject;
    }

    @RequestMapping(value = "/l", method = RequestMethod.GET)
    public String getMthod () {
        return "Logic";
    }
}
