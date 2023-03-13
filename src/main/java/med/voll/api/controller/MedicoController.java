package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public void cadastrar(@RequestBody @Valid FormCadastroMedico formCadastroMedico) {
        medicoRepository.save(new Medico(formCadastroMedico));
    }

    @GetMapping
    public Page<MedicoListagemDTO> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao) {
        return medicoRepository.findAllByAtivoTrue(paginacao).map(MedicoListagemDTO::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid FormAtualizarMedico formAtualizarMedico) {
        var medico = medicoRepository.getReferenceById(formAtualizarMedico.id());
        medico.atualizarInformacoes(formAtualizarMedico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();
    }
}
