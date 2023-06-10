document.getElementById('eventoForm').addEventListener('submit', function(event) {
  event.preventDefault(); // Evita el envío del formulario

  // Recopila los datos del formulario
  var titulo = document.getElementById('titulo-evento').value;
  var descripcion = document.getElementById('descripcion-evento').value;
  var duracionTodoElDia = document.getElementById('duracion-checkbox').checked;
  var fecha = document.getElementById('fecha').value;
  var horaInicial = document.getElementById('horaInicial').value;
  var horaFinal = document.getElementById('hora').value;
  var tipoEvento = document.getElementById('evento-list').value;
  var frecuencia = document.getElementById('evento-list').value;
  var notificacion = document.getElementById('evento-list').value;

  // Muestra un mensaje de éxito
  alert('Se ha creado correctamente el evento');

  // Muestra los datos en la consola
  console.log('Título: ' + titulo);
  console.log('Descripción: ' + descripcion);
  console.log('Duración todo el día: ' + duracionTodoElDia);
  console.log('Fecha: ' + fecha);
  console.log('Hora Inicial: ' + horaInicial);
  console.log('Hora Final: ' + horaFinal);
  console.log('Tipo de Evento: ' + tipoEvento);
  console.log('Frecuencia: ' + frecuencia);
  console.log('Notificación: ' + notificacion);
});