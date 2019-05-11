package com.tgrajkowski.model.job;

import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class JobDtos {
    public List<String> dateList;
    private List<Integer> counts;
}
