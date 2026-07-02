# Registro de actualizaciones del paquete OKF de Ghost AI

## 2026-07-01

* **Migración**: Se reorganizaron los archivos de contexto originales de Ghost AI en un paquete orientado a OKF, con índices raíz y de directorio, frontmatter conceptual, enlaces relativos al paquete y separación entre estado e historial.
* **Normalización**: Se normalizó la terminología de almacenamiento para que los snapshots del canvas y las especificaciones generadas hagan referencia de forma consistente a Vercel Blob, con enlaces de metadatos en PostgreSQL.
* **Normalización**: Se actualizó la fase actual a Funcionalidad 29 completada porque el registro de funcionalidades completadas incluye la Funcionalidad 29 y no hay trabajo marcado como en curso.
* **Preservación**: Se separó el registro de funcionalidades completadas en [Historial de funcionalidades](/state/feature-history.md) sin perder detalles de las funcionalidades.
* **Optimización de contexto**: Se añadieron `/context/index.md`, `/context/cache-strategy.md`, `/context/progressive-ingestion.md` y `/context/audit-paths.md` para explicitar la caché estratégica, la ingesta progresiva, la estructura de layout, los enlaces absolutos y el comportamiento de auditoría.
* **Optimización de contexto**: Se añadieron tarjetas de contexto y claves de recuperación a archivos conceptuales de alto valor para que los agentes puedan decidir si cargar, cachear u omitir cada archivo antes de leerlo completo.
* **Normalización de enlaces**: Converted interno links a bundle-relative absolute paths beginning con `/`.
* **Preservación de auditoría**: Se actualizaron el mapa de fuentes y las notas de normalización para documentar la optimización de ingeniería de contexto sin descartar información migrada de las fuentes.
* **Pasada de experiencia de desarrollo**: Se añadió `/README.md` como punto de entrada pensado para personas y se agregó una sección “Desarrolladores: empezar aquí” a `/index.md`, manteniendo el contrato de carga para agentes.
* **Limpieza semántica**: Se afinó la redacción sobre plantillas iniciales en `/product/overview.md` para distinguir las plantillas ya implementadas de las posibles categorías futuras.
* **Reglas de mantenimiento**: Se añadieron reglas de mantenimiento de contexto a `/state/progress-tracker.md` para que los desarrolladores sepan cuándo actualizar el estado actual, el historial de funcionalidades, los conceptos de dominio y los archivos de auditoría.

