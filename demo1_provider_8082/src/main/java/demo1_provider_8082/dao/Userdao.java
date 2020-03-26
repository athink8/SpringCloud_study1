package demo1_provider_8082.dao;

import demo1_api.entities.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * author: jz
 * Time: 2020/3/14 23:30
 **/
@Mapper
public interface Userdao {
    public boolean addUser(User user);

    public User findById(Integer id);

    public User findByUsername(String username);

    public User findByEmail(String email);

    public User findByPhone(String phone);

    public List<User> findAll();
}
