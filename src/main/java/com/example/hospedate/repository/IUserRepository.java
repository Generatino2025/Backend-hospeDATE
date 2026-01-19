package com.example.hospedate.repository;

import com.example.hospedate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<Usuario, Long> {
    // Usuario findByUsername(String nombre);
}
