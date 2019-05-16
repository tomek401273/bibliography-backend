package com.tgrajkowski.controllers;

import com.tgrajkowski.model.authors.Publication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@RestController
//@CrossOrigin(origins = "*")
public class Controller {
    @PostMapping("/post")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        System.out.println("Uploaded");
        System.out.println(file.getOriginalFilename());
        try {
//            storageService.store(file);
//            files.add(file.getOriginalFilename());

            byte [] byteArr=file.getBytes();
			InputStream inputStream = new ByteArrayInputStream(byteArr);


            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @RequestMapping(value = "/pub", method = RequestMethod.GET)
    public Publication getPub() {
        return new Publication("THomas", 2019);
    }
}
