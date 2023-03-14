package med.voll.api.domain.paciente.dto;

import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.paciente.Paciente;

public record PacienteDetalhamentoDTO(Long id, String nome, String email, String cpf, String telefone, Endereco endereco) {

    public PacienteDetalhamentoDTO(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
