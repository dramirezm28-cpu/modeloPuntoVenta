const tabla=document.getElementById('tablaClientes');
const mensaje=document.getElementById('mensaje');
function mostrarMensaje(texto,tipo='ok'){mensaje.textContent=texto;mensaje.className=`mensaje ${tipo}`;}
function fecha(v){if(!v)return '';const d=new Date(v);return Number.isNaN(d.getTime())?v:d.toLocaleString('es-GT');}
function pintar(datos){
  if(!datos.length){tabla.innerHTML='<tr><td colspan="6" class="vacio">No se encontraron clientes</td></tr>';return;}
  tabla.innerHTML=datos.map(c=>`<tr><td>${c.idCliente ?? ''}</td><td>${c.nombre ?? ''}</td><td>${c.apellido ?? ''}</td><td>${c.email ?? ''}</td><td>${c.telefono ?? ''}</td><td>${fecha(c.fechaRegistro)}</td></tr>`).join('');
}
async function cargarClientes(url='/clientes/activos'){
  try{const r=await fetch(url);if(!r.ok)throw new Error('No se pudieron cargar los clientes');pintar(await r.json());}
  catch(e){tabla.innerHTML=`<tr><td colspan="6" class="vacio">${e.message}</td></tr>`;}
}
document.getElementById('formCliente').addEventListener('submit',async e=>{
  e.preventDefault();
  const datos={estado:true,nombre:document.getElementById('nombre').value.trim(),apellido:document.getElementById('apellido').value.trim(),email:document.getElementById('email').value.trim(),telefono:document.getElementById('telefono').value.trim(),fechaRegistro:new Date().toISOString()};
  try{const r=await fetch('/clientes',{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify(datos)});if(!r.ok)throw new Error('No se pudo registrar el cliente');e.target.reset();mostrarMensaje('Cliente registrado correctamente.');cargarClientes();}
  catch(err){mostrarMensaje(err.message,'error');}
});
document.getElementById('btnBuscar').addEventListener('click',()=>{const f=document.getElementById('filtro').value.trim();cargarClientes(f?`/clientes/activos/filtro?filtro=${encodeURIComponent(f)}`:'/clientes/activos');});
document.getElementById('btnTodos').addEventListener('click',()=>{document.getElementById('filtro').value='';cargarClientes();});
document.getElementById('filtro').addEventListener('keydown',e=>{if(e.key==='Enter')document.getElementById('btnBuscar').click();});
cargarClientes();
