package com.brianbeech.bus.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Brian
 * Date: 5/27/13
 * Time: 8:38 PM
 */
public class PersonPreparedStatementCreator implements PreparedStatementCreator {
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        return null;
    }


}
