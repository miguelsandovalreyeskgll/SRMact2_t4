# Guía rápida para explicar SRMact2_t4

## ¿Qué cambió respecto de la Actividad 1?

La Actividad 1 tiene `ProyectoController.java` y `MateriasController.java`, que devuelven texto, una lista JSON y un número. **Estos controladores se conservaron sin modificarlos**.

En la Actividad 2 se agregaron:

1. `MateriaDTO.java`: clase de datos con `id`, `nombre` y `categoria`.
2. `MateriasMvcController.java`: recibe solicitudes web y elige una página HTML de Thymeleaf.
3. `MateriaPostController.java`: recibe un JSON por POST desde Postman o Bruno.
4. `materias.html`, `formulario.html`, `detalle.html`: las páginas que ve el navegador.
5. `estilos.css`: colores y formato visual, sin JavaScript.

## Cada archivo y dónde se utiliza

| Acción | Método Java / anotación | ¿Qué devuelve? |
| --- | --- | --- |
| Abrir `/materias` | `listar()` + `@GetMapping` | `materias.html` con `th:each` |
| Abrir `/materias?categoria=Web` | `listar()` + `@RequestParam` | Lista filtrada por categoría |
| Abrir `/materias/1` | `detalle()` + `@PathVariable` | `detalle.html` con la materia 1 |
| Abrir `/materias/nueva` | `formulario()` + `@GetMapping` | `formulario.html` con objeto DTO vacío |
| Enviar el formulario | `guardar()` + `@ModelAttribute` | La misma vista con los datos capturados |
| Enviar JSON desde Postman | `recibirMateria()` + `@RequestBody` | JSON recibido como respuesta |

## Preguntas probables

**¿Qué es MVC?** Modelo = DTO, Vista = HTML de Thymeleaf, Controlador = `MateriasMvcController`.

**¿Qué hace Model?** Transporta los datos desde el controlador hacia la página HTML.

**¿Qué hace th:each?** Recorre los elementos de la lista para crear las filas de la tabla.

**¿Qué hace @RequestParam?** Lee un valor de la URL, como `?categoria=Web`.

**¿Qué hace @PathVariable?** Lee una parte de la ruta, como el `1` en `/materias/1`.

**¿Qué hace @ModelAttribute?** Llena un DTO con los valores enviados por el formulario HTML.

**¿Qué hace @RequestBody?** Convierte el cuerpo JSON del POST en un DTO Java.

**¿Qué hace @Value?** Lee `proyecto.nombre` desde `application.properties` y lo muestra en las vistas.

**¿Se guardan las materias nuevas?** No. La actividad solo solicita capturar/procesar el formulario y probar un POST; estos endpoints muestran la información recibida sin base de datos.

## Para la exposición

Mantén abiertas dos pestañas: `http://localhost:8083/materias` y `http://localhost:8083/materias/nueva`. Demuestra filtros y detalle. Finalmente muestra el POST con Postman y la respuesta JSON. Puedes indicar que la Actividad 1 continúa en `:8082` y la Actividad 2 utiliza el puerto **8083**, si está disponible.
