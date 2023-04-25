package medvoll.spring3_alura.domain.paciente;

import jakarta.validation.constraints.NotNull;
import medvoll.spring3_alura.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}

