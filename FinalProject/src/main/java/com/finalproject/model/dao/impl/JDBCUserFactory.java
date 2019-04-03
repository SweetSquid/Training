package com.finalproject.model.dao.impl;

import com.finalproject.model.dao.UserDao;
import com.finalproject.model.dao.mapper.UserMapper;
import com.finalproject.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCUserFactory implements UserDao {

    private Connection connection;

    public JDBCUserFactory(Connection connection) {
        this.connection = connection;
    }


    @Override
    public boolean create(User user) {
        UserMapper userMapper = new UserMapper();
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(NotebookSQL.ADD.QUERY);
            preparedStatement.setString(1, user.getRole().toString());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            result = preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User readId(int id) {
        return null;
    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {

        Optional<User> result = Optional.empty();

        String query = "SELECT * FROM users WHERE username = ?";

        try(PreparedStatement ps = connection.prepareCall(query)) {
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            UserMapper userMapper = new UserMapper();

            if (rs.next()) {
                result = Optional.of(userMapper.extractFromResultSet(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    enum NotebookSQL {
        READ("SELECT login, name FROM users WHERE login = (?)"),
        DELETE("DELETE FROM users WHERE login = (?)"),
        ADD("INSERT INTO users (id, role, name, username, email, password) VALUES (DEFAULT, (?), (?), (?), (?), (?))"),
        UPDATE("UPDATE users SET login = (?), name = (?) WHERE login = (?)"),
        GET_ALL("SELECT name, login FROM users");

        String QUERY;

        NotebookSQL(String QUERY) {
            this.QUERY = QUERY;
        }
    }

}