package com.optum.clientreportdb.services;

import com.optum.clientreportdb.model.PolicyModel;
import com.optum.clientreportdb.repositories.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Objects;

public class DatabaseExcelService {

    @Autowired
    PolicyRepository policyRepository;

    public DatabaseExcelService() {

    }
    private JdbcTemplate getJDBCTemplate() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/hsdp_pg");
        dataSource.setUsername("tFVZtSOz9vXfQGsW");
        dataSource.setPassword("xRUCdchCNdlKf4Lc");

        JdbcTemplate template = new JdbcTemplate(dataSource);
        return  template;
    }
}
