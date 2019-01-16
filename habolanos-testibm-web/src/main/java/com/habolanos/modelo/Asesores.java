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
@Table(name = "asesores", schema = "public")
@SequenceGenerator(name="SEQ_ASESORES")
public class Asesores implements java.io.Serializable {
    //@NotNull
    private Long idAsesor;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String apellidos;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String especialidad;
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String nombres;
    private Set<TarjetasCredito> tarjetasCreditos = new HashSet<TarjetasCredito>(0);

    public Asesores() {
    }

    public Asesores(Long idAsesor, String apellidos, String especialidad,
        String nombres, Set<TarjetasCredito> tarjetasCreditos) {
        this.idAsesor = idAsesor;
        this.apellidos = apellidos;
        this.especialidad = especialidad;
        this.nombres = nombres;
        this.tarjetasCreditos = tarjetasCreditos;
    }

    @Id
    @Column(name = "id_asesor", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ASESORES")
    public Long getIdAsesor() {
        return this.idAsesor;
    }

    public void setIdAsesor(Long idAsesor) {
        this.idAsesor = idAsesor;
    }

    @Column(name = "apellidos", nullable = false)
    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Column(name = "especialidad", nullable = false)
    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Column(name = "nombres", nullable = false)
    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "asesores")
    public Set<TarjetasCredito> getTarjetasCreditos() {
        return this.tarjetasCreditos;
    }

    public void setTarjetasCreditos(Set<TarjetasCredito> tarjetasCreditos) {
        this.tarjetasCreditos = tarjetasCreditos;
    }
}
