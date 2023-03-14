package med.voll.api.domain.usuario.repository.form;

import jakarta.validation.constraints.NotBlank;

public record FormAutenticacao(
        @NotBlank
        String login,
        @NotBlank
        String senha) {
}
