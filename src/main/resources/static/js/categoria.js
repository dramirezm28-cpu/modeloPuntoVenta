const tabla = document.getElementById('tablaCategorias');
const mensaje = document.getElementById('mensaje');

function mostrarMensaje(texto, tipo='ok'){
  mensaje.textContent = texto;
  mensaje.className = `mensaje ${tipo}`;
}

function pintar(datos){
  if(!datos.length){tabla.innerHTML='<tr><td colspan="4" class="vacio">No se encontraron categorías</td></tr>';return;}
  tabla.innerHTML = datos.map(c => `<tr>
    <td>${c.idCategoria ?? ''}</td>
    <td>${c.nombre ?? ''}</td>
    <td>${c.descripcion ?? ''}</td>
    <td><span class="badge">${c.estado ? 'Activo' : 'Inactivo'}</span></td>
  </tr>`).join('');
}

async function cargarCategorias(url='/categorias/activos'){
  try{
    const r=await fetch(url);
    if(!r.ok) throw new Error('No se pudieron cargar las categorías');
    pintar(await r.json());
  }catch(e){tabla.innerHTML=`<tr><td colspan="4" class="vacio">${e.message}</td></tr>`;}
}

document.getElementById('formCategoria').addEventListener('submit', async e => {
  e.preventDefault();
  const datos={estado:true,nombre:document.getElementById('nombre').value.trim(),descripcion:document.getElementById('descripcion').value.trim()};
  try{
    const r=await fetch('/categorias',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(datos)});
    if(!r.ok) throw new Error('No se pudo registrar la categoría');
    e.target.reset(); mostrarMensaje('Categoría registrada correctamente.'); cargarCategorias();
  }catch(err){mostrarMensaje(err.message,'error');}
});

document.getElementById('btnBuscar').addEventListener('click',()=>{
  const f=document.getElementById('filtro').value.trim();
  cargarCategorias(f?`/categorias/activos/filtro?filtro=${encodeURIComponent(f)}`:'/categorias/activos');
});
document.getElementById('btnTodos').addEventListener('click',()=>{document.getElementById('filtro').value='';cargarCategorias();});
document.getElementById('filtro').addEventListener('keydown',e=>{if(e.key==='Enter')document.getElementById('btnBuscar').click();});

cargarCategorias();
