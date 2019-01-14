package com.habolanos.service;

import com.habolanos.dto.TarjetasCreditoDTO;

import com.habolanos.modelo.TarjetasCredito;

import java.math.*;

import java.util.*;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
public interface TarjetasCreditoService {
    public List<TarjetasCredito> getTarjetasCredito() throws Exception;

    /**
         * Save an new TarjetasCredito entity
         */
    public void saveTarjetasCredito(TarjetasCredito entity)
        throws Exception;

    /**
         * Delete an existing TarjetasCredito entity
         *
         */
    public void deleteTarjetasCredito(TarjetasCredito entity)
        throws Exception;

    /**
        * Update an existing TarjetasCredito entity
        *
        */
    public void updateTarjetasCredito(TarjetasCredito entity)
        throws Exception;

    /**
         * Load an existing TarjetasCredito entity
         *
         */
    public TarjetasCredito getTarjetasCredito(Long idTarjeta)
        throws Exception;

    public List<TarjetasCredito> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<TarjetasCredito> findPageTarjetasCredito(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberTarjetasCredito() throws Exception;

    public List<TarjetasCreditoDTO> getDataTarjetasCredito()
        throws Exception;

    public void validateTarjetasCredito(TarjetasCredito tarjetasCredito)
        throws Exception;
}
