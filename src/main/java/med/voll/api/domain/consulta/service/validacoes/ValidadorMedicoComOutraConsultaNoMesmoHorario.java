package med.voll.api.domain.consulta.service.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.dto.FormAgendamentoConsulta;
import med.voll.api.domain.consulta.repository.ConsultaRepository;
import org.springframework.stereotype.Service;


@Service
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoConsulta {
    private final ConsultaRepository repository;

    public ValidadorMedicoComOutraConsultaNoMesmoHorario(ConsultaRepository repository) {
        this.repository = repository;
    }

    public void validar(FormAgendamentoConsulta dados) {
        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

        if(medicoPossuiOutraConsultaNoMesmoHorario)
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse horário");

    }
}
