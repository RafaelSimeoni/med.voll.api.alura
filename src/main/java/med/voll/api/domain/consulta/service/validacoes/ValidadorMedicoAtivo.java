package med.voll.api.domain.consulta.service.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.dto.FormAgendamentoConsulta;
import med.voll.api.domain.medico.repository.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class ValidadorMedicoAtivo implements ValidadorAgendamentoConsulta {

    private final MedicoRepository repository;

    public ValidadorMedicoAtivo(MedicoRepository repository) {
        this.repository = repository;
    }

    public void validar(FormAgendamentoConsulta dados) {
        if(dados.idMedico() == null) return;

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if(!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com médico inativo");
        }
    }
}
