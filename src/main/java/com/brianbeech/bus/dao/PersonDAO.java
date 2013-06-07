package com.brianbeech.bus.dao;

import com.brianbeech.bus.dao.mappers.PersonRowMapper;
import com.brianbeech.bus.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 4/26/13
 * Time: 1:50 PM
 */
@Service("PersonDAO")
@Transactional
public class PersonDAO {
    @Autowired
    private DataSource dataSource;
    @Autowired @Qualifier("personRowMapper")
    private PersonRowMapper personRowMapper;

    private static JdbcTemplate jdbcTemplate;

    public DataSource getDataSource() {
        return dataSource;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Person> getAllPeople(){
        return jdbcTemplate.query("select max(c.contact_datetime) as contact_datetime,p.person_id, p.first_name, p.last_name, p.middle_name,p.date_of_birth,p.date_membership_began, a.street_1,a.street_2,"+
                " a.city, a.state, a.zip, p.sex"+
                " FROM PERSONS p left join addresses a on p.person_id = a.person_id left join contacts c on p.person_id = c.person_id group by p.person_id", personRowMapper);
    }

    public void addPerson(Person person){
        KeyHolder key = new GeneratedKeyHolder();
        String query = "";
        /*jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps =
                        connection.prepareStatement(query, new String[] {"id"});
                ps.setString(1, person.getFirstName());
                return ps;
            }
        },key);*/
    }

    public Map<String,String> getPersonsByName(String inputName) {
        String query = "select * FROM Persons p left join addresses a on p.person_id = a.person_id WHERE p.first_Name like '%"+ inputName + "%' or p.last_Name like '%"+ inputName + "%'";
        Map<String,String> idNameMap = new HashMap<String, String>();
        for(Person p : (List<Person>) jdbcTemplate.query(query, personRowMapper)){
            idNameMap.put(String.valueOf(p.getPersonId()),p.getFirstName() + " " + p.getLastName());
        }
        return idNameMap;
    }

    public Person getPersonById(int personId){
        String query = "select max(c.contact_datetime) as contact_datetime,p.person_id, p.first_name, p.last_name, p.middle_name,p.date_of_birth,p.date_membership_began, a.street_1,a.street_2, " +
                "a.city, a.state, a.zip, p.sex from persons p left join addresses a on p.person_id = a.person_id " +
                "left join contacts c on p.person_id = c.person_id where p.person_id = " + personId + ";";
        return (Person) jdbcTemplate.queryForObject(query, personRowMapper);
    }

    public void addContactDateForPerson(int personId){
        String query = "INSERT INTO CONTACTS(PERSON_ID,CONTACT_DATETIME,ACTIVE) VALUES("+personId+",now(),1);";
        jdbcTemplate.execute(query);
    }

    public String getLatestContactDateForPerson(int personId){
        String query = "SELECT MAX(CONTACT_DATETIME) AS CONTACT_DATETIME FROM CONTACTS WHERE PERSON_ID = " + personId;
        return jdbcTemplate.queryForObject(query,String.class);
    }
}
