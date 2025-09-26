package org.zix.PeluqueriaCalderons.persistence;

import org.springframework.stereotype.Repository;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;
import org.zix.PeluqueriaCalderons.dominio.exception.FacturaNoExisteException;
import org.zix.PeluqueriaCalderons.dominio.exception.FacturaYaExisteException;
import org.zix.PeluqueriaCalderons.dominio.repository.FacturaRepository;
import org.zix.PeluqueriaCalderons.persistence.crud.FacturaCrud;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.EmpleadoEntity;
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
        return this.facturaMapper.toDto(this.facturaCrud.findAll());
    }

    @Override
    public FacturaDto buscarPorId(Long codigo) {
        FacturaEntity facturaEntity = this.facturaCrud.findById(codigo)
                .orElseThrow(() -> new FacturaNoExisteException(codigo));
        return this.facturaMapper.toDto(facturaEntity);
    }

    @Override
    public FacturaDto guardarFactura(FacturaDto facturaDto) {

        if (this.facturaCrud.findByClienteCodigoClienteAndFecha(facturaDto.cliente_id(), facturaDto.fecha()) != null) {
            throw new FacturaYaExisteException(facturaDto.codigo());
        }


        FacturaEntity factura = this.facturaMapper.toEntity(facturaDto);
        this.facturaCrud.save(factura);

        return this.facturaMapper.toDto(factura);
    }

    @Override
    public FacturaDto modificarFactura(Long codigo, ModFacturaDto modFacturaDto) {
        FacturaEntity factura = this.facturaCrud.findById(codigo)
                .orElseThrow(() -> new FacturaNoExisteException(codigo));

        factura.setFecha(modFacturaDto.fecha());
        factura.setTotal(modFacturaDto.total());

        ClienteEntity cliente = new ClienteEntity();
        cliente.setCodigoCliente(modFacturaDto.cliente_id());
        factura.setCliente(cliente);

        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setId(modFacturaDto.empleado_id());
        factura.setEmpleado(empleado);

        facturaCrud.save(factura);

        return this.facturaMapper.toDto(factura);
    }

    @Override
    public void eliminarFactura(Long codigo) {
        FacturaEntity facturaEntity = this.facturaCrud.findById(codigo)
                .orElseThrow(() -> new FacturaNoExisteException(codigo));
        this.facturaCrud.delete(facturaEntity);
    }
}
