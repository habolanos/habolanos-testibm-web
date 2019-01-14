package com.habolanos.mapper;

import com.habolanos.dto.TarjetasCreditoDTO;

import com.habolanos.modelo.*;
import com.habolanos.modelo.TarjetasCredito;

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
public class TarjetasCreditoMapperImpl implements TarjetasCreditoMapper {
    private static final Logger log = LoggerFactory.getLogger(TarjetasCreditoMapperImpl.class);

    /**
    * Service injected by Spring that manages Asesores entities
    *
    */
    @Autowired
    AsesoresService serviceAsesores1;

    /**
    * Service injected by Spring that manages Clientes entities
    *
    */
    @Autowired
    ClientesService serviceClientes2;

    @Transactional(readOnly = true)
    public TarjetasCreditoDTO tarjetasCreditoToTarjetasCreditoDTO(
        TarjetasCredito tarjetasCredito) throws Exception {
        try {
            TarjetasCreditoDTO tarjetasCreditoDTO = new TarjetasCreditoDTO();

            tarjetasCreditoDTO.setIdTarjeta(tarjetasCredito.getIdTarjeta());
            tarjetasCreditoDTO.setCcv((tarjetasCredito.getCcv() != null)
                ? tarjetasCredito.getCcv() : null);
            tarjetasCreditoDTO.setEstado((tarjetasCredito.getEstado() != null)
                ? tarjetasCredito.getEstado() : null);
            tarjetasCreditoDTO.setFechaDesde(tarjetasCredito.getFechaDesde());
            tarjetasCreditoDTO.setFechaHasta(tarjetasCredito.getFechaHasta());
            tarjetasCreditoDTO.setFranquicia((tarjetasCredito.getFranquicia() != null)
                ? tarjetasCredito.getFranquicia() : null);
            tarjetasCreditoDTO.setMontoAvances((tarjetasCredito.getMontoAvances() != null)
                ? tarjetasCredito.getMontoAvances() : null);
            tarjetasCreditoDTO.setMontoCredito((tarjetasCredito.getMontoCredito() != null)
                ? tarjetasCredito.getMontoCredito() : null);
            tarjetasCreditoDTO.setNumero((tarjetasCredito.getNumero() != null)
                ? tarjetasCredito.getNumero() : null);
            tarjetasCreditoDTO.setNumero01((tarjetasCredito.getNumero01() != null)
                ? tarjetasCredito.getNumero01() : null);
            tarjetasCreditoDTO.setNumero02((tarjetasCredito.getNumero02() != null)
                ? tarjetasCredito.getNumero02() : null);
            tarjetasCreditoDTO.setNumero03((tarjetasCredito.getNumero03() != null)
                ? tarjetasCredito.getNumero03() : null);
            tarjetasCreditoDTO.setNumero04((tarjetasCredito.getNumero04() != null)
                ? tarjetasCredito.getNumero04() : null);
            tarjetasCreditoDTO.setIdAsesor_Asesores((tarjetasCredito.getAsesores()
                                                                    .getIdAsesor() != null)
                ? tarjetasCredito.getAsesores().getIdAsesor() : null);
            tarjetasCreditoDTO.setDocumento_Clientes((tarjetasCredito.getClientes()
                                                                     .getDocumento() != null)
                ? tarjetasCredito.getClientes().getDocumento() : null);

            return tarjetasCreditoDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public TarjetasCredito tarjetasCreditoDTOToTarjetasCredito(
        TarjetasCreditoDTO tarjetasCreditoDTO) throws Exception {
        try {
            TarjetasCredito tarjetasCredito = new TarjetasCredito();

            tarjetasCredito.setIdTarjeta(tarjetasCreditoDTO.getIdTarjeta());
            tarjetasCredito.setCcv((tarjetasCreditoDTO.getCcv() != null)
                ? tarjetasCreditoDTO.getCcv() : null);
            tarjetasCredito.setEstado((tarjetasCreditoDTO.getEstado() != null)
                ? tarjetasCreditoDTO.getEstado() : null);
            tarjetasCredito.setFechaDesde(tarjetasCreditoDTO.getFechaDesde());
            tarjetasCredito.setFechaHasta(tarjetasCreditoDTO.getFechaHasta());
            tarjetasCredito.setFranquicia((tarjetasCreditoDTO.getFranquicia() != null)
                ? tarjetasCreditoDTO.getFranquicia() : null);
            tarjetasCredito.setMontoAvances((tarjetasCreditoDTO.getMontoAvances() != null)
                ? tarjetasCreditoDTO.getMontoAvances() : null);
            tarjetasCredito.setMontoCredito((tarjetasCreditoDTO.getMontoCredito() != null)
                ? tarjetasCreditoDTO.getMontoCredito() : null);
            tarjetasCredito.setNumero((tarjetasCreditoDTO.getNumero() != null)
                ? tarjetasCreditoDTO.getNumero() : null);
            tarjetasCredito.setNumero01((tarjetasCreditoDTO.getNumero01() != null)
                ? tarjetasCreditoDTO.getNumero01() : null);
            tarjetasCredito.setNumero02((tarjetasCreditoDTO.getNumero02() != null)
                ? tarjetasCreditoDTO.getNumero02() : null);
            tarjetasCredito.setNumero03((tarjetasCreditoDTO.getNumero03() != null)
                ? tarjetasCreditoDTO.getNumero03() : null);
            tarjetasCredito.setNumero04((tarjetasCreditoDTO.getNumero04() != null)
                ? tarjetasCreditoDTO.getNumero04() : null);

            Asesores asesores = new Asesores();

            if (tarjetasCreditoDTO.getIdAsesor_Asesores() != null) {
                asesores = serviceAsesores1.getAsesores(tarjetasCreditoDTO.getIdAsesor_Asesores());
            }

            if (asesores != null) {
                tarjetasCredito.setAsesores(asesores);
            }

            Clientes clientes = new Clientes();

            if (tarjetasCreditoDTO.getDocumento_Clientes() != null) {
                clientes = serviceClientes2.getClientes(tarjetasCreditoDTO.getDocumento_Clientes());
            }

            if (clientes != null) {
                tarjetasCredito.setClientes(clientes);
            }

            return tarjetasCredito;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<TarjetasCreditoDTO> listTarjetasCreditoToListTarjetasCreditoDTO(
        List<TarjetasCredito> listTarjetasCredito) throws Exception {
        try {
            List<TarjetasCreditoDTO> tarjetasCreditoDTOs = new ArrayList<TarjetasCreditoDTO>();

            for (TarjetasCredito tarjetasCredito : listTarjetasCredito) {
                TarjetasCreditoDTO tarjetasCreditoDTO = tarjetasCreditoToTarjetasCreditoDTO(tarjetasCredito);

                tarjetasCreditoDTOs.add(tarjetasCreditoDTO);
            }

            return tarjetasCreditoDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<TarjetasCredito> listTarjetasCreditoDTOToListTarjetasCredito(
        List<TarjetasCreditoDTO> listTarjetasCreditoDTO)
        throws Exception {
        try {
            List<TarjetasCredito> listTarjetasCredito = new ArrayList<TarjetasCredito>();

            for (TarjetasCreditoDTO tarjetasCreditoDTO : listTarjetasCreditoDTO) {
                TarjetasCredito tarjetasCredito = tarjetasCreditoDTOToTarjetasCredito(tarjetasCreditoDTO);

                listTarjetasCredito.add(tarjetasCredito);
            }

            return listTarjetasCredito;
        } catch (Exception e) {
            throw e;
        }
    }
}
