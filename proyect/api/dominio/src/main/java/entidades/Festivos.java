package diasfestiavos.api.dominio.entidades;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
@Entity
@Table(name = "festivos")
public class Festivos {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_Festivos")
        @GenericGenerator(name = "secuencia_Festivos", strategy = "increment")
        @Column(name = "id")
        private int id;

        @Column(name = "nombre", length = 100, unique = true)
        private String nombre;

        @Column(name = "dia", length = 100, unique = true)
        private int dia;

        @Column(name = "mes", length = 100, unique = true)
        private int mes;

        @Column(name = "dia pascua", length = 100, unique = true)
        private int dia_pascua;
        @ManyToOne
        @JoinColumn(name = "id tipo", referencedColumnName = "id")
        private Tipo clasificacionFestivo;

        public Festivos() {
        }

        public Festivos(int id, String nombre, int dia, int mes, int dia_pascua, Tipo clasificacionFestivo) {
            this.id = id;
            this.nombre = nombre;
            this.dia = dia;
            this.mes = mes;
            this.dia_pascua = dia_pascua;
            this.clasificacionFestivo = clasificacionFestivo;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public int getDia() {
            return dia;
        }

        public void setDia(int dia) {
            this.dia = dia;
        }

        public int getMes() {
            return mes;
        }

        public void setMes(int mes) {
            this.mes = mes;
        }

        public int getDia_pascua() {
            return dia_pascua;
        }

        public void setDia_pascua(int dia_pascua) {
            this.dia_pascua = dia_pascua;
        }

        public Tipo getClasificacionFestivo() {
            return clasificacionFestivo;
        }

        public void setClasificacionFestivo(Tipo clasificacionFestivo) {
            this.clasificacionFestivo = clasificacionFestivo;
        }

        } 


