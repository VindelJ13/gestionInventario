package dao;

import java.util.List;
import models.Venta;

public interface VentaDao {

    List<Venta> getVentas();

    public List<Venta> getVentasOf(int id);

    public void insertVenta(Venta venta);

    //public void updateVenta(Venta venta);
    public void deleteVenta(int id);
}
