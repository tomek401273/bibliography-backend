package com.tgrajkowski.model.job;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class JobsDaily {
    public List<String> dateList;
    private List<Integer> counts;
}
