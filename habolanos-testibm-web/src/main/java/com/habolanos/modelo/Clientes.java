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
@Table(name = "clientes", schema = "public")
public class Clientes implements java.io.Serializable {
    @NotNull
    private Long documento;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String apellidos;
    @NotNull
    @NotEmpty
    @Size(max = 30)
    private String ciudad;
    @NotNull
    @NotEmpty
    @Size(max = 100)
    private String direccion;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String nombres;
    private Long numeroTarjetasAsociadas;
    private String telefono;
    @NotNull
    @NotEmpty
    @Size(max = 2)
    private String tipoDocumento;
    private Set<TarjetasCredito> tarjetasCreditos = new HashSet<TarjetasCredito>(0);

    public Clientes() {
    }

    public Clientes(Long documento, String apellidos, String ciudad,
        String direccion, String nombres, Long numeroTarjetasAsociadas,
        Set<TarjetasCredito> tarjetasCreditos, String telefono,
        String tipoDocumento) {
        this.documento = documento;
        this.apellidos = apellidos;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nombres = nombres;
        this.numeroTarjetasAsociadas = numeroTarjetasAsociadas;
        this.telefono = telefono;
        this.tipoDocumento = tipoDocumento;
        this.tarjetasCreditos = tarjetasCreditos;
    }

    @Id
    @Column(name = "documento", unique = true, nullable = false)
    public Long getDocumento() {
        return this.documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    @Column(name = "apellidos", nullable = false)
    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Column(name = "ciudad", nullable = false)
    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Column(name = "direccion", nullable = false)
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "nombres", nullable = false)
    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Column(name = "numero_tarjetas_asociadas")
    public Long getNumeroTarjetasAsociadas() {
        return this.numeroTarjetasAsociadas;
    }

    public void setNumeroTarjetasAsociadas(Long numeroTarjetasAsociadas) {
        this.numeroTarjetasAsociadas = numeroTarjetasAsociadas;
    }

    @Column(name = "telefono")
    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Column(name = "tipo_documento", nullable = false)
    public String getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "clientes")
    public Set<TarjetasCredito> getTarjetasCreditos() {
        return this.tarjetasCreditos;
    }

    public void setTarjetasCreditos(Set<TarjetasCredito> tarjetasCreditos) {
        this.tarjetasCreditos = tarjetasCreditos;
    }
}
