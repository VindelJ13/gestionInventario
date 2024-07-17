package controllers;

import dao.VentaDao;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import models.Venta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {

    @Autowired
    private VentaDao ventaDao;

    @GetMapping("api/ventas")
    public List<Venta> getVentas() {
        return ventaDao.getVentas();
    }

    @PostMapping("api/ventas")
    public ResponseEntity<String> insertVenta(@RequestBody Venta venta) {
        try {
            ventaDao.insertVenta(venta);
            return ResponseEntity.ok("La venta se registró con éxito");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo procesar la solicitud: Producto no encontrado");
        }
    }

    @GetMapping("api/ventas/{id}")
    public List<Venta> getVentasOf(@PathVariable int id) {
        return ventaDao.getVentasOf(id);
    }

    @DeleteMapping("api/ventas/{id}")
    public void deleteVenta(@PathVariable int id) {
        ventaDao.deleteVenta(id);
    }

}
