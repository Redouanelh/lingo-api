package com.hu.lingo.trainer.application;

import com.hu.lingo.trainer.data.UserRepository;
import com.hu.lingo.trainer.domain.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService extends BaseService<User> {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> allUsers() {
        return this.userRepository.findAll();
    }
}
