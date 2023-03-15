package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.dto.DetalhamentoConsultaDTO;
import med.voll.api.domain.consulta.dto.FormAgendamentoConsulta;
import med.voll.api.domain.consulta.service.ConsultaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhamentoConsultaDTO> agendar(@RequestBody @Valid FormAgendamentoConsulta form) {
        return ResponseEntity.ok(consultaService.agendar(form));
    }

}
