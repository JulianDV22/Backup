const formulario = document.getElementById('formulario');

formulario.addEventListener('submit', (event) => {
  event.preventDefault();

  const primerNombre = document.getElementById('primer-nombre').value;
  const segundoNombre = document.getElementById('segundo-nombre').value;
  const primerApellido = document.getElementById('primer-apellido').value;
  const segundoApellido = document.getElementById('segundo-apellido').value;
  const numeroTelefonico = document.getElementById('numero-telefonico').value;
  const correo = document.getElementById('correo').value;
  const pais = document.getElementById('pais').value;
  const confirmacionCorreo = document.getElementById('confirmacion-correo').value;
  const tipoDocumento = document.getElementById('tipo-documento').value;
  const numeroDocumento = document.getElementById('numero-documento').value;
  const estadoEstudiante = document.getElementById('estado-estudiante').value;
  const fechaNacimiento = document.getElementById('nacimiento').value;
  const contraseña = document.getElementById('contraseña').value;

  const estudiante = {
    primerNombre: primerNombre,
    segundoNombre: segundoNombre,
    primerApellido: primerApellido,
    segundoApellido: segundoApellido,
    numeroTelefonico: numeroTelefonico,
    correo: correo,
    pais: pais,
    confirmacionCorreo: confirmacionCorreo,
    tipoDocumento: tipoDocumento,
    numeroDocumento: numeroDocumento,
    estadoEstudiante: estadoEstudiante,
    fechaNacimiento: fechaNacimiento,
    contraseña: contraseña
  };

  // solicitud POST al backend
  fetch('/estudiante', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(estudiante),
  })
  .then(response => response.json())
  .then(data => {
    // Procesar la respuesta del backend
    console.log('Estudiante guardado:', data);
    alert('Estudiante guardado correctamente');

    // Obtener los datos
    return fetch('/paises');
  })
  .then(response => response.json())
  .then(data => {

    console.log('Datos de los países:', data);

    const selectPais = document.getElementById('pais');
    data.forEach(pais => {
      const option = document.createElement('option');
      option.value = pais.id;
      option.textContent = pais.nombre;
      selectPais.appendChild(option);
    });
  })
  .catch(error => {
    console.error('Error al guardar el estudiante o al obtener los datos de los países:', error);
    alert('Ocurrió un error al guardar el estudiante o al obtener los datos de los países');
  });
});
