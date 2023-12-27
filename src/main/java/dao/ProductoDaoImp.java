package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import models.Producto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ProductoDaoImp implements ProductoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Producto> getProductos() {
        String query = "FROM Producto";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void insertProducto(Producto producto) {
        entityManager.merge(producto);
    }

    @Override
    public void deleteProducto(int id) {
        Producto producto = entityManager.find(Producto.class, id);
        entityManager.remove(producto);
    }

    @Override
    public Producto getProducto(int id) {
        String query = "FROM Producto p WHERE p.id_producto = :id_producto";
        return entityManager.createQuery(query, Producto.class)
                .setParameter("id_producto", id)
                .getSingleResult();
    }

    @Override
    public void updateProducto(Producto producto) {
        // Verificar si la entidad está bajo la gestión del contexto de persistencia actual
        if (!entityManager.contains(producto)) {
            // Si no está bajo la gestión, cargar la entidad actual desde la base de datos
            Producto productoExistente = entityManager.find(Producto.class, producto.getId_producto());

            // Verificar si la entidad existe en la base de datos antes de intentar actualizar
            if (productoExistente != null) {
                // Actualizar los campos de la entidad existente con los valores del producto proporcionado
                productoExistente.setNombre(producto.getNombre());
                productoExistente.setDescripcion(producto.getDescripcion());
                productoExistente.setPrecio(producto.getPrecio());
                productoExistente.setCosto_producto(producto.getCosto_producto());
                productoExistente.setExistencia(producto.getExistencia());

                // Merge para sincronizar los cambios con la base de datos
                entityManager.merge(productoExistente);
            }
        }
    }

}
