package com.habolanos.service;

import com.habolanos.dto.AsesoresDTO;

import com.habolanos.modelo.Asesores;

import java.math.*;

import java.util.*;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
public interface AsesoresService {
    public List<Asesores> getAsesores() throws Exception;

    /**
         * Save an new Asesores entity
         */
    public void saveAsesores(Asesores entity) throws Exception;

    /**
         * Delete an existing Asesores entity
         *
         */
    public void deleteAsesores(Asesores entity) throws Exception;

    /**
        * Update an existing Asesores entity
        *
        */
    public void updateAsesores(Asesores entity) throws Exception;

    /**
         * Load an existing Asesores entity
         *
         */
    public Asesores getAsesores(Long idAsesor) throws Exception;

    public List<Asesores> findByCriteria(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Asesores> findPageAsesores(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAsesores() throws Exception;

    public List<AsesoresDTO> getDataAsesores() throws Exception;

    public void validateAsesores(Asesores asesores) throws Exception;
}
