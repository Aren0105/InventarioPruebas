const input = document.getElementById("productoVenta");
const list = document.getElementById("sugerenciaProductosVenta");
const formaPago = document.getElementById("metodoPago");
const pagoRecibido = document.getElementById("pago-recibido");
const vuelto = document.getElementById("vuelto");
const inputPrecio = document.getElementById("precioVenta");


input.addEventListener("change", async (e) =>{
  const nombreProducto = e.target.value.trim();
  if(nombreProducto.length>0){
    const res = await fetch(`/ventas/venta/precio?nombreProducto=${encodeURIComponent(nombreProducto)}`);
    if(res.ok){
      const precio = await res.json();
      if(precio>0){
        inputPrecio.value = precio;
      }
    }
  } else{
    inputPrecio.value = "";
  }
})


formaPago.addEventListener("change", (e) =>{
  const  valorSeleccionado = e.target.value;
  if(valorSeleccionado.trim()==="tarjeta" || valorSeleccionado.trim()==="transferencia"){
    pagoRecibido.classList.add("d-none");
    vuelto.classList.add("d-none");
  } else{
    pagoRecibido.classList.remove("d-none");
    vuelto.classList.remove("d-none");
  }
})


let timer;
input.addEventListener("input", () => {
  clearTimeout(timer);
  const q = input.value.trim();
  if (q.length < 2) {
    list.innerHTML = "";
    return;
  }

  timer = setTimeout(async () => {
    const res = await fetch(
      `/ventas/productos/sugerencia?q=${encodeURIComponent(q)}`,
    );
    const data = await res.json();
    list.innerHTML = data.map((n) => `<option value="${n}"></option>`).join("");
  }, 300);
});

