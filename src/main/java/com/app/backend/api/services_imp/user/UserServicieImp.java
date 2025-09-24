package com.app.backend.api.services_imp.user;

import com.app.backend.api.dtos.user_dto.UserDetailDTO;
import com.app.backend.api.models.enums.RolUser;
import com.app.backend.api.services.UserService;

import java.util.List;
import java.util.Optional;

public class UserServicieImp implements UserService {
    @Override
    public UserDetailDTO obtenerPorId(Long userId) {
        return null;
    }
    @Override
    public Optional<UserDetailDTO> obtenerPorEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<UserDetailDTO> listarTodos() {
        return List.of();
    }

    @Override
    public UserDetailDTO actualizarPerfil(Long userId, String nombre, String email, RolUser role) {
        return null;
    }
    
    @Override
    public void eliminar(Long userId) {
    }
    @Override
    public long contarPorRol(RolUser role) {
        return 0;
    }
}
