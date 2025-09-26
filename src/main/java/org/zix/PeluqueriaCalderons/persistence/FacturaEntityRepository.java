package org.zix.PeluqueriaCalderons.persistence;

import org.springframework.stereotype.Repository;
import org.zix.PeluqueriaCalderons.dominio.dto.FacturaDto;
import org.zix.PeluqueriaCalderons.dominio.dto.ModFacturaDto;
import org.zix.PeluqueriaCalderons.dominio.exception.FacturaNoExisteException;
import org.zix.PeluqueriaCalderons.dominio.exception.FacturaYaExisteException;
import org.zix.PeluqueriaCalderons.dominio.repository.FacturaRepository;
import org.zix.PeluqueriaCalderons.persistence.crud.CrudFacturaEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.ClienteEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.EmpleadoEntity;
import org.zix.PeluqueriaCalderons.persistence.entity.FacturaEntity;
import org.zix.PeluqueriaCalderons.web.mapper.CitaMapper;
import org.zix.PeluqueriaCalderons.web.mapper.FacturaMapper;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class FacturaEntityRepository implements FacturaRepository {

    private CrudFacturaEntity crudFactura;
    private FacturaMapper facturaMapper;

    public FacturaEntityRepository (CrudFacturaEntity crudFactura, FacturaMapper facturaMapper) {
        this.crudFactura = crudFactura;
        this.facturaMapper = facturaMapper;
    }

    @Override
    public List<FacturaDto> obtenerTodo (){
        return this.facturaMapper.toDto(this.crudFactura.findAll());
    }

    @Override
    public FacturaDto buscarFacturaPorCodigo(Long codigoFactura) {
        FacturaEntity factura = this.crudFactura.findById(codigoFactura).orElseThrow(() -> new FacturaNoExisteException(codigoFactura));
        return this.facturaMapper.toDto(factura);
    }

    @Override
    public FacturaDto guardarFactura(ModFacturaDto modFacturaDto) {
        if (this.crudFactura.findFirstByFechaHoraAndCliente_CodigoCliente(modFacturaDto.date(), modFacturaDto.clienteId()).isPresent()) {
            throw new FacturaYaExisteException(modFacturaDto.date(), modFacturaDto.clienteId());
        }

        FacturaEntity factura = new FacturaEntity();
        factura.setTotal(modFacturaDto.totalidad());
        factura.setFechaHora(modFacturaDto.date());

        ClienteEntity cliente = new ClienteEntity();
        cliente.setCodigoCliente(modFacturaDto.clienteId());
        factura.setCliente(cliente);

        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setId(modFacturaDto.empleadoId());
        factura.setEmpleado(empleado);

        this.crudFactura.save(factura);
        return this.facturaMapper.toDto(factura);

    }

    @Override
    public FacturaDto modificarFactura(Long codigoFactura, ModFacturaDto modFacturaDto) {
        FacturaEntity factura = this.crudFactura.findById(codigoFactura).orElseThrow(() -> new FacturaNoExisteException(codigoFactura));

        factura.setTotal(modFacturaDto.totalidad());
        factura.setFechaHora(modFacturaDto.date());

        ClienteEntity cliente = new ClienteEntity();
        cliente.setCodigoCliente(modFacturaDto.clienteId());
        factura.setCliente(cliente);
        EmpleadoEntity empleado = new EmpleadoEntity();
        empleado.setId(modFacturaDto.empleadoId());
        factura.setEmpleado(empleado);

        return this.facturaMapper.toDto(this.crudFactura.save(factura));
    }

    @Override
    public void eliminarFactura(Long codigoFactura) {

        FacturaEntity factura = this.crudFactura.findById(codigoFactura).orElseThrow(() -> new FacturaNoExisteException(codigoFactura));
        this.crudFactura.deleteById(codigoFactura);
    }

}
