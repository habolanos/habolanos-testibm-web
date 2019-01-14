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
public class TarjetasCreditoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TarjetasCreditoDTO.class);
    private String ccv;
    private Long estado;
    private Date fechaDesde;
    private Date fechaHasta;
    private String franquicia;
    private Long idTarjeta;
    private Double montoAvances;
    private Double montoCredito;
    private Long numero;
    private String numero01;
    private String numero02;
    private String numero03;
    private String numero04;
    private Long idAsesor_Asesores;
    private Long documento_Clientes;

    public String getCcv() {
        return ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

    public Long getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(Long idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public Double getMontoAvances() {
        return montoAvances;
    }

    public void setMontoAvances(Double montoAvances) {
        this.montoAvances = montoAvances;
    }

    public Double getMontoCredito() {
        return montoCredito;
    }

    public void setMontoCredito(Double montoCredito) {
        this.montoCredito = montoCredito;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getNumero01() {
        return numero01;
    }

    public void setNumero01(String numero01) {
        this.numero01 = numero01;
    }

    public String getNumero02() {
        return numero02;
    }

    public void setNumero02(String numero02) {
        this.numero02 = numero02;
    }

    public String getNumero03() {
        return numero03;
    }

    public void setNumero03(String numero03) {
        this.numero03 = numero03;
    }

    public String getNumero04() {
        return numero04;
    }

    public void setNumero04(String numero04) {
        this.numero04 = numero04;
    }

    public Long getIdAsesor_Asesores() {
        return idAsesor_Asesores;
    }

    public void setIdAsesor_Asesores(Long idAsesor_Asesores) {
        this.idAsesor_Asesores = idAsesor_Asesores;
    }

    public Long getDocumento_Clientes() {
        return documento_Clientes;
    }

    public void setDocumento_Clientes(Long documento_Clientes) {
        this.documento_Clientes = documento_Clientes;
    }
}
