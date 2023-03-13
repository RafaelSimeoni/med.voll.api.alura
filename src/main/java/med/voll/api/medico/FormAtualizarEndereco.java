package med.voll.api.medico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record FormAtualizarEndereco(
        String logradouro,
        String bairro,

        @Pattern(regexp = "\\d{8}")
        String cep,

        String cidade,

        String uf,
        String complemento,
        String numero)
{}
