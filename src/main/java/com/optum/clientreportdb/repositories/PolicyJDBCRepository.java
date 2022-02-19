package com.optum.clientreportdb.repositories;

import com.optum.clientreportdb.model.PolicyModel;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

@Repository
public class PolicyJDBCRepository implements PolicyRepository{


    private JdbcTemplate jdbcTemplate = getJDBCTemplate();

    @Override
    public int save(PolicyModel policyModel) {

        System.out.println("INSERTING the records......");

        String query = "INSERT INTO CLIENTREPODB (id, client, start_date, " +
                "end_date, lpr, policy_number, is_active) VALUES(?,?,?,?,?,?,?)";

        return jdbcTemplate.update(query,
                policyModel.getId(), policyModel.getClient(), policyModel.getStartDate(), policyModel.getEndDate(),
                policyModel.getLprNumber(), policyModel.getPolicyNumber(), policyModel.getIsActive());
    }

    @Override
    public int update(PolicyModel policyModel) {
        System.out.println("Updating the records......");
        String query = "UPDATE CLIENTREPODB SET lpr = ?, client = ?, is_active = ? where policy_number = ?";
        return jdbcTemplate.update(query,new Object[]{policyModel.getLprNumber(), policyModel.getClient(),
                policyModel.getIsActive(), policyModel.getPolicyNumber()});

    }

    @Override
    public PolicyModel findByPolicyNumber(String policyNumber) {
        try {
            System.out.println("FIND Policy Number......"+policyNumber);
            String query = "SELECT * FROM clientrepodb WHERE policy_number=?";
            PolicyModel policyModel = jdbcTemplate.queryForObject(query,
                    BeanPropertyRowMapper.newInstance(PolicyModel.class), policyNumber);
            System.out.println("Policy policyModel......"+policyModel);
            return policyModel;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(int policyNumber) {
        return 0;
    }

    private JdbcTemplate getJDBCTemplate() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl("jdbc:postgresql://localhost:5432/hsdp_pg");
        dataSource.setUsername("tFVZtSOz9vXfQGsW");
        dataSource.setPassword("lAetr1xPKeETRZcc");

        JdbcTemplate template = new JdbcTemplate(dataSource);
        return  template;
    }
}
