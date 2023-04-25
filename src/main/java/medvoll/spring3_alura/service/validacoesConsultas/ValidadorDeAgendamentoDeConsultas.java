package medvoll.spring3_alura.service.validacoesConsultas;

import medvoll.spring3_alura.domain.consulta.DadosAgendamentoDTO;
import org.springframework.stereotype.Component;


public interface ValidadorDeAgendamentoDeConsultas {

    void validar(DadosAgendamentoDTO dadosAgendamentoDTO);
}
