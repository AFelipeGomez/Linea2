/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicundi.serviciobasicoejb.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlElement;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

/**
 *
 * @author Andres Gomez
 */
@Entity
@Table(name = "medico")

@NamedQueries({
    @NamedQuery(name = "Medico.listar", query = "SELECT a FROM Medico a"),
    @NamedQuery(name = "Medico.validarId", query = "SELECT COUNT(a.id)  FROM Medico a WHERE a.id = :id"),
    @NamedQuery(name = "Medico.listarMedicoById", query = "SELECT a FROM Medico a WHERE a.id = :id"),
        @NamedQuery(name = "Medico.EliminarMedico", query = "DELETE FROM Medico a WHERE a.id = :id"),
        
})
public class Medico implements Serializable {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 25)
    private String nombre;
    
    @Column(name = "apellido", nullable = false, length = 25)
    private String apellido;
    
    @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
        + "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
        + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
        + "(?:[A-Za-z0-9-]*[A-Za-z0-9])?",
        message = "{Correo Invalido}")
    @Column(name = "correo", nullable = false, length = 60, unique = true)
    private String correo;
    @XmlElement    
    @XmlInverseReference (mappedBy = "medico")
    @OneToMany(mappedBy = "medico", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)    
    private List<Consulta> consultaMedico;
    
    @NotNull(message = "Objeto direccion es requerido")
    @OneToOne(mappedBy = "medico", cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
    @XmlElement
    @XmlInverseReference (mappedBy = "medico")
    private Direccion direccion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    @JsonManagedReference

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
   @JsonManagedReference
    public List<Consulta> getConsultaMedico() {
        return consultaMedico;
    }

    public void setConsultaMedico(List<Consulta> consultaMedico) {
        this.consultaMedico = consultaMedico;
    }

  
    
}
