package med.voll.api.domain.consulta.service.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.dto.FormAgendamentoConsulta;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoConsulta {
    public void validar(FormAgendamentoConsulta dados) {
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dados.data()).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
