package com.habolanos.mapper;

import com.habolanos.dto.ClientesDTO;

import com.habolanos.modelo.Clientes;

import java.util.List;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
public interface ClientesMapper {
    public ClientesDTO clientesToClientesDTO(Clientes clientes)
        throws Exception;

    public Clientes clientesDTOToClientes(ClientesDTO clientesDTO)
        throws Exception;

    public List<ClientesDTO> listClientesToListClientesDTO(
        List<Clientes> clientess) throws Exception;

    public List<Clientes> listClientesDTOToListClientes(
        List<ClientesDTO> clientesDTOs) throws Exception;
}
