package demo1_provider_8083.service;

import demo1_api.entities.User;
import demo1_provider_8083.dao.Userdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: jz
 * Time: 2020/3/14 23:57
 **/
@Service
@Transactional
public class UserService {
    @Autowired(required = false)
    private Userdao userdao;

    public boolean addUser(User user) {
        return userdao.addUser(user);
    }

    public User findById(Integer id) {
        return userdao.findById(id);
    }

    public User findByUsername(String username) {
        return userdao.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userdao.findByEmail(email);
    }

    public User findByPhone(String phone) {
        return userdao.findByPhone(phone);
    }

    public List<User> findAll() {
        return userdao.findAll();
    }

}
