package med.voll.api.domain.consulta.service.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.dto.FormAgendamentoConsulta;
import med.voll.api.domain.paciente.repository.PacienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidadorPacienteAtivo implements ValidadorAgendamentoConsulta {

    private final PacienteRepository repository;

    public ValidadorPacienteAtivo(PacienteRepository repository) {
        this.repository = repository;
    }

    public void validar(FormAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if(!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo");
        }
    }
}
