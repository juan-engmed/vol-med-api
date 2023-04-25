package medvoll.spring3_alura.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import medvoll.spring3_alura.domain.medicos.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoDTO(

        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
