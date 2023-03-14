package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.dto.FormAtualizarEndereco;
import med.voll.api.domain.endereco.dto.FormCadastroEndereco;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable //Os atributos de endereço ficarão na tabela Médico
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public Endereco(FormCadastroEndereco formCadastroEndereco) {
        this.logradouro = formCadastroEndereco.logradouro();
        this.bairro = formCadastroEndereco.bairro();
        this.cep = formCadastroEndereco.cep();
        this.cidade = formCadastroEndereco.cidade();
        this.uf = formCadastroEndereco.uf();
        this.complemento = formCadastroEndereco.complemento();
        this.numero = formCadastroEndereco.numero();
    }

    public void atualizarInformacoes(FormAtualizarEndereco dados) {
        if(dados.logradouro() != null) this.logradouro = dados.logradouro();
        if(dados.bairro() != null) this.bairro = dados.bairro();
        if(dados.cep() != null) this.cep = dados.cep();
        if(dados.cidade() != null) this.cidade = dados.cidade();
        if(dados.uf() != null) this.uf = dados.uf();
        if(dados.complemento() != null) this.complemento = dados.complemento();
        if(dados.numero() != null) this.numero = dados.numero();
    }
}
