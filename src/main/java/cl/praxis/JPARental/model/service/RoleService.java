package cl.praxis.JPARental.model.service;

import cl.praxis.JPARental.model.entities.Role;
import cl.praxis.JPARental.model.entities.User;

import java.util.List;

public interface RoleService {
  List<Role> findAll();
  Role findOne(int id);
  boolean create(Role r);
  boolean update(Role r);
  boolean delete(int id);
}
