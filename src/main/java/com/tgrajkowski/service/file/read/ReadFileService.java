package com.tgrajkowski.service.file.read;

import com.tgrajkowski.model.BibliographyException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReadFileService {
    List<String> readFile(MultipartFile multipartFile) throws BibliographyException;
}
