package com.tjpu22t01.gateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaTokenConfig {
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        SaReactorFilter saReactorFilter = new SaReactorFilter();
        saReactorFilter
                .addInclude("/**")
                .setAuth(obj -> {
                    SaRouter
                           .match("/**","/custom/logic/inout/open/**" , r -> StpUtil.checkLogin());
                })
                .setError(e->{
                    return SaResult.error(e.getMessage());
                });
        return saReactorFilter;
    }
}
