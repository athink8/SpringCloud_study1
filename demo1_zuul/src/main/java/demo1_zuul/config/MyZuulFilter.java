package demo1_zuul.config;

        import com.netflix.zuul.ZuulFilter;
        import com.netflix.zuul.context.RequestContext;
        import org.springframework.stereotype.Component;

        import javax.servlet.http.HttpServletRequest;

/**
 * 网关认证过滤器（Demo演示，实际根据自身业务考虑实现）
 *
 * @author jz
 * @date 2020-03-20.
 */
@Component
public class MyZuulFilter extends ZuulFilter {

    /**
     * per：路由之前，如实现认证、记录调试信息等
     * routing：路由时
     * post：路由后，比如添加HTTP Header
     * error：发生错误时调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器顺序，类似@Filter中的order
     */
    @Override
    public int filterOrder() {
        return 20;
    }

    /**
     * 这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     */
    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        System.out.println("getServerName "+request.getServerName()); //localhost
        System.out.println("getRequestURL "+request.getRequestURL()); //http://localhost:9999/p1/user/all
        System.out.println("getRequestURI "+request.getRequestURI()); //p1/user/all
        System.out.println("getServerPort "+request.getServerPort()); //9999
        System.out.println("authToken "+request.getHeader("authToken")); //获取请求头信息

        String token = request.getParameter("token");
        if (token == null) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(404);
            requestContext.setResponseBody("token cannot be empty");
        }
        requestContext.setSendZuulResponse(true);
        return null;
    }
}