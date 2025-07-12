package com.example.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponse(
    //Cuando inicio sesion , me genera tokens y devueve id_usuario , nombre y rol

    @JsonProperty ("access_token") String accessToken,
    @JsonProperty ("refresh_token") String refreshToken,
    Integer id_usuario,
    String nombre,
    String rol
    
) {

}
