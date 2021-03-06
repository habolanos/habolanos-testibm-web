package com.habolanos.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
@Entity
@Table(name = "tarjetas_credito", schema = "public")
@SequenceGenerator(name="SEQ_TARJETAS")
public class TarjetasCredito implements java.io.Serializable {
    //@NotNull
    private Long idTarjeta;
    @NotNull
    private Asesores asesores;
    @NotNull
    private Clientes clientes;
    @NotNull
    @NotEmpty
    @Size(max = 3)
    private String ccv;
    private Long estado;
    @NotNull
    private Date fechaDesde;
    @NotNull
    private Date fechaHasta;
    @NotNull
    @NotEmpty
    @Size(max = 10)
    private String franquicia;
    private Double montoAvances;
    private Double montoCredito;
    @NotNull
    private Long numero;
    
    private Set<Consumos> consumoses = new HashSet<Consumos>(0);

    public TarjetasCredito() {
    }

    public TarjetasCredito(Long idTarjeta, Asesores asesores, String ccv,
        Clientes clientes, Set<Consumos> consumoses, Long estado,
        Date fechaDesde, Date fechaHasta, String franquicia,
        Double montoAvances, Double montoCredito, Long numero) {
        this.idTarjeta = idTarjeta;
        this.asesores = asesores;
        this.clientes = clientes;
        this.ccv = ccv;
        this.estado = estado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.franquicia = franquicia;
        this.montoAvances = montoAvances;
        this.montoCredito = montoCredito;
        this.numero = numero;
        this.consumoses = consumoses;
    }

    @Id
    @Column(name = "id_tarjeta", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TARJETAS")
    public Long getIdTarjeta() {
        return this.idTarjeta;
    }

    public void setIdTarjeta(Long idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asesor")
    public Asesores getAsesores() {
        return this.asesores;
    }

    public void setAsesores(Asesores asesores) {
        this.asesores = asesores;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "documento")
    public Clientes getClientes() {
        return this.clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    @Column(name = "ccv", nullable = false)
    public String getCcv() {
        return this.ccv;
    }

    public void setCcv(String ccv) {
        this.ccv = ccv;
    }

    @Column(name = "estado")
    public Long getEstado() {
        return this.estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    @Column(name = "fecha_desde", nullable = false)
    public Date getFechaDesde() {
        return this.fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    @Column(name = "fecha_hasta", nullable = false)
    public Date getFechaHasta() {
        return this.fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    @Column(name = "franquicia", nullable = false)
    public String getFranquicia() {
        return this.franquicia;
    }

    public void setFranquicia(String franquicia) {
        this.franquicia = franquicia;
    }

    @Column(name = "monto_avances")
    public Double getMontoAvances() {
        return this.montoAvances;
    }

    public void setMontoAvances(Double montoAvances) {
        this.montoAvances = montoAvances;
    }

    @Column(name = "monto_credito")
    public Double getMontoCredito() {
        return this.montoCredito;
    }

    public void setMontoCredito(Double montoCredito) {
        this.montoCredito = montoCredito;
    }

    @Column(name = "numero", nullable = false)
    public Long getNumero() {
        return this.numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tarjetasCredito")
    public Set<Consumos> getConsumoses() {
        return this.consumoses;
    }

    public void setConsumoses(Set<Consumos> consumoses) {
        this.consumoses = consumoses;
    }
}
