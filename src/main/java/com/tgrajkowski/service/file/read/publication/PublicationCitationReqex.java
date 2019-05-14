package com.tgrajkowski.service.file.read.publication;

import java.util.ArrayList;
import java.util.List;

public class PublicationCitationReqex {
    public static final List<String> regexList = new ArrayList<>(List.of(
            "\\w+\\s[0-9]{4,4}",
            "\\w+\\s[i]\\s\\w+\\s[0-9]{4,4}",
            "\\w+\\s\\w+\\s\\w+\\s[0-9]{4,4}",
            "\\w+\\s[i]\\s[i][n][.]\\s[0-9]{4,4}"
    ));
}
