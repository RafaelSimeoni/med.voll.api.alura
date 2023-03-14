package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.dto.MedicoDetalhamentoDTO;
import med.voll.api.domain.medico.dto.MedicoListagemDTO;
import med.voll.api.domain.medico.dto.FormAtualizarMedico;
import med.voll.api.domain.medico.dto.FormCadastroMedico;
import med.voll.api.domain.medico.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    @Autowired
    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDetalhamentoDTO> cadastrar(@RequestBody @Valid FormCadastroMedico formCadastroMedico, UriComponentsBuilder uriComponentsBuilder) {
        var medico = new Medico(formCadastroMedico);
        medicoRepository.save(medico);

        var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoDetalhamentoDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoListagemDTO>> listar(@PageableDefault(size=12, sort={"nome"}) Pageable paginacao) {
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(MedicoListagemDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDetalhamentoDTO> atualizar(@RequestBody @Valid FormAtualizarMedico formAtualizarMedico) {
        var medico = medicoRepository.getReferenceById(formAtualizarMedico.id());
        medico.atualizarInformacoes(formAtualizarMedico);
        return ResponseEntity.ok(new MedicoDetalhamentoDTO(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDetalhamentoDTO> detalhar(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDetalhamentoDTO(medico));
    }
}
