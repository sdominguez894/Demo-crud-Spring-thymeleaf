# Context OKF

Esta carpeta contiene el contexto estructurado del proyecto.

La idea es tener un lugar común donde cualquier persona del equipo, o cualquier agente de IA, pueda entender cómo está pensado el producto, cómo está montado técnicamente, qué convenciones seguimos y en qué estado se encuentra el trabajo.

No pretende sustituir al código ni a la documentación larga del proyecto. Sirve como una capa de contexto práctica, fácil de leer y fácil de mantener.

## Por dónde empezar

Empieza leyendo estos ficheros:

1. `/context-okf/index.md`
2. `/context-okf/WORKFLOW.md`

Después, abre solo la sección que tenga sentido para la tarea que vayas a hacer.

Por ejemplo, si vas a tocar arquitectura, no hace falta leer diseño. Si vas a trabajar en una pantalla nueva, probablemente sí quieras revisar producto, diseño e ingeniería.

## Secciones principales

- `/context-okf/product` — visión del producto, usuarios, casos de uso y alcance funcional.
- `/context-okf/architecture` — arquitectura del sistema, módulos, servicios y decisiones técnicas.
- `/context-okf/engineering` — forma de trabajar, estándares de código, pruebas y flujo de desarrollo.
- `/context-okf/design` — contexto de interfaz, experiencia de usuario, componentes y criterios visuales.
- `/context-okf/state` — estado actual del proyecto, progreso, decisiones recientes e histórico de funcionalidades.
- `/context-okf/references` — fuentes, trazabilidad, notas de normalización y material de apoyo.

## Cómo usar este contexto

Antes de empezar una tarea, revisa el contexto mínimo necesario. No cargues ni leas todo por defecto.

El flujo recomendado es:

1. Leer `/context-okf/index.md`.
2. Ir al índice de la sección relacionada con la tarea.
3. Abrir solo los ficheros específicos que ayuden a tomar buenas decisiones.
4. Actualizar el contexto si la tarea cambia algo importante del producto, la arquitectura, el diseño, la forma de trabajar o el estado del proyecto.

## Para agentes de IA

Antes de modificar código, un agente debería leer `/context-okf/index.md` y después cargar únicamente los documentos relevantes para la tarea.

Cuando una implementación cambie conocimiento importante del proyecto, el agente debería proponer qué ficheros de `/context-okf` conviene actualizar.

Los agentes no deberían modificar el contexto por su cuenta salvo que la tarea lo pida de forma explícita.

## Mantenimiento

Este contexto solo es útil si se mantiene al día.

Cuando el código y el contexto se contradigan, trátalo como una señal de deuda documental. En una pull request importante, si cambia el comportamiento, la arquitectura, el diseño o el flujo de trabajo, debería revisarse también si hay que actualizar `/context-okf`.
