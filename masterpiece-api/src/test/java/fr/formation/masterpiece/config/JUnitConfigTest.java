package fr.formation.masterpiece.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.masterpiece.config.security.CustomUserDetails;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles(profiles = "test")
public class JUnitConfigTest {

    @Autowired
    protected ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json()
            .build();

    @Autowired
    protected ModelMapper modelMapper;

    protected final <S, D> D dtoConvert(S inputs, Class<D> destinationType) {
	return modelMapper.map(inputs, destinationType);
    }

    protected final <D> D jsonConvert(String inputs, Class<D> destinationType) {
	D converted = null;
	try {
	    converted = objectMapper.readValue(inputs, destinationType);
	} catch (IOException ex) {
	    throw new IllegalArgumentException("wrong json string", ex);
	}
	return converted;
    }

    @BeforeAll
    static void setupSecurityContext() {
	SecurityContext context = SecurityContextHolder.createEmptyContext();
	List<GrantedAuthority> authorities = new ArrayList<>();
	Set<String> groups = new HashSet<>();
	groups.add("ROLE_USER");
	for (String group : groups) {
	    authorities.add(new SimpleGrantedAuthority(group));
	}
	User user = new User("Tomas", "Totototo9!", authorities);
	CustomUserDetails userDetails = new CustomUserDetails(user, 1L);
	Authentication auth = new UsernamePasswordAuthenticationToken(
	        userDetails, "Totototo9!", userDetails.getAuthorities());
	OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth
	        .getDetails();
	Map<String, Object> additionalInfo = new HashMap<>();
	additionalInfo.put("userId", 1L);
	details.setDecodedDetails(additionalInfo);
	context.setAuthentication(auth);
	UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
	        userDetails, "PassWord");
	SecurityContextHolder.getContext().setAuthentication(token);
    }
}
