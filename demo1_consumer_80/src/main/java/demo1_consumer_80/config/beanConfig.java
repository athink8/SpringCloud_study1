package demo1_consumer_80.config;

        import org.springframework.cloud.client.loadbalancer.LoadBalanced;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.client.RestTemplate;

/**
 * author: jz
 * Time: 2020/3/15 1:54
 **/
@Configuration
public class beanConfig {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

}
