package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll med") //quem gerou o token
                    .withSubject(usuario.getLogin()) //adiciona o usuário ao token
                    .withClaim("ID", usuario.getId()) //aqui dá pra adicionar vários atributos do usuário que eu queira
                    .withExpiresAt(dataExpiracao()) //data da expiração
                    .sign(algoritmo); //assinatura
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Erro ao gerar token - " + ex);
        }
    }

    public String getSubject(String tokenJWT) {
        //validar o token
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Voll med") //verifica se o Issuer é "API Voll med"
                    .build()
                    .verify(tokenJWT) //Verifica se o token está valido
                    .getSubject();
        } catch (JWTVerificationException ex) {
            //token inválido ou expirado
            throw new RuntimeException("Token JWT inválido ou expirado");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
