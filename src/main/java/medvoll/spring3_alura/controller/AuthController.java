package medvoll.spring3_alura.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medvoll.spring3_alura.domain.usuario.DadosAuth;
import medvoll.spring3_alura.domain.usuario.Usuario;
import medvoll.spring3_alura.infra.security.DadosTokenJWTDTO;
import medvoll.spring3_alura.infra.security.TokenService;
import medvoll.spring3_alura.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    //Essa classe dispara o processo de Autenticação
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAuth dadosAuth){

        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosAuth.login(), dadosAuth.senha());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generateJWT((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWTDTO(tokenJWT));
    }

    @PostMapping("/new-user")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ResponseEntity createLogin(@RequestBody @Valid DadosAuth dadosAuth){

        Usuario usuario = usuarioService.registrarUsuario(dadosAuth.login(), dadosAuth.senha());

        return ResponseEntity.ok(new UsuarioDTO(usuario.getLogin(), "Usuário criado com sucesso"));
    }


}
