package demo1_consumer_feign.FeignClientService;

import demo1_api.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author: jz
 * Time: 2020/3/18 20:34
 **/
@Component
public class Hystrixfallback implements UserClientService {
    @Override
    public boolean addUser(User user) {
        return false;
    }

    @Override
    public User findById(Integer id) {
        return new User().setName("Hystrix错误消息").setId(id);
    }

    @Override
    public User findByUsername(String username) {
        return new User().setName(username).setCode("Hystrix错误消息");
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByPhone(String phone) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Object discovery1() {
        return null;
    }
}
