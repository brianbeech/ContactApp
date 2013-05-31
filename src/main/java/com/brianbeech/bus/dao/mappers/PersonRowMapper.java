package com.brianbeech.bus.dao.mappers;

import com.brianbeech.bus.domain.Person;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 4/26/13
 * Time: 1:57 PM
 */
@Service(value = "personRowMapper")
public class PersonRowMapper implements RowMapper {
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setPersonId(resultSet.getInt("PERSON_ID"));
        person.setFirstName(resultSet.getString("FIRST_NAME"));
        person.setLastName(resultSet.getString("LAST_NAME"));
        person.setSex(resultSet.getString("SEX"));
        person.setDob(resultSet.getDate("DATE_OF_BIRTH"));
        person.setStreet1(resultSet.getString("street_1"));
        person.setStreet2(resultSet.getString("STREET_2"));
        person.setCity(resultSet.getString("CITY"));
        person.setState(resultSet.getString("STATE"));
        person.setZipCode(resultSet.getString("ZIP"));
        return person;
    }
}
