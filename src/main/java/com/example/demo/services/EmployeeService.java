package com.example.demo.services;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.entities.EmployeeEntities;
import com.example.demo.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    final EmployeeRepository employeeRepository;
    final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

      // will do model mapping to convert one model to another
    public EmployeeDTO getEmployeeById(long id) {
        EmployeeEntities employeeEntities = employeeRepository.getById(id);
//        return new EmployeeDTO(employeeEntities.getId(),employeeEntities.getName(),
//                employeeEntities.getDataOfJoining(),employeeEntities.isActive());

        return modelMapper.map(employeeEntities, EmployeeDTO.class);
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
       EmployeeEntities employeeEntities = modelMapper.map(employeeDTO, EmployeeEntities.class);
        return modelMapper.map(employeeRepository.save(employeeEntities), EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployee() {
        /* for loop */
        /*List<EmployeeDTO> list = new ArrayList<>();
        for (EmployeeEntities employeeEntities : employeeRepository.findAll())
        {
            EmployeeDTO map = modelMapper.map(employeeEntities, EmployeeDTO.class);
            list.add(map);
        }
        return list;*/
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeEntities -> modelMapper.map(employeeEntities, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public boolean deleteEmployeeById(int id) {
        boolean isPresent = employeeRepository.existsById((long) id);
        if (isPresent) {
            employeeRepository.deleteById((long) id);
            return true;
        }
       return false;
    }
}
