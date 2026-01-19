package com.example.hospedate.service;

import com.example.hospedate.model.Usuario;
import com.example.hospedate.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //    public User registerUser(User user) {
//        User userLogin = new User();
//        userLogin.setUsername(user.getUsername());
//        userLogin.setPassword(passwordEncoder.encode(user.getPassword()));
//        // Encriptar la contraseña
//        return userRepository.save(userLogin);
//    }
    // Validar campos obligatorios
/*    public Usuario registerUser(Usuario user) {
        // Validar campos obligatorios
        if (user.getCorreo() == null || user.getContrasena() == null ||
                user.getNombre() == null || user.getApellido() == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setNombre(user.getNombre());
        newUser.setApellido(user.getApellido());

        return userRepository.save(newUser);
    }*/

    // Métdo de carga de usuario implementado desde UserDetailsService
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return new org.springframework.security.core.userdetails.User(user.getCorreo(), user.getContrasena(), new ArrayList<>());
    }
}
