package com.app.backend.api.servicies;

import com.app.backend.api.dtos.equipos_dto.EquipoRefDTO;
import com.app.backend.api.dtos.equipos_dto.celus.CeluDTO;
import com.app.backend.api.dtos.equipos_dto.celus.CeluListDTO;
import com.app.backend.api.dtos.equipos_dto.complementarios.ComplementoDTO;
import com.app.backend.api.dtos.equipos_dto.complementarios.ComplementoListDTO;
import com.app.backend.api.dtos.equipos_dto.handies.HandieDTO;
import com.app.backend.api.dtos.equipos_dto.handies.HandieListDTO;
import com.app.backend.api.dtos.equipos_dto.hardticket.HardTicketDTO;
import com.app.backend.api.dtos.equipos_dto.hardticket.HardTicketListDTO;
import com.app.backend.api.dtos.equipos_dto.pcs.PcDTO;
import com.app.backend.api.dtos.equipos_dto.pcs.PcListDTO;
import com.app.backend.api.dtos.equipos_dto.pdas.PdaDTO;
import com.app.backend.api.dtos.equipos_dto.pdas.PdaListDTO;
import com.app.backend.api.dtos.equipos_dto.pocket.PocketDTO;
import com.app.backend.api.dtos.equipos_dto.pocket.PocketListDTO;
import com.app.backend.api.models.enums.EquipoEstado;

import java.util.List;

public interface EquipoServicie {
    // ---------- Resumen mezclado (todos los tipos) por usuario ----------

    /**
     * Devuelve un resumen de TODOS los equipos que hoy tiene el usuario (cualquier tipo).
     * Útil para “qué tiene Pepito ahora”. Devuelve DTO genérico: id, type, estado, user, label.
     */
    List<EquipoRefDTO> listarResumenPorUsuario(Long userId);

    // ---------- Listados por TIPO y usuario (para filtros en front) ----------
    CeluListDTO listarCelusPorUsuario(Long userId);

    PdaListDTO listarPdasPorUsuario(Long userId);

    PcListDTO listarPcsPorUsuario(Long userId);

    HandieListDTO listarHandiesPorUsuario(Long userId);

    PocketListDTO listarPocketsPorUsuario(Long userId);

    HardTicketListDTO listarHardTicketsPorUsuario(Long userId);

    ComplementoListDTO listarComplementariosPorUsuario(Long userId);

    // ---------- Conteos (stock/custodia) ----------

    /**
     * Total de equipos (todos los tipos) en custodia de userId.
     */
    long contarPorUsuario(Long userId);

    /**
     * Total de equipos por usuario y estado técnico (FUNCIONAL/NO_FUNCIONAL).
     */
    long contarPorUsuarioYEstado(Long userId, EquipoEstado estado);

    // ---------- CRUD ADMIN por tipo ----------
    // CELU
    CeluDTO crearCelu(CeluDTO dto, Long ownerUserId);

    CeluDTO actualizarCelu(Long id, CeluDTO dto);

    void eliminarCelu(Long id);

    // PDA
    PdaDTO crearPda(PdaDTO dto, Long ownerUserId);

    PdaDTO actualizarPda(Long id, PdaDTO dto);

    void eliminarPda(Long id);

    // PC
    PcDTO crearPc(PcDTO dto, Long ownerUserId);

    PcDTO actualizarPc(Long id, PcDTO dto);

    void eliminarPc(Long id);

    // HANDY
    HandieDTO crearHandy(HandieDTO dto, Long ownerUserId);

    HandieDTO actualizarHandy(Long id, HandieDTO dto);

    void eliminarHandy(Long id);

    // POCKET
    PocketDTO crearPocket(PocketDTO dto, Long ownerUserId);

    PocketDTO actualizarPocket(Long id, PocketDTO dto);

    void eliminarPocket(Long id);

    // HARDTICKET
    HardTicketDTO crearHardTicket(HardTicketDTO dto, Long ownerUserId);

    HardTicketDTO actualizarHardTicket(Long id, HardTicketDTO dto);

    void eliminarHardTicket(Long id);

    // COMPLEMENTARIO
    ComplementoDTO crearComplementario(ComplementoDTO dto, Long ownerUserId);

    ComplementoDTO actualizarComplementario(Long id, ComplementoDTO dto);

    void eliminarComplementario(Long id);
}
