package med.voll.api.domain.endereco.dto;

import med.voll.api.domain.endereco.Endereco;

public record EnderecoDetalhamentoDTO(
        String logradouro,
        String bairro,

        String cep,

        String cidade,

        String uf,
        String complemento,
        String numero)
{
    public EnderecoDetalhamentoDTO(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf(),
                endereco.getComplemento(), endereco.getNumero());
    }
}
