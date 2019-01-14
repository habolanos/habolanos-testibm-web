package com.habolanos.view;

import com.habolanos.dto.AsesoresDTO;
import com.habolanos.dto.ClientesDTO;
import com.habolanos.dto.ConsumosDTO;
import com.habolanos.dto.TarjetasCreditoDTO;

import com.habolanos.modelo.Asesores;
import com.habolanos.modelo.Clientes;
import com.habolanos.modelo.Consumos;
import com.habolanos.modelo.TarjetasCredito;

import java.math.*;

import java.util.*;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
public interface BusinessDelegator {
    public List<Asesores> getAsesores() throws Exception;

    public void saveAsesores(Asesores entity) throws Exception;

    public void deleteAsesores(Asesores entity) throws Exception;

    public void updateAsesores(Asesores entity) throws Exception;

    public Asesores getAsesores(Long idAsesor) throws Exception;

    public List<Asesores> findByCriteriaInAsesores(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Asesores> findPageAsesores(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberAsesores() throws Exception;

    public List<AsesoresDTO> getDataAsesores() throws Exception;

    public void validateAsesores(Asesores asesores) throws Exception;

    public List<Clientes> getClientes() throws Exception;

    public void saveClientes(Clientes entity) throws Exception;

    public void deleteClientes(Clientes entity) throws Exception;

    public void updateClientes(Clientes entity) throws Exception;

    public Clientes getClientes(Long documento) throws Exception;

    public List<Clientes> findByCriteriaInClientes(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Clientes> findPageClientes(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberClientes() throws Exception;

    public List<ClientesDTO> getDataClientes() throws Exception;

    public void validateClientes(Clientes clientes) throws Exception;

    public List<Consumos> getConsumos() throws Exception;

    public void saveConsumos(Consumos entity) throws Exception;

    public void deleteConsumos(Consumos entity) throws Exception;

    public void updateConsumos(Consumos entity) throws Exception;

    public Consumos getConsumos(Long idConsumo) throws Exception;

    public List<Consumos> findByCriteriaInConsumos(Object[] variables,
        Object[] variablesBetween, Object[] variablesBetweenDates)
        throws Exception;

    public List<Consumos> findPageConsumos(String sortColumnName,
        boolean sortAscending, int startRow, int maxResults)
        throws Exception;

    public Long findTotalNumberConsumos() throws Exception;

    public List<ConsumosDTO> getDataConsumos() throws Exception;

    public void validateConsumos(Consumos consumos) throws Exception;

    public List<TarjetasCredito> getTarjetasCredito() throws Exception;

    public void saveTarjetasCredito(TarjetasCredito entity)
        throws Exception;

    public void deleteTarjetasCredito(TarjetasCredito entity)
        throws Exception;

    public void updateTarjetasCredito(TarjetasCredito entity)
        throws Exception;

    public TarjetasCredito getTarjetasCredito(Long idTarjeta)
        throws Exception;

    public List<TarjetasCredito> findByCriteriaInTarjetasCredito(
        Object[] variables, Object[] variablesBetween,
        Object[] variablesBetweenDates) throws Exception;

    public List<TarjetasCredito> findPageTarjetasCredito(
        String sortColumnName, boolean sortAscending, int startRow,
        int maxResults) throws Exception;

    public Long findTotalNumberTarjetasCredito() throws Exception;

    public List<TarjetasCreditoDTO> getDataTarjetasCredito()
        throws Exception;

    public void validateTarjetasCredito(TarjetasCredito tarjetasCredito)
        throws Exception;
}
