const tabla = document.getElementById('tablaProductos');
const mensaje = document.getElementById('mensaje');
let categorias = [];

function mostrarMensaje(texto,tipo='ok'){mensaje.textContent=texto;mensaje.className=`mensaje ${tipo}`;}
function nombreCategoria(id){const c=categorias.find(x=>x.idCategoria===id);return c?c.nombre:(id ?? '');}
function pintar(datos){
  if(!datos.length){tabla.innerHTML='<tr><td colspan="6" class="vacio">No se encontraron productos</td></tr>';return;}
  tabla.innerHTML=datos.map(p=>`<tr>
    <td>${p.idProducto ?? ''}</td><td>${p.nombre ?? ''}</td><td>${p.descripcion ?? ''}</td>
    <td>Q ${Number(p.precio ?? 0).toFixed(2)}</td><td>${p.stock ?? 0}</td><td>${nombreCategoria(p.idCategoria)}</td>
  </tr>`).join('');
}
async function cargarCategorias(){
  try{
    const r=await fetch('/categorias/activos'); if(!r.ok) throw new Error(); categorias=await r.json();
    const s=document.getElementById('idCategoria');
    s.innerHTML='<option value="">Seleccione...</option>'+categorias.map(c=>`<option value="${c.idCategoria}">${c.nombre}</option>`).join('');
  }catch(e){mostrarMensaje('No se pudieron cargar las categorías.','error');}
}
async function cargarProductos(url='/productos/activos'){
  try{const r=await fetch(url);if(!r.ok)throw new Error('No se pudieron cargar los productos');pintar(await r.json());}
  catch(e){tabla.innerHTML=`<tr><td colspan="6" class="vacio">${e.message}</td></tr>`;}
}
document.getElementById('formProducto').addEventListener('submit',async e=>{
  e.preventDefault();
  const datos={estado:true,nombre:document.getElementById('nombre').value.trim(),descripcion:document.getElementById('descripcion').value.trim(),precio:Number(document.getElementById('precio').value),stock:Number(document.getElementById('stock').value),idCategoria:Number(document.getElementById('idCategoria').value)};
  try{const r=await fetch('/productos',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(datos)});if(!r.ok)throw new Error('No se pudo registrar el producto');e.target.reset();mostrarMensaje('Producto registrado correctamente.');cargarProductos();}
  catch(err){mostrarMensaje(err.message,'error');}
});
document.getElementById('btnBuscar').addEventListener('click',()=>{const f=document.getElementById('filtro').value.trim();cargarProductos(f?`/productos/activos/filtro?filtro=${encodeURIComponent(f)}`:'/productos/activos');});
document.getElementById('btnTodos').addEventListener('click',()=>{document.getElementById('filtro').value='';cargarProductos();});
document.getElementById('filtro').addEventListener('keydown',e=>{if(e.key==='Enter')document.getElementById('btnBuscar').click();});
(async()=>{await cargarCategorias();await cargarProductos();})();
