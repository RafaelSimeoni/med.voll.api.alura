package med.voll.api.domain.medico.form;

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
