package com.clientsAPI.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.clientsAPI.models.entity.User;
import com.clientsAPI.models.services.IUserService;

@SuppressWarnings("deprecation")
@Component
public class AdditionalInformationToken implements TokenEnhancer{

	@Autowired
	private IUserService userService;
	
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
			
		User user = userService.findByUserName(authentication.getName());
		
		Map<String, Object> info = new HashMap<>();
		//Podemos obtener username directamente de authentication con getUserName()
		info.put("additionalInfo", "Hello" + user.getUsername());
		//La interfaz OAuth2AccessToken no tiene el metodo set por lo que los accedemos a través de la implementación concreta
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		return accessToken;
	}

}
