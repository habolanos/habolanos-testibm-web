package com.habolanos.controller;

import com.habolanos.dto.TarjetasCreditoDTO;

import com.habolanos.mapper.TarjetasCreditoMapper;

import com.habolanos.modelo.*;

import com.habolanos.service.TarjetasCreditoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/tarjetasCredito")
public class TarjetasCreditoRestController {
    private static final Logger log = LoggerFactory.getLogger(TarjetasCreditoRestController.class);
    @Autowired
    private TarjetasCreditoService tarjetasCreditoService;
    @Autowired
    private TarjetasCreditoMapper tarjetasCreditoMapper;

    @PostMapping(value = "/saveTarjetasCredito")
    public void saveTarjetasCredito(
        @RequestBody
    TarjetasCreditoDTO tarjetasCreditoDTO) throws Exception {
        try {
            TarjetasCredito tarjetasCredito = tarjetasCreditoMapper.tarjetasCreditoDTOToTarjetasCredito(tarjetasCreditoDTO);

            tarjetasCreditoService.saveTarjetasCredito(tarjetasCredito);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteTarjetasCredito/{idTarjeta}")
    public void deleteTarjetasCredito(@PathVariable("idTarjeta")
    Long idTarjeta) throws Exception {
        try {
            TarjetasCredito tarjetasCredito = tarjetasCreditoService.getTarjetasCredito(idTarjeta);

            tarjetasCreditoService.deleteTarjetasCredito(tarjetasCredito);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateTarjetasCredito/")
    public void updateTarjetasCredito(
        @RequestBody
    TarjetasCreditoDTO tarjetasCreditoDTO) throws Exception {
        try {
            TarjetasCredito tarjetasCredito = tarjetasCreditoMapper.tarjetasCreditoDTOToTarjetasCredito(tarjetasCreditoDTO);

            tarjetasCreditoService.updateTarjetasCredito(tarjetasCredito);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataTarjetasCredito")
    public List<TarjetasCreditoDTO> getDataTarjetasCredito()
        throws Exception {
        try {
            return tarjetasCreditoService.getDataTarjetasCredito();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getTarjetasCredito/{idTarjeta}")
    public TarjetasCreditoDTO getTarjetasCredito(
        @PathVariable("idTarjeta")
    Long idTarjeta) throws Exception {
        try {
            TarjetasCredito tarjetasCredito = tarjetasCreditoService.getTarjetasCredito(idTarjeta);

            return tarjetasCreditoMapper.tarjetasCreditoToTarjetasCreditoDTO(tarjetasCredito);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
