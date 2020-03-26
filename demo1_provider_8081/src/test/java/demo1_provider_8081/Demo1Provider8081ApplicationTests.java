package demo1_provider_8081;

import demo1_provider_8081.dao.Userdao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo1Provider8081ApplicationTests {
    @Autowired(required = false)
    private Userdao userdao;

    @Test
    void contextLoads() {
    }

}
