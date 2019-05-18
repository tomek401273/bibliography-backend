package com.tgrajkowski.service;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class WordToPdfService {
    public ByteArrayOutputStream convertDocxToPdf(MultipartFile multipartFile) throws IOException {
        XWPFDocument document = new XWPFDocument(multipartFile.getInputStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfOptions options = PdfOptions.create().fontEncoding("windows-1250");
        PdfConverter.getInstance().convert(document, byteArrayOutputStream, options);

        return byteArrayOutputStream;
    }
}
