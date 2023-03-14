package med.voll.api.domain.medico.dto;

import med.voll.api.domain.endereco.dto.EnderecoDetalhamentoDTO;
import med.voll.api.domain.medico.Especialidade;
import med.voll.api.domain.medico.Medico;

public record MedicoDetalhamentoDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        EnderecoDetalhamentoDTO endereco
) {
    public MedicoDetalhamentoDTO(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), new EnderecoDetalhamentoDTO(medico.getEndereco()));

    }
}
