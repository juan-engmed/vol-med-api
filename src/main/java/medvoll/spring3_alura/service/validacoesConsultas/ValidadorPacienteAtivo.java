package medvoll.spring3_alura.service.validacoesConsultas;

import medvoll.spring3_alura.domain.consulta.DadosAgendamentoDTO;
import medvoll.spring3_alura.domain.paciente.Paciente;
import medvoll.spring3_alura.domain.paciente.PacienteRepository;
import medvoll.spring3_alura.infra.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidadorPacienteAtivo implements ValidadorDeAgendamentoDeConsultas {

    @Autowired
    private PacienteRepository pacienteRepository;

    public void validar(DadosAgendamentoDTO dadosAgendamentoDTO){

        Paciente pacienteEstaAtivo = pacienteRepository.findAtivoById(dadosAgendamentoDTO.idPaciente())
                .orElseThrow(() -> new BusinessException("Consulta não pode ser agendada, pois Paciente não está ativo"));

    }
}
