package med.voll.api.domain.consulta.service.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.dto.FormAgendamentoConsulta;
import med.voll.api.domain.consulta.repository.ConsultaRepository;
import org.springframework.stereotype.Service;


@Service
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoConsulta {
    private final ConsultaRepository repository;

    public ValidadorPacienteSemOutraConsultaNoDia(ConsultaRepository repository) {
        this.repository = repository;
    }

    public void validar(FormAgendamentoConsulta dados) {
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia =
                repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
        if(pacientePossuiOutraConsultaNoDia)
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
    }

}
