package com.habolanos.mapper;

import com.habolanos.dto.ClientesDTO;

import com.habolanos.modelo.*;
import com.habolanos.modelo.Clientes;

import com.habolanos.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
@Component
@Scope("singleton")
public class ClientesMapperImpl implements ClientesMapper {
    private static final Logger log = LoggerFactory.getLogger(ClientesMapperImpl.class);

    @Transactional(readOnly = true)
    public ClientesDTO clientesToClientesDTO(Clientes clientes)
        throws Exception {
        try {
            ClientesDTO clientesDTO = new ClientesDTO();

            clientesDTO.setDocumento(clientes.getDocumento());
            clientesDTO.setApellidos((clientes.getApellidos() != null)
                ? clientes.getApellidos() : null);
            clientesDTO.setCiudad((clientes.getCiudad() != null)
                ? clientes.getCiudad() : null);
            clientesDTO.setDireccion((clientes.getDireccion() != null)
                ? clientes.getDireccion() : null);
            clientesDTO.setNombres((clientes.getNombres() != null)
                ? clientes.getNombres() : null);
            clientesDTO.setNumeroTarjetasAsociadas((clientes.getNumeroTarjetasAsociadas() != null)
                ? clientes.getNumeroTarjetasAsociadas() : null);
            clientesDTO.setTelefono((clientes.getTelefono() != null)
                ? clientes.getTelefono() : null);
            clientesDTO.setTipoDocumento((clientes.getTipoDocumento() != null)
                ? clientes.getTipoDocumento() : null);

            return clientesDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Clientes clientesDTOToClientes(ClientesDTO clientesDTO)
        throws Exception {
        try {
            Clientes clientes = new Clientes();

            clientes.setDocumento(clientesDTO.getDocumento());
            clientes.setApellidos((clientesDTO.getApellidos() != null)
                ? clientesDTO.getApellidos() : null);
            clientes.setCiudad((clientesDTO.getCiudad() != null)
                ? clientesDTO.getCiudad() : null);
            clientes.setDireccion((clientesDTO.getDireccion() != null)
                ? clientesDTO.getDireccion() : null);
            clientes.setNombres((clientesDTO.getNombres() != null)
                ? clientesDTO.getNombres() : null);
            clientes.setNumeroTarjetasAsociadas((clientesDTO.getNumeroTarjetasAsociadas() != null)
                ? clientesDTO.getNumeroTarjetasAsociadas() : null);
            clientes.setTelefono((clientesDTO.getTelefono() != null)
                ? clientesDTO.getTelefono() : null);
            clientes.setTipoDocumento((clientesDTO.getTipoDocumento() != null)
                ? clientesDTO.getTipoDocumento() : null);

            return clientes;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ClientesDTO> listClientesToListClientesDTO(
        List<Clientes> listClientes) throws Exception {
        try {
            List<ClientesDTO> clientesDTOs = new ArrayList<ClientesDTO>();

            for (Clientes clientes : listClientes) {
                ClientesDTO clientesDTO = clientesToClientesDTO(clientes);

                clientesDTOs.add(clientesDTO);
            }

            return clientesDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Clientes> listClientesDTOToListClientes(
        List<ClientesDTO> listClientesDTO) throws Exception {
        try {
            List<Clientes> listClientes = new ArrayList<Clientes>();

            for (ClientesDTO clientesDTO : listClientesDTO) {
                Clientes clientes = clientesDTOToClientes(clientesDTO);

                listClientes.add(clientes);
            }

            return listClientes;
        } catch (Exception e) {
            throw e;
        }
    }
}
