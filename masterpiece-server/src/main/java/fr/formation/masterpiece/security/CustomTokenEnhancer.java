package fr.formation.masterpiece.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {

    final static String USER_ID_KEY = "userId";

    /**
     * Method to enhance the token by adding additional informations inside.
     * 
     * @return the accesToken with additional info
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
            OAuth2Authentication authentication) {
	// Adding more information into token
	Map<String, Object> additionalInfo = new HashMap<>();
	CustomUserDetails user = (CustomUserDetails) authentication
	        .getPrincipal();
	additionalInfo.put(USER_ID_KEY, user.getId());
	((DefaultOAuth2AccessToken) accessToken)
	        .setAdditionalInformation(additionalInfo);
	return accessToken;
    }
}