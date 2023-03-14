package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.dto.FormAutenticacao;
import med.voll.api.infra.security.TokenJWTDTO;
import med.voll.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenJWTDTO> logar(@RequestBody @Valid FormAutenticacao formAutenticacao) {
        //A classe AuthenticationManager chama o método de AutenticacaoService por baixo dos panos
        var token = new UsernamePasswordAuthenticationToken(formAutenticacao.login(), formAutenticacao.senha());
        var authentication = authenticationManager.authenticate(token);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }
}
