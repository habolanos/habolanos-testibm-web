package com.habolanos.modelo;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author habolanos
* @email haroldadrian@gmail.com
*
*/
@Entity
@Table(name = "consumos", schema = "public")
@SequenceGenerator(name="SEQ_CONSUMOS")
public class Consumos implements java.io.Serializable {
    //@NotNull
    private Long idConsumo;
    @NotNull
    private TarjetasCredito tarjetasCredito;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String descripcion;
    @NotNull
    private Date fecha;
    private Date fechaHoraRegistro;
    private Double monto;

    public Consumos() {
    }

    public Consumos(Long idConsumo, String descripcion, Date fecha,
        Date fechaHoraRegistro, Double monto, TarjetasCredito tarjetasCredito) {
        this.idConsumo = idConsumo;
        this.tarjetasCredito = tarjetasCredito;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.fechaHoraRegistro = fechaHoraRegistro;
        this.monto = monto;
    }

    @Id
    @Column(name = "id_consumo", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CONSUMOS")
    public Long getIdConsumo() {
        return this.idConsumo;
    }

    public void setIdConsumo(Long idConsumo) {
        this.idConsumo = idConsumo;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tarjeta")
    public TarjetasCredito getTarjetasCredito() {
        return this.tarjetasCredito;
    }

    public void setTarjetasCredito(TarjetasCredito tarjetasCredito) {
        this.tarjetasCredito = tarjetasCredito;
    }

    @Column(name = "descripcion", nullable = false)
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "fecha", nullable = false)
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Column(name = "fecha_hora_registro")
    public Date getFechaHoraRegistro() {
        return this.fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(Date fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    @Column(name = "monto")
    public Double getMonto() {
        return this.monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
