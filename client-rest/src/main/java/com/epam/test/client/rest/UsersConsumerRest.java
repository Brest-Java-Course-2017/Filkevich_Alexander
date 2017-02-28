package com.epam.test.client.rest;

import com.epam.test.client.exception.ServerDataAccessException;
import com.epam.test.client.rest.api.UsersConsumer;
import com.epam.test.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by master on 28.2.17.
 */
public class UsersConsumerRest implements UsersConsumer {

    private String hostUrl;
    private String urlUsers;
    private String urlUser;

    RestTemplate restTemplate;

    public UsersConsumerRest(String hostUrl, String urlUsers, String urlUSer) {
        this.hostUrl = hostUrl;
        this.urlUsers = urlUsers;
        this.urlUser = urlUSer;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getAllUsers() throws ServerDataAccessException {
        ResponseEntity responseEntity=restTemplate.getForEntity(hostUrl+"/"+urlUsers,List.class);
        List<User> users=(List<User>) responseEntity.getBody();
        return users;
    }

    @Override
    public User getUserById(Integer id) throws ServerDataAccessException {
        return null;
    }

    @Override
    public User getUserByLogin(String login) throws ServerDataAccessException {
        ResponseEntity responseEntity=restTemplate.getForEntity(hostUrl+"/"+urlUser+"/"+login,User.class);
        Object user=responseEntity.getBody();
        return (User) user;
    }

    @Override
    public Integer addUser(User user) throws ServerDataAccessException {
        return null;
    }

    @Override
    public int updateUser(User user) throws ServerDataAccessException {
        return 0;
    }

    @Override
    public int deleteUser(Integer id) throws ServerDataAccessException {
        return 0;
    }
}