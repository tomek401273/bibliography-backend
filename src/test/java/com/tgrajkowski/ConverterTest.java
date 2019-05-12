package com.tgrajkowski;

import org.junit.Test;

//import com.cloudmersive.client.invoker.ApiClient;
//import com.cloudmersive.client.invoker.ApiException;
//import com.cloudmersive.client.invoker.Configuration;
//import com.cloudmersive.client.invoker.auth.*;
//import com.cloudmersive.client.ConvertDocumentApi;
public class ConverterTest {

 @Test
    public void test1() {
//     File wordFile = new File( ... ), target = new File( ... );
//     IConverter converter = ... ;
//     Future<Boolean> conversion = converter
//             .convert(wordFile).as(DocumentType.MS_WORD)
//             .to(target).as(DocumentType.PDF)
//             .prioritizeWith(1000) // optional
//             .schedule();
 }

 @Test
    public void test2() {
     System.out.println("111");
 }

 @Test
 public void test3() {
     int max = 10;
     int min = 1;
     int range = max-min+1;

     for (int i = 0; i <20; i++) {
         int value = (int)(Math.random()* 100)+1;
         System.out.println(value);
     }
 }
}
