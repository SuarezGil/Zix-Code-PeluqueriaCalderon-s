package org.zix.PeluqueriaCalderons.persistence;

import org.zix.PeluqueriaCalderons.dominio.repository.ClienteRepository;
import org.zix.PeluqueriaCalderons.persistence.crud.CrudClienteEntity;

import java.util.List;

public class ClienteEntityRepository implements ClienteRepository {
        private final CrudClienteEntity crudCliente;
    private final ClienteMapper clienteMapper;

    public PeliculaEntityRepository(CrudPeliculaEntity crudPelicula, PeliculaMapper peliculaMapper) {
        this.crudPelicula = crudPelicula;
        this.peliculaMapper = peliculaMapper;
    }


    @Override
    public List<PeliculaDto> obtenerTodo() {
        return this.peliculaMapper.toDto(this.crudPelicula.findAll());
    }

    @Override
    public PeliculaDto obtenerPeliculaPorCodigo(Long codigo) {
        return this.peliculaMapper.toDto(this.crudPelicula.findById(codigo).orElse(null));

    }

    @Override
    public PeliculaDto guardarPelicula(PeliculaDto peliculaDto) {
        //buscarportitulo
        //instanciar clase de entidad
        //convertir dto a entidad
        PeliculaEntity pelicula = this.peliculaMapper.toEntity(peliculaDto);
        pelicula.setEstado("D");
        if (this.crudPelicula.findFirstByNombre(peliculaDto.name())!=null){
            throw new PeliculaYaExisteException(peliculaDto.name());
        }
        //guardar en la DB con JPA
        this.crudPelicula.save(pelicula);
        //Retornar el valor guardado como DTO
        return this.peliculaMapper.toDto(pelicula);
    }

    @Override
    public PeliculaDto modificarPelicula(Long codigo, ModPeliculaDto modPeliculaDto) {
        PeliculaEntity pelicula = this.crudPelicula.findById(codigo).orElse(null);
//        pelicula.setNombre(modPeliculaDto.name());
//        pelicula.setFechaEstreno(modPeliculaDto.releaseDate());
//        pelicula.setCalificacion(BigDecimal.valueOf(modPeliculaDto.rating()));
//        this.crudPelicula.save(pelicula);
//        return this.peliculaMapper.toDto(pelicula);
        if (pelicula == null) {throw new PeliculaNoExisteException(codigo);
        }
        else{
            this.peliculaMapper.modificarEntityFromDto(modPeliculaDto, pelicula);
            return this.peliculaMapper.toDto(this.crudPelicula.save(pelicula));
        }
    }

    @Override
    public void  eliminarPelicula(Long codigo) {
        PeliculaEntity peliculaEntity= this.crudPelicula.findById(codigo).orElse(null);
        if (peliculaEntity == null){
            throw new PeliculaNoExisteException(codigo);
        }else{
            this.crudPelicula.deleteById(codigo);
        }
    }
}
}
