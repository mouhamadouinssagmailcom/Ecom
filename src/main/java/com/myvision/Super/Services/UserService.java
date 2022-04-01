package com.myvision.Super.Services;


import com.myvision.Super.Entity.User;

import java.util.Optional;

public interface UserService {
    void save(User user);
    void login(String username, String password);
    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> findById(long id);

}
