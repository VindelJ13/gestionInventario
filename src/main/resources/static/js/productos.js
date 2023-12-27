$(document).ready(() => {
    cargarProductos();
});
async function cargarProductos() {

    const request = await fetch('api/productos', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        }
    });
    const productos = await request.json();

    let plantilla = '';
    for (const producto of productos) {
        let btnEditar = '<button class="btnEditar" onclick="editarProducto(' + producto.id_producto + ')">Editar</button>';
        let btnEliminar = '<button type="button" class="btnEliminar" onclick = eliminarProducto(' + producto.id_producto + ')>Eliminar</button>';
        let btnVentas = '<button class="btnRegistrar2" onclick = "ventaProducto(' + producto.id_producto + ')" >Ir a ventas</button>';

        plantilla += `
        <tr>
            <td>${producto.id_producto}</td>
            <td>${producto.nombre}</td>
            <td>${producto.descripcion}</td>
            <td>${producto.precio}</td>
            <td>${producto.costo_producto}</td>
            <td>${producto.existencia}</td>
            <td>
                ${btnEditar}
                ${btnEliminar}
                ${btnVentas}
            </td>
        </tr>
`;
    }

    document.querySelector("#tablaProductos tbody").innerHTML = plantilla;
}

async function eliminarProducto(id) {
    if (confirm('¿Eliminar producto?')) {
        try {
            const response = await fetch('api/productos/' + id, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                // Si la respuesta del servidor indica un error
                const errorMessage = await response.text();
                throw new Error(`HTTP Error: ${response.status} - ${errorMessage}`);
            }

            location.reload();
        } catch (error) {
            alert('Error al eliminar producto, verifique que no tenga ventas asociadas');
        }
    }
}

function editarProducto(id) {
    window.location.href = 'registrarProducto.html?id=' + id;
}

function ventaProducto(id) {
    window.location.href = 'ventas.html?id=' + id;
}

