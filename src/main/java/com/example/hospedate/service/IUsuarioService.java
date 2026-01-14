package com.example.hospedate.service;

import com.example.hospedate.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario crear(Usuario usuario);
    List<Usuario> listar();
    Usuario buscarPorId(Long id);
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
}
