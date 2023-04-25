package medvoll.spring3_alura.service;

import jakarta.persistence.EntityNotFoundException;
import medvoll.spring3_alura.domain.consulta.Consulta;
import medvoll.spring3_alura.domain.consulta.ConsultaRepository;
import medvoll.spring3_alura.domain.consulta.DadosAgendamentoDTO;
import medvoll.spring3_alura.domain.consulta.DadosDetalhamentoConsultaDTO;
import medvoll.spring3_alura.domain.medicos.Medico;
import medvoll.spring3_alura.domain.medicos.MedicoRepository;
import medvoll.spring3_alura.domain.paciente.Paciente;
import medvoll.spring3_alura.domain.paciente.PacienteRepository;
import medvoll.spring3_alura.infra.exception.BusinessException;
import medvoll.spring3_alura.service.validacoesConsultas.ValidadorDeAgendamentoDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendaDeConsultasService {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorDeAgendamentoDeConsultas> validadores;


    public DadosDetalhamentoConsultaDTO agendar(DadosAgendamentoDTO dadosAgendamentoDTO){
        Paciente paciente = pacienteRepository.findById(dadosAgendamentoDTO.idPaciente())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não existe"));
        Medico  medico = escolherMedico(dadosAgendamentoDTO);

        //Percorremos todos os validaroes e suas implementações de validações
        validadores.forEach(v -> v.validar(dadosAgendamentoDTO));

        Consulta consulta = new Consulta(null, medico, paciente, dadosAgendamentoDTO.data());
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsultaDTO(consulta);

    }

    private Medico escolherMedico(DadosAgendamentoDTO dadosAgendamentoDTO) {
        if (dadosAgendamentoDTO.idMedico() != null) {
            return medicoRepository.findById(dadosAgendamentoDTO.idMedico()).orElseThrow(() -> new EntityNotFoundException("Médico não existe"));
        } else {
            if (dadosAgendamentoDTO.especialidade() == null) {
                throw new EntityNotFoundException("Especialidade é obrigatória quando médico não for escolhido");
            }
            return  medicoRepository
                    .escolherMedicoAleatorioLivreNaData(dadosAgendamentoDTO.especialidade(), dadosAgendamentoDTO.data())
                    .stream()
                    .limit(1)
                    .findFirst()
                    .orElseThrow(() -> new BusinessException("Nenhum Médico disponível para consulta"));
        }
    }
}
