package com.youssef.apisecurity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService{

    @Autowired
    private UserRepository userRepo;

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        // TODO Auto-generated method stub
        Optional<User> m = userRepo.findById(id);
        return m.isPresent() ? m.get() : null;
    }

    @Override
    public void deleteUser(Integer id) {
        // TODO Auto-generated method stub
        userRepo.deleteById(id);
    }

    @Override
    public User addOneUser(User m) {
        // TODO Auto-generated method stub
        return userRepo.save(m);
    }

    @Override
    public User updateOneUser(User m) {
        // TODO Auto-generated method stub
        return userRepo.save(m);
    }

}
