package com.habolanos.mapper;

import com.habolanos.dto.ConsumosDTO;

import com.habolanos.modelo.Consumos;

import java.util.List;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
public interface ConsumosMapper {
    public ConsumosDTO consumosToConsumosDTO(Consumos consumos)
        throws Exception;

    public Consumos consumosDTOToConsumos(ConsumosDTO consumosDTO)
        throws Exception;

    public List<ConsumosDTO> listConsumosToListConsumosDTO(
        List<Consumos> consumoss) throws Exception;

    public List<Consumos> listConsumosDTOToListConsumos(
        List<ConsumosDTO> consumosDTOs) throws Exception;
}
