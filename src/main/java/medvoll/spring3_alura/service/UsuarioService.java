package medvoll.spring3_alura.service;

import medvoll.spring3_alura.domain.usuario.Usuario;
import medvoll.spring3_alura.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Usuario registrarUsuario(String login, String senha) {

        Usuario usuario = new Usuario();
        usuario.setLogin(login);
        usuario.setSenha(passwordEncoder.encode(senha));

        return repository.save(usuario);

    }
}
