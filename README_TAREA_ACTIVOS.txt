TAREA: mostrarActivos, mostrarActivosFiltro y mostrarActivosFiltroTop

Se agregaron los tres metodos a:
- Categoria
- Producto
- Cliente
- Pedido
- PedidoDetalle

Rutas para probar en Postman (GET):

CATEGORIA
http://localhost:8080/categorias/activos
http://localhost:8080/categorias/activos/filtro?filtro=a
http://localhost:8080/categorias/activos/filtro/top?filtro=a

PRODUCTO
http://localhost:8080/productos/activos
http://localhost:8080/productos/activos/filtro?filtro=a
http://localhost:8080/productos/activos/filtro/top?filtro=a

CLIENTE
http://localhost:8080/clientes/activos
http://localhost:8080/clientes/activos/filtro?filtro=a
http://localhost:8080/clientes/activos/filtro/top?filtro=a

PEDIDO
http://localhost:8080/pedidos/activos
http://localhost:8080/pedidos/activos/filtro?filtro=a
http://localhost:8080/pedidos/activos/filtro/top?filtro=a

NOTA PEDIDO: el filtro busca por el nombre del cliente.

PEDIDO DETALLE
http://localhost:8080/pedido-detalles/activos
http://localhost:8080/pedido-detalles/activos/filtro?filtro=a
http://localhost:8080/pedido-detalles/activos/filtro/top?filtro=a

NOTA PEDIDO DETALLE:
La tabla pedido_detalle no tiene campo ESTADO. Por eso un detalle se considera activo
cuando su pedido asociado tiene ESTADO = true. El filtro busca por nombre del producto.

TOP:
mostrarActivosFiltroTop devuelve como maximo 3 registros activos que coincidan con el filtro.

ARCHIVOS MODIFICADOS:
controller/CategoriaController.java
controller/ProductoController.java
controller/ClienteController.java
controller/PedidoController.java
controller/PedidoDetalleController.java
service/CategoriaService.java
service/ProductoService.java
service/ClienteService.java
service/PedidoService.java
service/PedidoDetalleService.java
repository/CategoriaRepository.java
repository/ProductoRepository.java
repository/ClienteRepository.java
repository/PedidoRepository.java
repository/PedidoDetalleRepository.java
