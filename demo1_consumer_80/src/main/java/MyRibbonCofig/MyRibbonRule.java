package MyRibbonCofig;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * author: jz
 * Time: 2020/3/17 22:49
 **/
@Configuration
public class MyRibbonRule {
    @Bean
    public IRule myRuleConfig() {
//        return new RandomRule();//Ribbon默认是轮询，我自定义为随机
        return new MyRule1();
    }
}
