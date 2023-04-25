package medvoll.spring3_alura.service.validacoesConsultas;

import medvoll.spring3_alura.domain.consulta.ConsultaRepository;
import medvoll.spring3_alura.domain.consulta.DadosAgendamentoDTO;
import medvoll.spring3_alura.infra.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsulta implements ValidadorDeAgendamentoDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO){

        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository
                .existsByMedicoIdAndData(dadosAgendamentoDTO.idMedico(), dadosAgendamentoDTO.data());

        if(medicoPossuiOutraConsultaNoMesmoHorario){
            throw new BusinessException(("Médico já possui outra consulta agenda neste mesmo horário"));
        }
    }
}
