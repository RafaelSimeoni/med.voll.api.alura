package med.voll.api.domain.medico.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.dto.FormAtualizarEndereco;

public record FormAtualizarMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        FormAtualizarEndereco formAtualizarEndereco
) {
}
