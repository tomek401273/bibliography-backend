package com.tgrajkowski;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class PDF {
    public static void main(String[] args) throws Exception {
        String inputFile="/home/tomek/Documents/samples2/bibliography-backend/word.doc";
        String outputFile="/home/tomek/Documents/samples2/bibliography-backend/word.pdf";
        if (args != null && args.length == 2) {
            inputFile=args[0];
            outputFile=args[1];
        }
        System.out.println("inputFile:" + inputFile + ",outputFile:"+ outputFile);
        FileInputStream in=new FileInputStream(inputFile);
        XWPFDocument document=new XWPFDocument(in);
        File outFile=new File(outputFile);
        OutputStream out=new FileOutputStream(outFile);
        PdfOptions options = PdfOptions.create().fontEncoding("iso-8859-15");
        PdfConverter.getInstance().convert(document,out,options);
    }
}
