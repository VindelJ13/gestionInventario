$(document).ready(async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    let gananciaT = 0;
    cargarVentas(id, gananciaT);
});
async function cargarVentas(id, gananciaT) {

    const request = await fetch('api/ventas/' + id, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const ventas = await request.json();
    const precio = ventas[0].id_producto.precio;
    const costo = ventas[0].id_producto.costo_producto;

    let plantilla = '';
    for (const venta of ventas) {
        let btnEliminar = '<button type="button" class="btnEliminar" onclick = eliminarVenta(' + venta.id_venta + ')>Eliminar</button>';
        let ganancia = (precio - costo) * venta.cantidad;
        gananciaT = gananciaT + ganancia;
        plantilla += `
        <tr>
            <td>${venta.id_venta}</td>
            <td>${venta.cantidad}</td>
            <td>${venta.fecha_venta}</td>
            <td>${ganancia}</td>
            <td>
                ${btnEliminar}
            </td>
        </tr>
`;
    }

    let plantilla_tfoot = `<tr>
                            <td colspan="3">Ganancia Total</td>
                            <td>${gananciaT}</td>
                        </tr>`;

    document.querySelector("#sectionProducto h2").innerHTML = "Producto: " + ventas[0].id_producto.nombre;
    document.querySelector("#sectionProducto h3").innerHTML = "Precio: " + precio;
    document.querySelector("#sectionProducto h4").innerHTML = "Costo: " + costo;
    document.querySelector("#tablaVentas tbody").innerHTML = plantilla;
    document.querySelector("#tablaVentas tfoot").innerHTML = plantilla_tfoot;

}

async function eliminarVenta(id) {
    if (confirm('¿Eliminar venta?')) {
        try {
            await fetch('api/ventas/' + id, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });

            location.reload();

        } catch (error) {
            console.error('Error al eliminar venta:', error);
        }
    }

}