package com.api.pro.services;

import java.io.InputStream;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.pro.entity.Proyecto;
import com.api.pro.entity.Tarea;
import com.api.pro.entity.Usuario;
import com.api.pro.repository.ProyectoRepository;
import com.api.pro.repository.TareaRepository;
import com.api.pro.repository.UsuarioRepository;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReporteService {

    @Autowired
    private ProyectoRepository proyectoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TareaRepository tareaRepository;

    public byte[] generarReporteProyectos() throws JRException {
        List<Proyecto> proyectos = proyectoRepository.findAll();
        InputStream stream = getClass().getResourceAsStream("/reportes/reporteProyectos.jrxml");
        JasperReport reporte = JasperCompileManager.compileReport(stream);
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(proyectos);
        Map<String, Object> params = new HashMap<>();
        params.put("titulo", "Reporte de Proyectos");
        JasperPrint print = JasperFillManager.fillReport(reporte, params, ds);
        return JasperExportManager.exportReportToPdf(print);
    }
    
    public byte[] generarReporteUsuarios() throws JRException {
        List<Usuario> usuarios = usuarioRepository.findAll();
        InputStream stream = getClass().getResourceAsStream("/reportes/reporteUsuarios.jrxml");
        JasperReport reporte = JasperCompileManager.compileReport(stream);
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(usuarios);
        JasperPrint print = JasperFillManager.fillReport(reporte, new HashMap<>(), ds);
        return JasperExportManager.exportReportToPdf(print);
    }

    public byte[] generarReporteTareas() throws JRException {
        List<Tarea> tareas = tareaRepository.findAll();
        InputStream stream = getClass().getResourceAsStream("/reportes/reporteTareas.jrxml");
        JasperReport reporte = JasperCompileManager.compileReport(stream);
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(tareas);
        JasperPrint print = JasperFillManager.fillReport(reporte, new HashMap<>(), ds);
        return JasperExportManager.exportReportToPdf(print);
    }
}