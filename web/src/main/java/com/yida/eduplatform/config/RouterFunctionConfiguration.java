package com.yida.eduplatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import java.util.Collection;

/**
 * 路由器函数
 */
@Configuration
public class RouterFunctionConfiguration {

    /**
     * Servlet
     * 请求接口：ServletRequest 或者 HttpSevletRequest
     * 响应接口：ServletResponse 或者 HttpServletResponse
     * Spring 5.0 重新定义了服务请求和响应接口：
     * 请求接口：ServletRequest
     * 响应接口：ServletResponse
     * 既可以支持 Servlet规范，也可以支持自定义，比如Netty（Web Server）
     * 以本例：
     * 定义Get请求，并且返回所有的用户对象URI:/person/find/all
     * Flux是0-》N个对象集合
     * Mono是0-》1个对象集合
     * reactive中的Flux或者Mono它是异步处理（非阻塞）
     * 集合对象基本上是同步处理（阻塞）
     * Flux 或者 Mono 都是 Publisher
     */
//    @Bean
//    @Autowired
//    public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository){
//        System.out.print("personFindAll----------------");
//        return RouterFunctions.route(RequestPredicates.GET("/person/find/all"),
//            request ->{
//                Collection<User> users = userRepository.findAll();
//                Flux<User> userFlux = Flux.fromIterable(users);
//                return ServerResponse.ok().body(userFlux, User.class);
//            });
//    }
}
