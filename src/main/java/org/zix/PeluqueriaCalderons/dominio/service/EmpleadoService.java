package org.zix.PeluqueriaCalderons.dominio.service;

import org.zix.PeluqueriaCalderons.dominio.dto.EmpleadoDto;
import org.zix.PeluqueriaCalderons.dominio.dto.CreateEmpleadoDto;
import org.zix.PeluqueriaCalderons.dominio.exception.EmpleadoNoExistsException;
import org.zix.PeluqueriaCalderons.dominio.exception.EmpleadoYaExistsException;
import org.zix.PeluqueriaCalderons.persistence.entity.EmpleadoEntity;
import org.zix.PeluqueriaCalderons.dominio.repository.EmpleadoRepository;
import org.zix.PeluqueriaCalderons.web.mapper.EmpleadoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private EmpleadoMapper empleadoMapper;

    public List<EmpleadoDto> getAllEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmpleadoDto> getEmpleadosActivos() {
        return empleadoRepository.findByActivo(true).stream()
                .map(empleadoMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<EmpleadoDto> getEmpleadosByPuesto(String puesto) {
        return empleadoRepository.findByPuesto(puesto).stream()
                .map(empleadoMapper::toDto)
                .collect(Collectors.toList());
    }

    public EmpleadoDto getEmpleadoById(Long id) {
        Optional<EmpleadoEntity> empleadoOpt = empleadoRepository.findById(id);
        if (empleadoOpt.isEmpty()) {
            throw new EmpleadoNoExistsException("Empleado no encontrado con ID: " + id);
        }
        return empleadoMapper.toDto(empleadoOpt.get());
    }

    public EmpleadoDto getEmpleadoByEmail(String email) {
        Optional<EmpleadoEntity> empleadoOpt = empleadoRepository.findByEmail(email);
        if (empleadoOpt.isEmpty()) {
            throw new EmpleadoNoExistsException("Empleado no encontrado con email: " + email);
        }
        return empleadoMapper.toDto(empleadoOpt.get());
    }

    public EmpleadoDto createEmpleado(CreateEmpleadoDto empleadoDto) {
        // Verificar si ya existe un empleado con el mismo email
        if (empleadoRepository.existsByEmail(empleadoDto.getEmail())) {
            throw new EmpleadoYaExistsException("Ya existe un empleado con el email: " + empleadoDto.getEmail());
        }

        EmpleadoEntity empleado = empleadoMapper.fromCreateDto(empleadoDto);
        EmpleadoEntity savedEmpleado = empleadoRepository.save(empleado);
        return empleadoMapper.toDto(savedEmpleado);
    }

    public EmpleadoDto updateEmpleado(Long id, EmpleadoDto empleadoDto) {
        Optional<EmpleadoEntity> empleadoOpt = empleadoRepository.findById(id);
        if (empleadoOpt.isEmpty()) {
            throw new EmpleadoNoExistsException("Empleado no encontrado con ID: " + id);
        }

        // Verificar si el email ya existe en otro empleado
        Optional<EmpleadoEntity> empleadoConEmailOpt = empleadoRepository.findByEmail(empleadoDto.getEmail());
        if (empleadoConEmailOpt.isPresent() && !empleadoConEmailOpt.get().getId().equals(id)) {
            throw new EmpleadoYaExistsException("Ya existe otro empleado con el email: " + empleadoDto.getEmail());
        }

        EmpleadoEntity empleado = empleadoOpt.get();
        empleado.setNombre(empleadoDto.getNombre());
        empleado.setEmail(empleadoDto.getEmail());
        empleado.setTelefono(empleadoDto.getTelefono());
        empleado.setPuesto(empleadoDto.getPuesto());
        empleado.setFechaContratacion(empleadoDto.getFechaContratacion());
        empleado.setActivo(empleadoDto.getActivo());

        EmpleadoEntity updatedEmpleado = empleadoRepository.save(empleado);
        return empleadoMapper.toDto(updatedEmpleado);
    }

    public void deleteEmpleado(Long id) {
        if (!empleadoRepository.existsById(id)) {
            throw new EmpleadoNoExistsException("Empleado no encontrado con ID: " + id);
        }
        empleadoRepository.deleteById(id);
    }

    public EmpleadoDto activateEmpleado(Long id) {
        return changeEmpleadoStatus(id, true);
    }

    public EmpleadoDto deactivateEmpleado(Long id) {
        return changeEmpleadoStatus(id, false);
    }

    private EmpleadoDto changeEmpleadoStatus(Long id, boolean activo) {
        Optional<EmpleadoEntity> empleadoOpt = empleadoRepository.findById(id);
        if (empleadoOpt.isEmpty()) {
            throw new EmpleadoNoExistsException("Empleado no encontrado con ID: " + id);
        }

        EmpleadoEntity empleado = empleadoOpt.get();
        empleado.setActivo(activo);

        EmpleadoEntity updatedEmpleado = empleadoRepository.save(empleado);
        return empleadoMapper.toDto(updatedEmpleado);
    }
}