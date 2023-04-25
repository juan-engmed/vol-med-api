package medvoll.spring3_alura.service.validacoesConsultas;

import medvoll.spring3_alura.domain.consulta.DadosAgendamentoDTO;
import medvoll.spring3_alura.domain.medicos.Medico;
import medvoll.spring3_alura.domain.medicos.MedicoRepository;
import medvoll.spring3_alura.infra.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo  implements ValidadorDeAgendamentoDeConsultas{

    @Autowired
    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO){

        if(dadosAgendamentoDTO.idMedico() == null){

            return;
        }

        Medico medicoEstaAtivo = medicoRepository.findAtivoById(dadosAgendamentoDTO.idMedico())
                .orElseThrow(() -> new BusinessException("Consulta não pode ser agendada, pois Médico não está ativo"));

    }
}
