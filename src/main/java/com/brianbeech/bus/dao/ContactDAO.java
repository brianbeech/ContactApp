package com.brianbeech.bus.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 5/28/13
 * Time: 12:07 PM
 */
@Service("ContactDAO")
@Transactional
public class ContactDAO {
    @Autowired
    private DataSource dataSource;
    private static JdbcTemplate jdbcTemplate;
    //@Value('${membersVisited}')
    private static String membersVisitedThisMonth = "SELECT COUNT(*) FROM CONTACTS WHERE PERSON_ID IN (SELECT PERSON_ID FROM PERSONS WHERE DATE_MEMBERSHIP_BEGAN IS NOT NULL AND DATE_MEMBERSHIP_ENDED IS NULL)";

    public DataSource getDataSource() {
        return dataSource;
    }
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int getNumberOfVisitsThisMonth(){
        return jdbcTemplate.queryForInt("SELECT COUNT(distinct person_id) FROM CONTACTS WHERE MONTH(CONTACT_DATETIME) = MONTH(NOW())");
    }

    public int getNumberOfInactiveMemberVisitsThisMonth(){
        return jdbcTemplate.queryForInt("select 1");
    }

    public int getNumberOfVisitorsVisitedThisMonth(){
        return jdbcTemplate.queryForInt("select 1");
    }

    public int getNumberOfMembersVisitedThisMonth(){
        return jdbcTemplate.queryForInt(membersVisitedThisMonth);
    }
}
