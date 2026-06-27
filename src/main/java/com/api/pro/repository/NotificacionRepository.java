package com.api.pro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.pro.entity.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByUsuarioIdUsuarioOrderByFechaDesc(Integer idUsuario);
}