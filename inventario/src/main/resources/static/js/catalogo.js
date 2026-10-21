const input = document.getElementById("buscarProducto");
const list = document.getElementById("sugerenciaProductos");
let timer;

const CART_KEY = "premiumPartsCart";
const WA_PHONE = "5180056245";

// Revisa si ya hay un carrito guardado en el navegador y lo carga, si no, crea uno vacío
function getCart() {
  return JSON.parse(localStorage.getItem(CART_KEY)) || [];
}

// Toma el arreglo actualizado del carrito, lo transforma en texto plano y lo guarda en el navegador
function saveCart(cart) {
  localStorage.setItem(CART_KEY, JSON.stringify(cart));
  updateCartBadge();
}

function findCartItem(cart, id) {
  return cart.find((item) => item.id == id);
}

// 1. Hace el llamado a getCart para obtener el estado actual del carrito
// 2. Mediante findCartItem revisa si el producto ya existe en el carrito, si ya existe, incrementa la cantidad, si no, lo agrega con cantidad 1
// 3. Guarda los cambios con saveCart
function addToCart(product) {
  const cart = getCart();
  const existente = findCartItem(cart, product.id);
  if (existente) {
    existente.cantidad++;
  } else {
    cart.push({
      id: product.id,
      nombre: product.nombre,
      precio: product.precio,
      cantidad: 1,
    });
  }
  saveCart(cart);
}

// Elimina un producto del carrito mediante su id, si la cantidad es mayor a 1, solo decrementa la cantidad, si es 0, lo elimina completamente del carrito
function removeFromCart(id) {
  let cart = getCart();
  const existente = findCartItem(cart, id);
  if (existente) {
    existente.cantidad--;
    if (existente.cantidad <= 0) {
      cart = cart.filter((item) => item.id != id);
    }
  }
  saveCart(cart);
}

function removeItemCompletely(id) {
  let cart = getCart();
  cart = cart.filter((item) => item.id != id);
  saveCart(cart);
}

function getProductQuantity(id) {
  const cart = getCart();
  const item = findCartItem(cart, id);
  return item ? item.cantidad : 0;
}

function syncCatalogCounters() {
  document.querySelectorAll(".contador-producto").forEach((contador) => {
    const btnSumar = contador.querySelector(".btn-sumar");
    const cantidad = contador.querySelector(".cantidad-producto");
    cantidad.textContent = getProductQuantity(btnSumar.dataset.id);
  });
}

function updateCartBadge() {
  const cart = getCart();
  const total = cart.reduce((sum, item) => sum + item.cantidad, 0);
  const cartBtn = document.querySelector(".btn.p-2.ms-2.fs-4");
  if (!cartBtn) return;
  let badge = cartBtn.querySelector(".cart-badge");
  if (total > 0) {
    if (!badge) {
      badge = document.createElement("span");
      badge.className = "cart-badge";
      Object.assign(badge.style, {
        position: "absolute",
        top: "-4px",
        right: "-8px",
        background: "#dc3545",
        color: "#fff",
        borderRadius: "50%",
        width: "20px",
        height: "20px",
        fontSize: "11px",
        fontWeight: "700",
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
        lineHeight: "1",
      });
      cartBtn.style.position = "relative";
      cartBtn.appendChild(badge);
    }
    badge.textContent = total > 99 ? "99+" : total;
  } else {
    badge?.remove();
  }
}

function buildWhatsAppMessage() {
  const cart = getCart();
  if (cart.length === 0) return "";
  const lines = cart.map(
    (item) =>
      `• ${item.nombre} x${item.cantidad} - S/ ${(item.precio * item.cantidad).toFixed(2)}`,
  );
  const total = cart.reduce(
    (sum, item) => sum + item.precio * item.cantidad,
    0,
  );
  lines.push("", `*Total: S/ ${total.toFixed(2)}*`);
  return lines.join("\n");
}

function renderCartModal() {
  const container = document.getElementById("cartItemsContainer");
  const cart = getCart();

  if (cart.length === 0) {
    container.innerHTML = `<div class="text-center py-5 text-muted">
      <i class="bi bi-cart" style="font-size:3rem"></i>
      <p class="mt-2 mb-0">Tu carrito est\u00e1 vac\u00edo</p>
    </div>`;
    document.getElementById("cartTotal").textContent = "S/ 0.00";
    document.getElementById("btnWhatsApp").disabled = true;
    return;
  }

  let html = `<div class="table-responsive">
    <table class="table align-middle mb-0">
      <thead class="table-light">
        <tr>
          <th>Producto</th>
          <th class="text-center">Cantidad</th>
          <th class="text-end">Precio</th>
          <th class="text-end">Subtotal</th>
          <th></th>
        </tr>
      </thead>
      <tbody>`;

  let total = 0;
  cart.forEach((item) => {
    const subtotal = item.precio * item.cantidad;
    total += subtotal;
    html += `<tr>
      <td>${item.nombre}</td>
      <td class="text-center">${item.cantidad}</td>
      <td class="text-end">S/ ${item.precio.toFixed(2)}</td>
      <td class="text-end">S/ ${subtotal.toFixed(2)}</td>
      <td class="text-end">
        <button class="btn btn-sm btn-outline-danger border-0 remove-item" data-id="${item.id}">
          <i class="bi bi-trash"></i>
        </button>
      </td>
    </tr>`;
  });

  html += `</tbody></table></div>`;
  container.innerHTML = html;
  document.getElementById("cartTotal").textContent = `S/ ${total.toFixed(2)}`;
  document.getElementById("btnWhatsApp").disabled = false;

  container.querySelectorAll(".remove-item").forEach((btn) => {
    btn.addEventListener("click", () => {
      removeItemCompletely(btn.dataset.id);
      syncCatalogCounters();
      renderCartModal();
    });
  });
}

document.querySelector(".btn.p-2.ms-2.fs-4").addEventListener("click", () => {
  renderCartModal();
  const modal = new bootstrap.Modal(document.getElementById("modalCarrito"));
  modal.show();
});

document.getElementById("btnWhatsApp").addEventListener("click", () => {
  const msg = buildWhatsAppMessage();
  if (!msg) return;
  window.open(
    `https://wa.me/${WA_PHONE}?text=${encodeURIComponent(msg)}`,
    "_blank",
  );
});

document.querySelectorAll(".contador-producto").forEach((contador) => {
  const btnSumar = contador.querySelector(".btn-sumar");
  const btnRestar = contador.querySelector(".btn-restar");
  const cantidad = contador.querySelector(".cantidad-producto");
  const id = btnSumar.dataset.id;
  const nombre = btnSumar.dataset.nombre;
  const precio = parseFloat(btnSumar.dataset.precio);

  if (id) {
    cantidad.textContent = getProductQuantity(id);
  }

  btnSumar.addEventListener("click", () => {
    if (!id) return;
    addToCart({ id, nombre, precio });
    cantidad.textContent = getProductQuantity(id);
  });

  btnRestar.addEventListener("click", () => {
    if (!id) return;
    removeFromCart(id);
    cantidad.textContent = getProductQuantity(id);
  });
});

updateCartBadge();

input.addEventListener("input", () => {
  clearTimeout(timer);
  const q = input.value.trim();
  if (q.length < 2) {
    list.innerHTML = "";
    return;
  }

  timer = setTimeout(async () => {
    const res = await fetch(`/catalogo/sugerencia?q=${encodeURIComponent(q)}`);
    const data = await res.json();
    list.innerHTML = data.map((n) => `<option value="${n}"></option>`).join("");
  }, 300);
});
