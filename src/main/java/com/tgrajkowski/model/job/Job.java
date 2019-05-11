package com.tgrajkowski.model.job;

import com.tgrajkowski.user.Users;
import lombok.*;

import javax.persistence.*;
import java.util.Date;



@SqlResultSetMapping(name = JobDaoIml.STATEMENT_SQLMAP, classes = {
        @ConstructorResult(targetClass = JobDto.class,
                columns = {

                        @ColumnResult(name = "date_trunc", type = Date.class),
                        @ColumnResult(name = "count", type = Integer.class)
                }
        )
})


@Entity
@Table(name = "jobs")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Date date;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private Users users;

}
