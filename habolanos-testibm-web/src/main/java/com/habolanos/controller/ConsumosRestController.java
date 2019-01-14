package com.habolanos.controller;

import com.habolanos.dto.ConsumosDTO;

import com.habolanos.mapper.ConsumosMapper;

import com.habolanos.modelo.*;

import com.habolanos.service.ConsumosService;

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
@RequestMapping("/consumos")
public class ConsumosRestController {
    private static final Logger log = LoggerFactory.getLogger(ConsumosRestController.class);
    @Autowired
    private ConsumosService consumosService;
    @Autowired
    private ConsumosMapper consumosMapper;

    @PostMapping(value = "/saveConsumos")
    public void saveConsumos(@RequestBody
    ConsumosDTO consumosDTO) throws Exception {
        try {
            Consumos consumos = consumosMapper.consumosDTOToConsumos(consumosDTO);

            consumosService.saveConsumos(consumos);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteConsumos/{idConsumo}")
    public void deleteConsumos(@PathVariable("idConsumo")
    Long idConsumo) throws Exception {
        try {
            Consumos consumos = consumosService.getConsumos(idConsumo);

            consumosService.deleteConsumos(consumos);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateConsumos/")
    public void updateConsumos(@RequestBody
    ConsumosDTO consumosDTO) throws Exception {
        try {
            Consumos consumos = consumosMapper.consumosDTOToConsumos(consumosDTO);

            consumosService.updateConsumos(consumos);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataConsumos")
    public List<ConsumosDTO> getDataConsumos() throws Exception {
        try {
            return consumosService.getDataConsumos();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getConsumos/{idConsumo}")
    public ConsumosDTO getConsumos(@PathVariable("idConsumo")
    Long idConsumo) throws Exception {
        try {
            Consumos consumos = consumosService.getConsumos(idConsumo);

            return consumosMapper.consumosToConsumosDTO(consumos);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
