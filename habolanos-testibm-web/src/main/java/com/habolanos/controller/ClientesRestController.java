package com.habolanos.controller;

import com.habolanos.dto.ClientesDTO;

import com.habolanos.mapper.ClientesMapper;

import com.habolanos.modelo.*;

import com.habolanos.service.ClientesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClientesRestController {
    private static final Logger log = LoggerFactory.getLogger(ClientesRestController.class);
    @Autowired
    private ClientesService clientesService;
    @Autowired
    private ClientesMapper clientesMapper;

    @PostMapping(value = "/saveClientes")
    public void saveClientes(@RequestBody
    ClientesDTO clientesDTO) throws Exception {
        try {
            Clientes clientes = clientesMapper.clientesDTOToClientes(clientesDTO);

            clientesService.saveClientes(clientes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteClientes/{documento}")
    public void deleteClientes(@PathVariable("documento")
    Long documento) throws Exception {
        try {
            Clientes clientes = clientesService.getClientes(documento);

            clientesService.deleteClientes(clientes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateClientes/")
    public void updateClientes(@RequestBody
    ClientesDTO clientesDTO) throws Exception {
        try {
            Clientes clientes = clientesMapper.clientesDTOToClientes(clientesDTO);

            clientesService.updateClientes(clientes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataClientes")
    public List<ClientesDTO> getDataClientes() throws Exception {
        try {
            return clientesService.getDataClientes();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getClientes/{documento}")
    public ClientesDTO getClientes(@PathVariable("documento")
    Long documento) throws Exception {
        try {
            Clientes clientes = clientesService.getClientes(documento);

            return clientesMapper.clientesToClientesDTO(clientes);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
