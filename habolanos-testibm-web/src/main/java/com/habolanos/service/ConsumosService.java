package com.habolanos.service;

import com.habolanos.dto.ConsumosDTO;

import com.habolanos.modelo.Consumos;

import java.math.*;

import java.util.*;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
public interface ConsumosService {
    public List<Consumos> getConsumos() throws Exception;

    /**
         * Save an new Consumos entity
         */
    public void saveConsumos(Consumos entity) throws Exception;

    /**
         * Delete an existing Consumos entity
         *
         */
    public void deleteConsumos(Consumos entity) throws Exception;

    /**
        * Update an existing Consumos entity
        *
        */
    public void updateConsumos(Consumos entity) throws Exception;

    /**
         * Load an existing Consumos entity
         *
         */
    public Consumos getConsumos(Long idConsumo) throws Exception;

    public List<Consumos> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Consumos> findPageConsumos(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberConsumos() throws Exception;

    public List<ConsumosDTO> getDataConsumos() throws Exception;

    public void validateConsumos(Consumos consumos) throws Exception;
}
