package com.habolanos.mapper;

import com.habolanos.dto.TarjetasCreditoDTO;

import com.habolanos.modelo.TarjetasCredito;

import java.util.List;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
public interface TarjetasCreditoMapper {
    public TarjetasCreditoDTO tarjetasCreditoToTarjetasCreditoDTO(
        TarjetasCredito tarjetasCredito) throws Exception;

    public TarjetasCredito tarjetasCreditoDTOToTarjetasCredito(
        TarjetasCreditoDTO tarjetasCreditoDTO) throws Exception;

    public List<TarjetasCreditoDTO> listTarjetasCreditoToListTarjetasCreditoDTO(
        List<TarjetasCredito> tarjetasCreditos) throws Exception;

    public List<TarjetasCredito> listTarjetasCreditoDTOToListTarjetasCredito(
        List<TarjetasCreditoDTO> tarjetasCreditoDTOs) throws Exception;
}
