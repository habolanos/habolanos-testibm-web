package com.habolanos.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
public class ClientesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ClientesDTO.class);
    private String apellidos;
    private String ciudad;
    private String direccion;
    private Long documento;
    private String nombres;
    private Long numeroTarjetasAsociadas;
    private String telefono;
    private String tipoDocumento;

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Long getNumeroTarjetasAsociadas() {
        return numeroTarjetasAsociadas;
    }

    public void setNumeroTarjetasAsociadas(Long numeroTarjetasAsociadas) {
        this.numeroTarjetasAsociadas = numeroTarjetasAsociadas;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}
