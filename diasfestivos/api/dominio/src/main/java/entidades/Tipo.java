package  diasfestivos.api.dominio.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos")
public class Tipo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tipos")
    @GenericGenerator(name = "secuencia_tipos", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "tipo", length = 100, unique = true)
    private String estandar;

    public Tipo() {
    }

    public Tipo(int id, String estandar) {
        this.id = id;
        this.estandar = estandar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstandar() {
        return estandar;
    }

    public void setEstandar(String estandar) {
        this.estandar = estandar;
    }

}
