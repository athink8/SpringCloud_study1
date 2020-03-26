package MyRibbonCofig;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * author: jz
 * Time: 2020/3/17 23:12
 * 每个服务访问4次，轮询下一个服务
 **/
public class MyRule1 extends AbstractLoadBalancerRule {
    private int sum = 0;    //总共被调用的次数，目前要求每台被调用3次
    private int currentIndex = 0;//当前提供服务的机器号

    public MyRule1() {
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while (server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();
                List<Server> allList = lb.getAllServers();
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                int index = this.chooseInt(serverCount);
                server = (Server) upList.get(index);
                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    /*选择算法*/
    public int chooseInt(int serverCount) {
        if(currentIndex>=0){
            sum++;
        }
        if(sum>=4){
            sum=0;
            currentIndex++;
        }
        if(currentIndex>=serverCount){
            sum=0;
            currentIndex=0;
        }
        return currentIndex;
    }
}
