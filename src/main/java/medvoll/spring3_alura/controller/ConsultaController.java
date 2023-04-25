package medvoll.spring3_alura.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import medvoll.spring3_alura.domain.consulta.DadosAgendamentoDTO;
import medvoll.spring3_alura.domain.consulta.DadosDetalhamentoConsultaDTO;
import medvoll.spring3_alura.service.AgendaDeConsultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {


    @Autowired
    private AgendaDeConsultasService agendaDeConsultasService;

    @PostMapping
    @Transactional
    public ResponseEntity agendarConsulta(@RequestBody @Valid DadosAgendamentoDTO dadosAgendamentoDTO){

        var dto = agendaDeConsultasService.agendar(dadosAgendamentoDTO);

        return ResponseEntity.ok().body(dto);
    }


}
