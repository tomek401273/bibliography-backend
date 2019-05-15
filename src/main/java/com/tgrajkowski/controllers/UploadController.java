package com.tgrajkowski.controllers;

import com.tgrajkowski.model.BibliographyException;
import com.tgrajkowski.model.ReturnMainObject;
import com.tgrajkowski.service.SortingService;
import com.tgrajkowski.service.WordToPdfService;
import com.tgrajkowski.service.file.CheckFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/upload")
@CrossOrigin(origins = "*")
public class UploadController {
    @Autowired
    private CheckFileService checkFileService;

    @Autowired
    private WordToPdfService wordToPdfService;

    @Autowired
    private SortingService sortingService;


    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public ReturnMainObject uploadFile(@RequestParam("file") MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws Exception {
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
    public String getMthod() {
        return "Logic";
    }

    @RequestMapping(value = "/docx/to/pdf", method = RequestMethod.POST)
    public void convertDoxToPdf(@RequestParam("file") MultipartFile multipartFile, HttpServletResponse response) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = wordToPdfService.convertDocxToPdf(multipartFile);
        response.setContentType("application/pdf");
        response.setContentLength(byteArrayOutputStream.toByteArray().length);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "converted.pdf" + "\"");
        FileCopyUtils.copy(byteArrayOutputStream.toByteArray(), response.getOutputStream());
    }

    @RequestMapping(value = "/bibliography/order", method = RequestMethod.POST)
    public void orderBibliography(@RequestParam("file") MultipartFile multipartFile, HttpServletResponse response) throws IOException, BibliographyException {
        ByteArrayOutputStream byteArrayOutputStream = sortingService.orderBibliography(multipartFile);
        response.setContentLength(byteArrayOutputStream.toByteArray().length);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "bib.txt" + "\"");
        FileCopyUtils.copy(byteArrayOutputStream.toByteArray(), response.getOutputStream());
    }
}
