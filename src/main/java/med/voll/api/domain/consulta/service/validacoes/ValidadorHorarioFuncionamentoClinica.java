package med.voll.api.domain.consulta.service.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.dto.FormAgendamentoConsulta;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsulta {

    public void validar(FormAgendamentoConsulta dados) {
        var domingo = dados.data().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dados.data().getHour() < 7;
        var depoisDoFechamentoDaClinica = dados.data().getHour() > 18;

        if(domingo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
