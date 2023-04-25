package medvoll.spring3_alura.service.validacoesConsultas;

import medvoll.spring3_alura.domain.consulta.DadosAgendamentoDTO;
import medvoll.spring3_alura.infra.exception.BusinessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorDeAgendamentoDeConsultas {

    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO){

        var dataConsulta = dadosAgendamentoDTO.data();
        var agora = LocalDateTime.now();
        var diferencaMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if(diferencaMinutos < 30){
            throw new BusinessException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
