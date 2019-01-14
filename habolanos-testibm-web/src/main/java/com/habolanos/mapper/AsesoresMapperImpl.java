package com.habolanos.mapper;

import com.habolanos.dto.AsesoresDTO;

import com.habolanos.modelo.*;
import com.habolanos.modelo.Asesores;

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
public class AsesoresMapperImpl implements AsesoresMapper {
    private static final Logger log = LoggerFactory.getLogger(AsesoresMapperImpl.class);

    @Transactional(readOnly = true)
    public AsesoresDTO asesoresToAsesoresDTO(Asesores asesores)
        throws Exception {
        try {
            AsesoresDTO asesoresDTO = new AsesoresDTO();

            asesoresDTO.setIdAsesor(asesores.getIdAsesor());
            asesoresDTO.setApellidos((asesores.getApellidos() != null)
                ? asesores.getApellidos() : null);
            asesoresDTO.setEspecialidad((asesores.getEspecialidad() != null)
                ? asesores.getEspecialidad() : null);
            asesoresDTO.setNombres((asesores.getNombres() != null)
                ? asesores.getNombres() : null);

            return asesoresDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public Asesores asesoresDTOToAsesores(AsesoresDTO asesoresDTO)
        throws Exception {
        try {
            Asesores asesores = new Asesores();

            asesores.setIdAsesor(asesoresDTO.getIdAsesor());
            asesores.setApellidos((asesoresDTO.getApellidos() != null)
                ? asesoresDTO.getApellidos() : null);
            asesores.setEspecialidad((asesoresDTO.getEspecialidad() != null)
                ? asesoresDTO.getEspecialidad() : null);
            asesores.setNombres((asesoresDTO.getNombres() != null)
                ? asesoresDTO.getNombres() : null);

            return asesores;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<AsesoresDTO> listAsesoresToListAsesoresDTO(
        List<Asesores> listAsesores) throws Exception {
        try {
            List<AsesoresDTO> asesoresDTOs = new ArrayList<AsesoresDTO>();

            for (Asesores asesores : listAsesores) {
                AsesoresDTO asesoresDTO = asesoresToAsesoresDTO(asesores);

                asesoresDTOs.add(asesoresDTO);
            }

            return asesoresDTOs;
        } catch (Exception e) {
            throw e;
        }
    }

    @Transactional(readOnly = true)
    public List<Asesores> listAsesoresDTOToListAsesores(
        List<AsesoresDTO> listAsesoresDTO) throws Exception {
        try {
            List<Asesores> listAsesores = new ArrayList<Asesores>();

            for (AsesoresDTO asesoresDTO : listAsesoresDTO) {
                Asesores asesores = asesoresDTOToAsesores(asesoresDTO);

                listAsesores.add(asesores);
            }

            return listAsesores;
        } catch (Exception e) {
            throw e;
        }
    }
}
