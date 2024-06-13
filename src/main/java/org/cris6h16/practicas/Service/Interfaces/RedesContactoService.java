package org.cris6h16.practicas.Service.Interfaces;

import org.cris6h16.practicas.Models.ERedesContacto;
import org.cris6h16.practicas.Models.RedesContacto;

public interface RedesContactoService {
    RedesContacto buscarPorNombre(ERedesContacto nombre);
    void guardar(RedesContacto redesContacto);
}
