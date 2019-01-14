package com.habolanos.service;

import com.habolanos.dto.ClientesDTO;

import com.habolanos.modelo.Clientes;

import java.math.*;

import java.util.*;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
public interface ClientesService {
    public List<Clientes> getClientes() throws Exception;

    /**
         * Save an new Clientes entity
         */
    public void saveClientes(Clientes entity) throws Exception;

    /**
         * Delete an existing Clientes entity
         *
         */
    public void deleteClientes(Clientes entity) throws Exception;

    /**
        * Update an existing Clientes entity
        *
        */
    public void updateClientes(Clientes entity) throws Exception;

    /**
         * Load an existing Clientes entity
         *
         */
    public Clientes getClientes(Long documento) throws Exception;

    public List<Clientes> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Clientes> findPageClientes(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberClientes() throws Exception;

    public List<ClientesDTO> getDataClientes() throws Exception;

    public void validateClientes(Clientes clientes) throws Exception;
}
