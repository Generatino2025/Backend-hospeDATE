package com.example.hospedate.service;

import com.example.hospedate.exception.ResourceNotFoundException;
import com.example.hospedate.model.Usuario;
import com.example.hospedate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

   // private final UsuarioRepository usuarioRepository;


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario crear(Usuario usuario) {
    /*    if (usuarioRepository.findByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya existe");
        }*/
        Usuario user = usuarioRepository.findByCorreo(usuario.getCorreo())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya existe");
        }

        Usuario newUser = new Usuario();
        newUser.setCorreo(usuario.getCorreo());
        newUser.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        newUser.setNombre(usuario.getNombre());
        newUser.setApellido(usuario.getApellido());
        newUser.setTipoDoc(usuario.getTipoDoc());
        newUser.setNumeroDoc(usuario.getNumeroDoc());
        newUser.setTelefono(usuario.getTelefono());
        newUser.setRol(usuario.getRol());
        System.out.println(newUser);
        return usuarioRepository.save(newUser);
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        Usuario existente = buscarPorId(id);
        existente.setNombre(usuario.getNombre());
        existente.setApellido(usuario.getApellido());
        existente.setTelefono(usuario.getTelefono());
        return usuarioRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.delete(buscarPorId(id));
    }

    // Método de carga de usuario implementado desde UserDetailsService
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        return new org.springframework.security.core.userdetails.User(
                user.getCorreo(),
                user.getContrasena(),
                new ArrayList<>()
        );
    }
}

