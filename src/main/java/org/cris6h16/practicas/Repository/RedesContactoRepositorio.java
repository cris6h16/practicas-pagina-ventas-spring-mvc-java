package org.cris6h16.practicas.Repository;

import org.cris6h16.practicas.Models.ERedesContacto;
import org.cris6h16.practicas.Models.RedesContacto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedesContactoRepositorio extends JpaRepository<RedesContacto, Long>{
    RedesContacto findByNombre(ERedesContacto nombre);
}
