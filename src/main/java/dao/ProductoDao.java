package dao;

import java.util.List;
import models.Producto;

public interface ProductoDao {

    List<Producto> getProductos();

    public Producto getProducto(int id);

    public void insertProducto(Producto producto);

    public void updateProducto(Producto producto);

    public void deleteProducto(int id);
}
