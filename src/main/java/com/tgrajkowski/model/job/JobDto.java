package com.tgrajkowski.model.job;

import lombok.*;
import java.util.Date;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private Date date;
    private Integer count;
}
