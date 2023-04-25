package medvoll.spring3_alura.service.validacoesConsultas;

import medvoll.spring3_alura.domain.consulta.DadosAgendamentoDTO;
import medvoll.spring3_alura.infra.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorDeAgendamentoDeConsultas {

    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO){

        var dataConsulta = dadosAgendamentoDTO.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if (domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica){
            throw new BusinessException("Consulta fora do horário de funcionamento");
        }



    }
}
