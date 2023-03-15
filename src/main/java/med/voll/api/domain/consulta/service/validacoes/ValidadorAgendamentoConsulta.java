package med.voll.api.domain.consulta.service.validacoes;

import med.voll.api.domain.consulta.dto.FormAgendamentoConsulta;

public interface ValidadorAgendamentoConsulta {
    void validar(FormAgendamentoConsulta dados);
}
