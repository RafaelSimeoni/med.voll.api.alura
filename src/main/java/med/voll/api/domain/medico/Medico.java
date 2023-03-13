package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.medico.form.FormAtualizarMedico;
import med.voll.api.domain.medico.form.FormCadastroMedico;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") //gera o equals e hashcode comparando apenas o id e não todos os atributos
@Table(name = "medicos")
@Entity
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    private Boolean ativo;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded //Cria os campos de endereço na mesma tabela
    private Endereco endereco;

    public Medico(FormCadastroMedico formCadastroMedico) {
        this.ativo = true;
        this.nome = formCadastroMedico.nome();
        this.email = formCadastroMedico.email();
        this.telefone = formCadastroMedico.telefone();
        this.crm = formCadastroMedico.crm();
        this.especialidade = formCadastroMedico.especialidade();
        this.endereco = new Endereco(formCadastroMedico.endereco());
    }

    public void atualizarInformacoes(FormAtualizarMedico dados) {
        if(dados.nome() != null) this.nome = dados.nome();
        if(dados.telefone() != null) this.telefone = dados.telefone();
        if(dados.formAtualizarEndereco() != null) this.endereco.atualizarInformacoes(dados.formAtualizarEndereco());
    }

    public void excluir() {
        this.ativo = false;
    }
}
