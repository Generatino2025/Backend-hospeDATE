package com.example.hospedate.repository;

import com.example.hospedate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 Usuario existsByCorreo(String correo);

  //  boolean existsByCorreoAndIdUsuarioNot(String correo, Long idUsuario);

  ///  Usuario findByUsername(String correo);

}

