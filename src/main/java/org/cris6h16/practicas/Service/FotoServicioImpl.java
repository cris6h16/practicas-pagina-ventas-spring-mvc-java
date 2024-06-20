package org.cris6h16.practicas.Service;

import org.cris6h16.practicas.Models.Foto;
import org.cris6h16.practicas.Models.Usuario;
import org.cris6h16.practicas.Repository.FotoRepositorio;
import org.cris6h16.practicas.Repository.UsuarioRepositorio;
import org.cris6h16.practicas.Service.Interfaces.FotoService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FotoServicioImpl implements FotoService {
    private final FotoRepositorio fotoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    public FotoServicioImpl(FotoRepositorio fotoRepositorio, UsuarioRepositorio usuarioRepositorio) {
        this.fotoRepositorio = fotoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @Override
    public Optional<Foto> findByUsuarioId(Long usuarioID) {
        return fotoRepositorio.findByUsuarioId(usuarioID);
    }


    @Override
    public void save(Foto foto, Long usuarioID) {
        Usuario usuario = usuarioRepositorio.findById(usuarioID).get();
        foto.setUsuario(usuario);
        usuario.setFoto(foto);
        fotoRepositorio.save(foto);
    }
}
