package cl.praxis.JPARental.model.service;

import cl.praxis.JPARental.model.entities.User;

import java.util.List;

public interface UserService {
  List<User> findAll();
  User findOne(int id);
  boolean create(User u);
  boolean update(User u);
  boolean delete(int id);
}
