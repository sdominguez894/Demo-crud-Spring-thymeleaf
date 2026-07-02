---
type: Playbook
título: Ghost AI Flujo de trabajo de desarrollo
description: Flujo de trabajo guiado por especificaciones para implementar funcionalidades acotadas y verificables contra el paquete de contexto de Ghost AI.
tags: [flujo de trabajo, playbook, ai-agent, implementación, documentation]
timestamp: 2026-07-01T13:40:00Z
context_role: estable-concepto
cache_policy: núcleo-estable
related:
  - /architecture/system.md
  - /engineering/code-standards.md
  - /state/progress-tracker.md
auditoría:
  source_map: /references/source-map.md
  normalization_notes: /references/normalization-notes.md
  update_log: /state/log.md
source_files:
  - ai-workflow-rules.md
---

# Flujo de trabajo de desarrollo

## Tarjeta de contexto

| Campo | Valor |
| --- | --- |
| Rol de contexto | Flujo de trabajo de implementación para agentes. |
| Clase de caché | Concepto estable. |
| Cuándo cargar | Scoping work, splitting funcionalidades, handling missing requisitos, o updating docs. |
| Combinar con | `/state/progress-tracker.md` para actual estado; `/architecture/system.md` para invariantes. |
| Ruta de auditoría | Los cambios en el flujo de trabajo o en la estructura del contexto deben añadirse a `/state/log.md`. |

## Claves de recuperación

- flujo de trabajo guiado por especificaciones
- scoping reglas
- split work
- missing requisitos
- protegidas foundation componentes
- docs en sync


## Enfoque

Construye este proyecto de forma incremental usando un flujo guiado por especificaciones. Los archivos de contexto definen qué construir, cómo construirlo y cuál es el estado actual del progreso. Implementa siempre contra estas especificaciones: no infieras ni inventes comportamiento desde cero.

## Reglas de alcance

- Work on one funcionalidad unit o subsystem at a time.
- preferir small, verifiable increments over gran tamaño speculative changes.
- No combines límites del sistema que no estén relacionados en un único paso de implementación.

## Cuándo dividir el trabajo

Split an implementación step if it combines:

- UI changes y segundo plano tarea changes.
- Estado del canvas en tiempo real y persistencia en base de datos.
- Multiple unrelated API rutas.
- Comportamiento que no esté claramente definido en los archivos de contexto.

Si un cambio no puede verificarse rápidamente de extremo a extremo, el alcance es demasiado amplio: divídelo.

## Gestión de requisitos faltantes

- No inventes comportamiento de producto que no esté definido en los archivos de contexto.
- Si un requisito es ambiguo, resuélvelo en el archivo de contexto correspondiente antes de implementar.
- Si a requisito is missing, add it como an open question en [Rastreador de progreso](/state/progress-tracker.md) before continuing.

## Componentes base protegidos

Do not modify generado third-party foundation componentes unless explicitly instructed.

Esto incluye:

- `components/ui/*` — shadcn/ui componentes.
- Third-party library internals.

Deben mantenerse genéricos y reutilizables.

Los estilos específicos del proyecto, los cambios de layout y la lógica de funcionalidades deben implementarse en componentes de nivel aplicación en lugar de modificar componentes base.

Modifica estos archivos solo cuando una tarea lo requiera explícitamente.

## Mantener la documentación sincronizada

Actualiza el archivo de contexto correspondiente cada vez que cambie la implementación:

- Arquitectura del sistema o límites.
- almacenamiento model decisions.
- código conventions o standards.
- Alcance de la funcionalidad.

El estado de progreso debe reflejar el estado real de la implementación, no el estado deseado.

## Antes de pasar a la siguiente unidad

1. La unidad actual funciona de extremo a extremo dentro de su alcance definido.
2. No invariante definido en [Arquitectura del sistema](/architecture/system.md) was violated.
3. [Rastreador de progreso](/state/progress-tracker.md) refleja el trabajo completado.

## Conceptos relacionados

- [Estándares de código](/engineering/code-standards.md) explica las restricciones de implementación que protege este flujo de trabajo.
- [Arquitectura del sistema](/architecture/system.md) define los invariantes que cada paso de implementación debe preservar.
