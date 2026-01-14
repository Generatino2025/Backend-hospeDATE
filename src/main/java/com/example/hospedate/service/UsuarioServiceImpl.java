package com.example.hospedate.service;

import com.example.hospedate.exception.ResourceNotFoundException;
import com.example.hospedate.model.Usuario;
import com.example.hospedate.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario crear(Usuario usuario) {
        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya existe");
        }
        return usuarioRepository.save(usuario);
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
}

