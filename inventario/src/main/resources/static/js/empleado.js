function cargarDatosModal(button) {
  var id = button.getAttribute('data-id');
  var nombre = button.getAttribute('data-nombre');
  var apellido = button.getAttribute('data-apellido');
  var email = button.getAttribute('data-email');
  var password = button.getAttribute('data-password');
  var rol = button.getAttribute('data-rol');

  document.getElementById('editarId').value = id;
  document.getElementById('editarNombre').value = nombre;
  document.getElementById('editarApellido').value = apellido;
  document.getElementById('editarEmail').value = email;
  document.getElementById('editarPassword').value = password;
  document.getElementById('editarRol').value = rol;
}
