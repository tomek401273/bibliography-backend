package com.tgrajkowski.model.job;

import org.apache.poi.hssf.record.formula.functions.Int;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Repository
public class JobDaoImpl {
    @PersistenceContext
    private EntityManager em;

    static final String STATEMENT_SQLMAP = "Statement-SQL-Mapping";

    public List<JobDto> findPipelinedStatements() {
        Query query = em.createNativeQuery(
                "select date_trunc('day', date), count(id) from jobs group by date_trunc('day', date)",
                STATEMENT_SQLMAP);
        return query.getResultList();
    }
}
