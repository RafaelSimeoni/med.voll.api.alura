package med.voll.api.domain.medico.form;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record FormAtualizarMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        FormAtualizarEndereco formAtualizarEndereco
) {
}
