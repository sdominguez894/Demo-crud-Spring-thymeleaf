---
type: contexto ingesta Guide
título: Ghost AI Guía de ingesta progresiva
description: Perfiles de carga basados en tareas para minimizar el contexto sin perder corrección.
tags: [ingeniería de contexto, progresiva-ingesta, retrieval]
timestamp: 2026-07-01T13:40:00Z
context_role: ingesta-policy
cache_policy: núcleo-estable
related:
  - /context/cache-strategy.md
  - /architecture/system.md
  - /engineering/code-standards.md
  - /design/ui-context.md
  - /state/progress-tracker.md
auditoría:
  update_log: /state/log.md
---

# Guía de ingesta progresiva

## Mínimo universal

Carga estos archivos primero para nearly every tarea:

| Orden | Archivo | Propósito |
| --- | --- | --- |
| 1 | `/index.md` | enrutamiento del paquete y tarea perfil selection. |
| 2 | `/context/index.md` | contexto carga reglas. |
| 3 | `/state/progress-tracker.md` | Estado actual de implementación. |

## Perfiles de tarea

| Tarea | Añadir estos archivos | Evitar salvo que sea necesario |
| --- | --- | --- |
| Planificación de producto | `/product/overview.md` | `/state/feature-history.md` |
| Planificación de arquitectura | `/architecture/system.md`, `/engineering/code-standards.md` | `/design/ui-context.md` unless UI affected |
| Implementación de API o backend | `/architecture/system.md`, `/engineering/code-standards.md`, `/engineering/development-workflow.md` | `/design/ui-context.md`, `/state/feature-history.md` |
| Implementación de UI | `/design/ui-context.md`, `/engineering/code-standards.md`, `/product/overview.md` | `/state/feature-history.md` |
| Implementación de flujo de IA | `/architecture/system.md`, `/engineering/development-workflow.md`, `/engineering/code-standards.md` | `/design/ui-context.md` unless sidebar/canvas UI affected |
| Trabajo de almacenamiento o persistencia | `/architecture/system.md`, `/engineering/code-standards.md`, `/state/progress-tracker.md` | histórico funcionalidades unless esquema procedencia is needed |
| Depuración de un subsistema completado | Relevant concepto archivo plus `/state/feature-history.md` | Unrelated concepto directorios |
| Revisión de migración o procedencia | `/references/source-map.md`, `/references/normalization-notes.md`, `/state/log.md` | Full concepto archivos unless claims need comprobación |

## Reglas de escalado

- Si un requisito es ambiguo, carga el archivo conceptual más cercano antes de inventar comportamiento.
- Si estado actual entra en conflicto con a concepto estable, preferir `/state/progress-tracker.md` y check `/state/log.md` o `/references/normalization-notes.md`.
- Si hace falta evidencia de implementación, carga `/state/feature-history.md` después del concepto estable correspondiente.
- Si a change crosses límites del sistema, cargar `/engineering/development-workflow.md` before implementación.
