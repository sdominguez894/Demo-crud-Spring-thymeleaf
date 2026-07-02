# Flujo de trabajo con Context OKF

Este proyecto usa `/context-okf` como carpeta de contexto compartido.

Su objetivo es que el equipo y los agentes de IA puedan entender el proyecto sin tener que reconstruir todo el conocimiento desde cero cada vez.

El contexto debe ser útil, concreto y fácil de mantener. No hace falta documentarlo todo. Sí hace falta documentar aquello que ayude a tomar mejores decisiones durante el desarrollo.

## Cuándo consultar el contexto

Consulta `/context-okf` antes de:

- empezar una funcionalidad nueva;
- hacer cambios de arquitectura;
- refactorizar una parte importante del sistema;
- tocar flujos de usuario o pantallas relevantes;
- cambiar convenciones de código, pruebas o despliegue;
- pedir a un agente de IA que trabaje sobre el proyecto;
- revisar el estado del proyecto o decidir próximos pasos.

No es necesario leer todo el contexto cada vez. La idea es leer de forma progresiva.

## Lectura progresiva

El orden recomendado es:

1. Leer `/context-okf/index.md`.
2. Abrir el índice de la sección relacionada con la tarea.
3. Leer sólo los documentos concretos que aporten contexto útil.
4. Volver al código o a la tarea con una idea clara de las restricciones y decisiones existentes.

Ejemplos:

- Para una funcionalidad nueva: revisar `product`, `architecture`, `engineering` y `state`.
- Para una pantalla o flujo visual: revisar `product`, `design` y, si aplica, `engineering`.
- Para una refactorización: revisar `architecture`, `engineering` y `state`.
- Para una decisión técnica: revisar `architecture`, `engineering`, `references` y `state/log.md`.

## Cuándo actualizar el contexto

Actualiza `/context-okf` cuando cambie conocimiento importante del proyecto.

Algunos ejemplos:

- Cambia el alcance del producto → actualizar `/context-okf/product/overview.md`.
- Cambia la arquitectura → actualizar `/context-okf/architecture/system.md`.
- Cambian las convenciones de código → actualizar `/context-okf/engineering/code-standards.md`.
- Cambia el flujo de desarrollo → actualizar `/context-okf/engineering/development-workflow.md`.
- Cambian criterios de interfaz o experiencia → actualizar `/context-okf/design/ui-context.md`.
- Cambia el estado del proyecto → actualizar `/context-okf/state/progress-tracker.md`.
- Se toma una decisión importante → registrarla en `/context-okf/state/log.md`.
- Se completa una funcionalidad relevante → actualizar `/context-okf/state/feature-history.md`.

## Reglas para escribir contexto

Cada fichero debe tener una responsabilidad clara.

Es mejor escribir secciones cortas y directas que textos largos difíciles de mantener.

Evita duplicar la misma información en varios sitios. Si algo ya está explicado en otro documento, enlázalo.

Cuando una información antigua deje de ser cierta, elimínala o muévela al histórico si tiene valor conservarla.

El contexto debe explicar decisiones, restricciones y criterios. No debería convertirse en una copia del código.

## Estado del proyecto

La carpeta `/context-okf/state` sirve para separar el estado actual del histórico.

Usa:

- `/context-okf/state/progress-tracker.md` para el estado actual, próximos pasos y bloqueos.
- `/context-okf/state/log.md` para decisiones, eventos relevantes y cambios importantes.
- `/context-okf/state/feature-history.md` para funcionalidades ya completadas.

Evita mezclar planes futuros, trabajo en curso e histórico en el mismo sitio.

## Uso con agentes de IA

Cuando trabajes con un agente de IA, pídele que siga este flujo:

1. Leer `/context-okf/index.md`.
2. Identificar qué secciones necesita para la tarea.
3. Leer solo los ficheros relevantes.
4. Explicar brevemente qué contexto ha tenido en cuenta.
5. Hacer el cambio solicitado.
6. Proponer actualizaciones de contexto si el cambio afecta al producto, la arquitectura, el diseño, las convenciones o el estado del proyecto.

Un buen prompt inicial podría ser:

```text
Antes de modificar código, lee `/context-okf/index.md` y después solo los documentos de contexto necesarios para esta tarea. Indica qué ficheros has usado y avísame si el cambio requiere actualizar `/context-okf`.
```

## Revisión en pull requests

Los cambios de contexto deberían revisarse igual que los cambios de código.

Una pull request importante debería responder a esta pregunta:

> ¿Este cambio deja obsoleto algún documento de `/context-okf`?

Si la respuesta es sí, la pull request debería incluir la actualización correspondiente o dejar una nota clara indicando qué falta por actualizar.

## Ritmo de mantenimiento

No hace falta hacer grandes sesiones de documentación. Es mejor mantener el contexto poco a poco.

Recomendación práctica:

- actualizar contexto durante el trabajo normal cuando cambie algo relevante;
- revisar `/context-okf/state/progress-tracker.md` al inicio o cierre de cada sprint;
- revisar producto, arquitectura e ingeniería después de cambios grandes;
- eliminar información obsoleta en cuanto empiece a confundir.

El objetivo no es tener documentación perfecta. El objetivo es que el contexto ayude al equipo a moverse con más seguridad.
