package medvoll.spring3_alura.service.validacoesConsultas;

import medvoll.spring3_alura.domain.consulta.ConsultaRepository;
import medvoll.spring3_alura.domain.consulta.DadosAgendamentoDTO;
import medvoll.spring3_alura.infra.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorDeAgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO){

        var primeiroHorario = dadosAgendamentoDTO.data().withHour(7);
        var ultimoHorario = dadosAgendamentoDTO.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository
                .existsByPacienteIdAndDataBetween(dadosAgendamentoDTO.idPaciente(), primeiroHorario, ultimoHorario);

        if(pacientePossuiOutraConsultaNoDia){
            throw new BusinessException("Pacciente já possui uma consulta agendada para este dia.");
        }


    }
}
