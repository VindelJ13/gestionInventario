package controllers;

import dao.VentaDao;
import java.util.List;
import models.Venta;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void insertVenta(@RequestBody Venta venta) {
        ventaDao.insertVenta(venta);
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
