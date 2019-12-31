package com.sen.myshop.plus.business.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.sql.DataSource;

/**
 * @Auther: Sen
 * @Date: 2019/12/28 23:35
 * @Description: 认证服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 配置数据源
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 注入tokenStore，指定token的存储位置
     * @return
     */
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

    /**
     * 用于密码模式
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                //指定token的存储位置
                .tokenStore(tokenStore());
    }

    /**
     * 允许客户端访问/oauth/check_token路径检查token
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //客户端Id
                .withClient("client")
                //客户端密钥
                .secret(passwordEncoder.encode("secret"))
                //授权类型
                .authorizedGrantTypes("password", "refresh_token")
                //授权范围
                .scopes("backend")
                //设置资源的访问权限，不设置则全部资源都可以访问
                .resourceIds("backend-resources")
                //设置访问令牌的有效时间为1天
                .accessTokenValiditySeconds(60 * 60 * 24)
                //设置刷新令牌的时间为30天
                .refreshTokenValiditySeconds(60 * 60 * 24 * 30);
    }


}
