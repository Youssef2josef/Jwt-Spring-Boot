package com.youssef.apisecurity.user;

import java.util.List;

public interface OperationService {
    public User addOneUser(User p);
    public List<User> getAllUsers();

    public User getUserById(Integer id);


    public User updateOneUser(User p);

    public void deleteUser(Integer id);

}
