package org.yolkin.service;

import org.yolkin.model.User;
import org.yolkin.repository.EventRepository;
import org.yolkin.repository.UserRepository;
import org.yolkin.repository.hibernate.HibernateEventRepositoryImpl;
import org.yolkin.repository.hibernate.HibernateUserRepositoryImpl;
import org.yolkin.util.ServiceHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static javax.servlet.http.HttpServletResponse.*;


public class UserService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public UserService() {
        userRepository = new HibernateUserRepositoryImpl();
        eventRepository = new HibernateEventRepositoryImpl();
    }

    public UserService(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public User create(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServiceHelper helper = new ServiceHelper(eventRepository, userRepository, resp, req);

        if (helper.userServiceCreateRequestIsCorrect()) {
            return helper.createUser();
        } else {
            return null;
        }
    }

    public User getById(String id, HttpServletResponse resp, HttpServletRequest req) throws IOException {
        ServiceHelper helper = new ServiceHelper(eventRepository, userRepository, resp, req);

        if (helper.userServiceGetByIdRequestIsCorrect(id)) {
            return helper.getUserById();
        } else {
            return null;
        }
    }

    public User update(HttpServletRequest req, HttpServletResponse resp, String mappingUrl) throws IOException {
        ServiceHelper helper = new ServiceHelper(eventRepository, userRepository, resp, req, mappingUrl);

        if(helper.userServiceUpdateRequestIsCorrect()) {
            return helper.updateUser();
        } else {
            return null;
        }
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp, String mappingUrl) throws IOException {
        ServiceHelper helper = new ServiceHelper(eventRepository, userRepository, resp, req, mappingUrl);

        if (helper.userServiceDeleteRequestIsCorrect()) {
           helper.deleteUser();
        }
    }
}