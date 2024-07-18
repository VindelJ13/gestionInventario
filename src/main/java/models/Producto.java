package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Productos")
public class Producto {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private int id_producto;
    @Getter
    @Setter
    @Column(name = "nombre")
    private String nombre;
    @Getter
    @Setter
    @Column(name = "descripcion")
    private String descripcion;
    @Getter
    @Setter
    @Column(name = "precio")
    private int precio;
    @Getter
    @Setter
    @Column(name = "costo_producto")
    private int costo_producto;
    @Getter
    @Setter
    @Column(name = "existencia")
    private int existencia;

}
