package org.zix.PeluqueriaCalderons.persistence;

import org.springframework.stereotype.Repository;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;
import org.zix.PeluqueriaCalderons.dominio.exception.FacturaNoExisteException;
import org.zix.PeluqueriaCalderons.dominio.exception.FacturaYaExisteException;
import org.zix.PeluqueriaCalderons.dominio.repository.FacturaRepository;
import org.zix.PeluqueriaCalderons.persistence.crud.FacturaCrud;
import org.zix.PeluqueriaCalderons.persistence.entity.FacturaEntity;
import org.zix.PeluqueriaCalderons.web.mapper.FacturaMapper;

import java.util.List;

@Repository
public class FacturaEntityRepository implements FacturaRepository {
    private final FacturaCrud facturaCrud;
    private final FacturaMapper facturaMapper;

    public FacturaEntityRepository(FacturaCrud facturaCrud, FacturaMapper facturaMapper) {
        this.facturaCrud = facturaCrud;
        this.facturaMapper = facturaMapper;
    }

    @Override
    public List<FacturaDto> obtenerTodo() {
        return this.facturaMapper.toDtos(this.facturaCrud.findAll());
    }

    @Override
    public FacturaDto buscarPorId(Long codigo) {
        FacturaEntity facturaEntity = this.facturaCrud.findById(codigo).orElseThrow(() -> new FacturaNoExisteException(codigo));
        return this.facturaMapper.toDto(facturaEntity);
    }

    @Override
    public FacturaDto guardarFactura(FacturaDto facturaDto) {
        if (this.facturaCrud.findByClienteIdAndFechaHora(facturaDto.cliente_id(), facturaDto.fecha()) != null) {
            throw new FacturaYaExisteException(facturaDto.cliente_id());
        }
        FacturaEntity factura = this.facturaMapper.toEntity(facturaDto);

        this.facturaCrud.save(factura);

        return this.facturaMapper.toDto(factura);
    }

    @Override
    public FacturaDto modificarFactura(Long codigo, ModFacturaDto modFacturaDto) {

        FacturaEntity factura = this.facturaCrud.findById(codigo).orElse(null);

        if (factura == null) throw new FacturaNoExisteException(codigo);
        else { return this.facturaMapper.toDto(factura);
        }

    }

    @Override
    public void eliminarFactura(Long codigo) {
        FacturaEntity facturaEntity = this.facturaCrud.findById(codigo).orElseThrow(() -> new FacturaNoExisteException(codigo));
        this.facturaCrud.delete(facturaEntity);
    }
}
