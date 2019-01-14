package com.habolanos.mapper;

import com.habolanos.dto.ConsumosDTO;

import com.habolanos.modelo.*;
import com.habolanos.modelo.Consumos;

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
public class ConsumosMapperImpl implements ConsumosMapper {
    private static final Logger log = LoggerFactory.getLogger(ConsumosMapperImpl.class);

    /**
    * Service injected by Spring that manages TarjetasCredito entities
    *
    */
    @Autowired
    TarjetasCreditoService serviceTarjetasCredito1;

    @Transactional(readOnly = true)
    public ConsumosDTO consumosToConsumosDTO(Consumos consumos)
        throws Exception {
        try {
            ConsumosDTO consumosDTO = new ConsumosDTO();

            consumosDTO.setIdConsumo(consumos.getIdConsumo());
            consumosDTO.setDescripcion((consumos.getDescripcion() != null)
                ? consumos.getDescripcion() : null);
            consumosDTO.setFecha(consumos.getFecha());
            consumosDTO.setFechaHoraRegistro(consumos.getFechaHoraRegistro());
            consumosDTO.setMonto((consumos.getMonto() != null)
                ? consumos.getMonto() : null);
            consumosDTO.setIdTarjeta_TarjetasCredito((consumos.getTarjetasCredito()
                                                              .getIdTarjeta() != null)
                ? consumos.getTarjetasCredito().getIdTarjeta() : null);

            return consumosDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Consumos consumosDTOToConsumos(ConsumosDTO consumosDTO)
        throws Exception {
        try {
            Consumos consumos = new Consumos();

            consumos.setIdConsumo(consumosDTO.getIdConsumo());
            consumos.setDescripcion((consumosDTO.getDescripcion() != null)
                ? consumosDTO.getDescripcion() : null);
            consumos.setFecha(consumosDTO.getFecha());
            consumos.setFechaHoraRegistro(consumosDTO.getFechaHoraRegistro());
            consumos.setMonto((consumosDTO.getMonto() != null)
                ? consumosDTO.getMonto() : null);

            TarjetasCredito tarjetasCredito = new TarjetasCredito();

            if (consumosDTO.getIdTarjeta_TarjetasCredito() != null) {
                tarjetasCredito = serviceTarjetasCredito1.getTarjetasCredito(consumosDTO.getIdTarjeta_TarjetasCredito());
            }

            if (tarjetasCredito != null) {
                consumos.setTarjetasCredito(tarjetasCredito);
            }

            return consumos;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<ConsumosDTO> listConsumosToListConsumosDTO(
        List<Consumos> listConsumos) throws Exception {
        try {
            List<ConsumosDTO> consumosDTOs = new ArrayList<ConsumosDTO>();

            for (Consumos consumos : listConsumos) {
                ConsumosDTO consumosDTO = consumosToConsumosDTO(consumos);

                consumosDTOs.add(consumosDTO);
            }

            return consumosDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Consumos> listConsumosDTOToListConsumos(
        List<ConsumosDTO> listConsumosDTO) throws Exception {
        try {
            List<Consumos> listConsumos = new ArrayList<Consumos>();

            for (ConsumosDTO consumosDTO : listConsumosDTO) {
                Consumos consumos = consumosDTOToConsumos(consumosDTO);

                listConsumos.add(consumos);
            }

            return listConsumos;
        } catch (Exception e) {
            throw e;
        }
    }
}
