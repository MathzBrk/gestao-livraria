package br.com.livraria.gestao_livraria.service;

import br.com.livraria.gestao_livraria.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken( Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("gestao-livraria")
                    .withSubject(usuario.getLogin())
                    .withClaim("ID", usuario.getId())
                    .withExpiresAt(dataExpericao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token jwt" + exception.getMessage());
        }
    }

    public String getSubject(String token) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    // specify any specific claim validations
                    .withIssuer("gestao-livraria")
                    // reusable verifier instance
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception){
            // Invalid signature/claims
            throw new RuntimeException("Token JWT inválido ou expirado!" + exception.getMessage());
        }
    }

    private Instant dataExpericao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
