package com.habolanos.controller;

import com.habolanos.dto.AsesoresDTO;

import com.habolanos.mapper.AsesoresMapper;

import com.habolanos.modelo.*;

import com.habolanos.service.AsesoresService;

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
@RequestMapping("/asesores")
public class AsesoresRestController {
    private static final Logger log = LoggerFactory.getLogger(AsesoresRestController.class);
    @Autowired
    private AsesoresService asesoresService;
    @Autowired
    private AsesoresMapper asesoresMapper;

    @PostMapping(value = "/saveAsesores")
    public void saveAsesores(@RequestBody
    AsesoresDTO asesoresDTO) throws Exception {
        try {
            Asesores asesores = asesoresMapper.asesoresDTOToAsesores(asesoresDTO);

            asesoresService.saveAsesores(asesores);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @DeleteMapping(value = "/deleteAsesores/{idAsesor}")
    public void deleteAsesores(@PathVariable("idAsesor")
    Long idAsesor) throws Exception {
        try {
            Asesores asesores = asesoresService.getAsesores(idAsesor);

            asesoresService.deleteAsesores(asesores);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @PutMapping(value = "/updateAsesores/")
    public void updateAsesores(@RequestBody
    AsesoresDTO asesoresDTO) throws Exception {
        try {
            Asesores asesores = asesoresMapper.asesoresDTOToAsesores(asesoresDTO);

            asesoresService.updateAsesores(asesores);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getDataAsesores")
    public List<AsesoresDTO> getDataAsesores() throws Exception {
        try {
            return asesoresService.getDataAsesores();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @GetMapping(value = "/getAsesores/{idAsesor}")
    public AsesoresDTO getAsesores(@PathVariable("idAsesor")
    Long idAsesor) throws Exception {
        try {
            Asesores asesores = asesoresService.getAsesores(idAsesor);

            return asesoresMapper.asesoresToAsesoresDTO(asesores);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}
