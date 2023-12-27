async function registrarProducto(event) {

    event.preventDefault();

    let datos = {};

    datos.nombre = document.getElementById("txtNombre").value;
    datos.descripcion = document.getElementById("txtDescripcion").value;
    datos.precio = parseFloat(document.getElementById("txtPrecio").value);
    datos.costo_producto = parseFloat(document.getElementById("txtCosto").value);
    datos.existencia = parseInt(document.getElementById("txtExistencia").value, 10);

    try {
        const request = await fetch('api/productos', {
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

        alert("El producto se registró con éxito");
        window.location.href = 'productos.html';
    } catch (error) {
        console.error('Error al registrar el producto:', error);
        alert('Hubo un error al registrar el producto. Por favor, inténtalo de nuevo.');
    }
}

//Obtener datos a editar
const urlParams = new URLSearchParams(window.location.search);
document.addEventListener("DOMContentLoaded", async function () {

    const id = urlParams.get('id');

    if (id) {
        try {
            // Realizar una solicitud para obtener los datos del producto por su ID
            const response = await fetch('api/productos/' + id);

            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const producto = await response.json();

            // Llenar el formulario con los datos del producto
            document.getElementById("txtNombre").value = producto.nombre;
            document.getElementById("txtDescripcion").value = producto.descripcion;
            document.getElementById("txtPrecio").value = producto.precio;
            document.getElementById("txtCosto").value = producto.costo_producto;
            document.getElementById("txtExistencia").value = producto.existencia;

            // Ocultar el botón "Registrar" y mostrar el botón "Actualizar"
            document.getElementById("btnRegistrar").style.display = "none";
            document.getElementById("btnActualizar").style.display = "block";
        } catch (error) {
            console.error('Error al obtener el producto por ID:', error);
            alert('Hubo un error al obtener el producto. Por favor, inténtalo de nuevo.');
        }
    }
});

async function actualizarProducto() {
    const id = urlParams.get('id');

    let datos = {};
    datos.nombre = document.getElementById("txtNombre").value;
    datos.descripcion = document.getElementById("txtDescripcion").value;
    datos.precio = parseFloat(document.getElementById("txtPrecio").value);
    datos.costo_producto = parseFloat(document.getElementById("txtCosto").value);
    datos.existencia = parseInt(document.getElementById("txtExistencia").value, 10);

    try {
        const request = await fetch('api/productos/' + id, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        });

        if (!request.ok) {
            throw new Error(`HTTP error! Status: ${request.status}`);
        }

        alert("El producto se actualizó con éxito");
        window.location.href = 'productos.html';
    } catch (error) {
        console.error('Error al actualizar el producto:', error);
        alert('Hubo un error al actualizar el producto. Por favor, inténtalo de nuevo.');
    }
}


