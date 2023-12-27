package controllers;

import dao.ProductoDao;
import java.util.List;
import models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {

    @Autowired
    private ProductoDao productoDao;

    @GetMapping("api/productos")
    public List<Producto> getProductos() {
        return productoDao.getProductos();
    }

    @PostMapping("api/productos")
    public void insertProducto(@RequestBody Producto producto) {
        productoDao.insertProducto(producto);
    }

    /*@DeleteMapping("api/productos/{id}")
    public void deleteProducto(@PathVariable int id) {
        productoDao.deleteProducto(id);
    }*/
    @DeleteMapping("api/productos/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable int id) {
        try {
            productoDao.deleteProducto(id);
            return new ResponseEntity<>("Producto eliminado con éxito", HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>("No se puede eliminar el producto debido a restricciones de integridad referencial.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el producto", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("api/productos/{id}")
    public Producto getProducto(@PathVariable int id) {
        return productoDao.getProducto(id);
    }

    @PutMapping("api/productos/{id}")
    public Producto updateProducto(@PathVariable int id, @RequestBody Producto productoActualizado) {
        productoActualizado.setId_producto(id);
        productoDao.updateProducto(productoActualizado);

        return productoActualizado;
    }

}
