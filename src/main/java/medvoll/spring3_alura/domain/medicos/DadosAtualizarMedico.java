package medvoll.spring3_alura.domain.medicos;

import jakarta.validation.constraints.NotNull;
import medvoll.spring3_alura.domain.endereco.DadosEndereco;

public record DadosAtualizarMedico(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {

}
