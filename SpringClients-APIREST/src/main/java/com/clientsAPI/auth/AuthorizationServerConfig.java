package com.clientsAPI.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@SuppressWarnings("deprecation")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("authenticationManager") //Qualifier nos permite inyectar una instancia especifica del tipo AuthenticationManager por si hemos creado varias
	private AuthenticationManager authenticationManager;

	@Autowired
	private AdditionalInformationToken additionalInfoToken;
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void configure(@SuppressWarnings("deprecation") AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("angularapp")
		.secret(passwordEncoder.encode("12345"))
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(additionalInfoToken, accessTokenConverter() ));
		
		endpoints.authenticationManager(authenticationManager)
		.accessTokenConverter(accessTokenConverter())
		.tokenStore(tokenStore())
		.tokenEnhancer(tokenEnhancerChain);
	}

	@SuppressWarnings("deprecation")
	@Bean 
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@SuppressWarnings("deprecation")
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		@SuppressWarnings("deprecation")
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		//La llave privada es la que firma el token
		jwtAccessTokenConverter.setSigningKey(JwtConfig.RSA_PRIVATE);
		//La llave publica es la que verifica que nuestro token sea auténtico
		jwtAccessTokenConverter.setVerifierKey(JwtConfig.RSA_PUBLIC);
		return jwtAccessTokenConverter;
	}
	
	
}
