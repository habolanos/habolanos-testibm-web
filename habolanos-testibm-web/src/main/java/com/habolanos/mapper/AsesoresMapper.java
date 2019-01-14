package com.habolanos.mapper;

import com.habolanos.dto.AsesoresDTO;

import com.habolanos.modelo.Asesores;

import java.util.List;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
public interface AsesoresMapper {
    public AsesoresDTO asesoresToAsesoresDTO(Asesores asesores)
        throws Exception;

    public Asesores asesoresDTOToAsesores(AsesoresDTO asesoresDTO)
        throws Exception;

    public List<AsesoresDTO> listAsesoresToListAsesoresDTO(
        List<Asesores> asesoress) throws Exception;

    public List<Asesores> listAsesoresDTOToListAsesores(
        List<AsesoresDTO> asesoresDTOs) throws Exception;
}
