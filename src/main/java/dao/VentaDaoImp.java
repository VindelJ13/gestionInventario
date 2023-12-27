package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import models.Venta;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class VentaDaoImp implements VentaDao {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Venta> getVentas() {
        String query = "FROM Venta";
        return entityManager.createQuery(query).getResultList();
    }
    
    @Override
    public void insertVenta(Venta venta) {
        entityManager.merge(venta);
    }
    
    @Override
    public List<Venta> getVentasOf(int id) {
        String query = "FROM Venta p WHERE p.id_producto.id_producto = :id_producto";
        return entityManager.createQuery(query, Venta.class)
                .setParameter("id_producto", id)
                .getResultList();
    }
    
    @Override
    public void deleteVenta(int id) {
        Venta venta = entityManager.find(Venta.class, id);
        entityManager.remove(venta);
    }
    
}
