package com.tgrajkowski.model.job;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JobsForDay {
    private long date;
    private int count;
}

