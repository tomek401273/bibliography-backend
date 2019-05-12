package com.tgrajkowski.service.ODT2PDF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MapProvider {
    public static Map<String, String> getProjectMap() {
        Map<String, String> project = new HashMap<String, String>();
        project.put("Name", "Test");
        project.put("HomePage", "http://www.test.pl");
        project.put("Developer", "test.com");
        return project;
    }


    public static List<Map<String, String>> getDevelopers() {
        List<Map<String, String>> developers = new ArrayList<>();
        Map<String, String> developer1 = new HashMap<>();
        developer1.put("Name", "M");
        developer1.put("LastName", "K");
        developer1.put("Mail", "m.com.");
        developers.add(developer1);
        return developers;
    }
}
