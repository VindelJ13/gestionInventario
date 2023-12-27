async function registrarVenta(event) {

    event.preventDefault();

    let datos = {};

    datos.id_producto = {
        id_producto: parseInt(document.getElementById("txtIDProducto").value, 10),
    };
    datos.cantidad = parseInt(document.getElementById("txtCantidad").value, 10);
    // Formatear la fecha como una cadena en formato ISO (YYYY-MM-DD)
    datos.fecha_venta = new Date(document.getElementById("txtFecha").value).toISOString().split('T')[0];

    try {
        const request = await fetch('api/ventas', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        if (!request.ok) {
            throw new Error(`HTTP error! Status: ${request.status}`);
        }

        alert("La venta se registró con éxito");
        window.location.href = 'productos.html';
    } catch (error) {
        console.error('Error al registrar la venta:', error);
        alert('Hubo un error al registrar la venta. Por favor, inténtalo de nuevo.');
    }
}