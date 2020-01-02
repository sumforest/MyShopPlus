package com.sen.myshop.plus.business.configure;

import io.lettuce.core.support.RedisClientFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

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

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

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
        //基于数据库存储token
        // return new JdbcTokenStore(dataSource());
        //基于redis存储token
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 用于密码模式
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                //指定数据库token的存储位置
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

    /**
     * 基于数据库实现客户端配置
     * @return
     */
    @Bean
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource());
    }

    /**
     * 配置客户端
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //基于数据库管理token
        clients.withClientDetails(clientDetailsService());

        //以下是基于内存管理token
        /*clients.inMemory()
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
                .refreshTokenValiditySeconds(60 * 60 * 24 * 30);*/
    }


}
