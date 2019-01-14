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
public class ConsumosDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ConsumosDTO.class);
    private String descripcion;
    private Date fecha;
    private Date fechaHoraRegistro;
    private Long idConsumo;
    private Double monto;
    private Long idTarjeta_TarjetasCredito;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(Date fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public Long getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(Long idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Long getIdTarjeta_TarjetasCredito() {
        return idTarjeta_TarjetasCredito;
    }

    public void setIdTarjeta_TarjetasCredito(Long idTarjeta_TarjetasCredito) {
        this.idTarjeta_TarjetasCredito = idTarjeta_TarjetasCredito;
    }
}
