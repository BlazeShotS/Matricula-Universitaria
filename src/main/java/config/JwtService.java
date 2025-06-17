package config;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    //Generar un token de acceso
    public String generateToken (UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String, Object>extraClaims, 
    UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtConfig.getTokenExpirationInMillis());
    }


}



