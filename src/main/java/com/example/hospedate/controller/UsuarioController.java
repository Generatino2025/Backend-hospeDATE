package com.example.hospedate.controller;

import com.example.hospedate.JwtUtil;
import com.example.hospedate.dto.UserDTO;
import com.example.hospedate.model.Usuario;
import com.example.hospedate.service.IUsuarioService;
import com.example.hospedate.service.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospedate/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServiceImpl userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final IUsuarioService usuarioService;


    public UsuarioController(IUsuarioService usuarioService, UsuarioServiceImpl userService) {
        this.usuarioService = usuarioService;

    }

    @PostMapping
     public Usuario crear(@Valid @RequestBody Usuario usuario) {
        return usuarioService.crear(usuario);
        /*return ResponseEntity.ok("Usuario registrado con éxito");*/
    }

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listar();
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }

    //----Autenticación
    @PostMapping("/loginConDTO")
    public ResponseEntity<String> loginConDTO(@RequestBody UserDTO userDto) {
        UserDetails userDetails = userService.loadUserByUsername(userDto.getCorreo());
        if (userDetails != null && passwordEncoder.matches(userDto.getContrasena(), userDetails.getPassword())) {
            String token = jwtUtil.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Credenciales inválidas");
    }

    @GetMapping("/resource")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getProtectedResource() {
        return ResponseEntity.ok("Este es un recurso protegido!");
    }
}

