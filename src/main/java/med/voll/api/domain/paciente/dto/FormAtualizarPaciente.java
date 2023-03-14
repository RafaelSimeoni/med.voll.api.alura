package med.voll.api.domain.paciente.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.dto.FormAtualizarEndereco;

public record FormAtualizarPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        FormAtualizarEndereco endereco) {
}
