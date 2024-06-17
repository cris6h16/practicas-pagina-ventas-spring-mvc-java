package org.cris6h16.practicas.Service.Interfaces;

import org.cris6h16.practicas.Models.ERedesContacto;
import org.cris6h16.practicas.Models.RedesContacto;

import java.util.List;

public interface RedesContactoService {
    RedesContacto buscarPorNombre(ERedesContacto nombre);
    void guardar(RedesContacto redesContacto);

    List<RedesContacto> obtenerTodo();
}
