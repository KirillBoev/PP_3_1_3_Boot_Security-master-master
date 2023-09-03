package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<User> showUsers();

    void save(User user);

    User getUser(Long id);

    void delete(Long id);

    void update(User user);

    User getUserByEmail(String email);

    List<Role> listRoles();
}