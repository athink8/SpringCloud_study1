package demo1_zuul.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

@Component
public class MyFallbackProvider implements FallbackProvider {

    /**
     * getRoute方法的返回值就是要监听的挂掉的微服务的名字
     * 如果需要所有服务都走这个熔断回退，则return "*" 或 return null
     *
     * @return
     */
    @Override
    public String getRoute() {
        return "*";
    }

    /**
     * 当服务无法执行的时候，返回托底信息
     *
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        return new ClientHttpResponse() {

            /**
             * ClientHttpResponse的fallback的状态码
             * @return
             * @throws IOException
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            /**
             * ClientHttpResponse的fallback的状态码
             * @return
             * @throws IOException
             */
            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            /**
             * ClientHttpResponse的fallback的状态码
             * @return
             * @throws IOException
             */
            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            /**
             * Close this response, freeing any resources created.
             */
            @Override
            public void close() {

            }

            /**
             * 设置响应体
             * @return
             * @throws IOException
             */
            @Override
            public InputStream getBody() throws IOException {
                String content = "该服务不可用!!";
                return new ByteArrayInputStream(content.getBytes());
            }

            /**
             * 设置响应头信息
             * @return
             */
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("utf-8"));
                headers.setContentType(mt);

                return headers;
            }
        };
    }
}