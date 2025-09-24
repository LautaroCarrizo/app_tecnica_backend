package com.app.backend.api.services;

import com.app.backend.api.dtos.user_dto.UserDetailDTO;
import com.app.backend.api.models.enums.RolUser;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * Devuelve detalle por id.
     */
    UserDetailDTO obtenerPorId(Long userId);

    /**
     * Devuelve detalle por email (útil para login/autenticación).
     */
    Optional<UserDetailDTO> obtenerPorEmail(String email);

    /**
     * Listado básico de usuarios (para administración).
     */
    List<UserDetailDTO> listarTodos();

    /**
     * Cambia nombre/email/rol (sin tocar password).
     */
    UserDetailDTO actualizarPerfil(Long userId, String nombre, String email, RolUser role);

    /**
     * Elimina un usuario (política a definir: física o lógica).
     */
    void eliminar(Long userId);

    /**
     * Cuenta usuarios por rol (ej.: ADMIN) — útil para reglas de negocio.
     */
    long contarPorRol(RolUser role);
}
