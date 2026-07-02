---
type: Reference
título: Mapa de fuentes de migración
description: Mapeo desde los archivos de contexto originales de Ghost AI al paquete de conceptos con enfoque OKF.
tags: [migración, source-map, okf]
timestamp: 2026-07-01T13:40:00Z
context_role: auditoría-reference
cache_policy: carga-diferida
related:
  - /references/normalization-notes.md
source_files:
  - índice.md
  - project-overview.md
  - architecture-context.md
  - código-standards.md
  - ai-workflow-rules.md
  - ui-context.md
  - progress-tracker.md
---

# Mapa de fuentes de migración

| Archivo original | Destino OKF | Tratamiento |
| --- | --- | --- |
| `index.md` | `/index.md` | Converted into an OKF raíz índice con `okf_version`, progresiva-disclosure sections, y links a every major concepto area. |
| `project-overview.md` | `/product/overview.md` | Se conservó el resumen de producto, objetivos, flujo de usuario, funcionalidades, alcance y criterios de éxito. La terminología de almacenamiento se normalizó a Vercel Blob. |
| `architecture-context.md` | `/architecture/system.md` | Preserved stack, límites, almacenamiento model, autenticación model, starter template model, AI generación model, y invariantes. almacenamiento field names were normalized. |
| `code-standards.md` | `/engineering/code-standards.md` | Preserved all reglas de implementación y upgraded local filename references a bundle-relative OKF links. |
| `ai-workflow-rules.md` | `/engineering/development-workflow.md` | Se conservó el flujo de trabajo de desarrollo guiado por especificaciones y se actualizaron las referencias a enlaces OKF relativos al paquete. |
| `ui-context.md` | `/design/ui-context.md` | Preserved theme, tokens, tipografía, radius scale, aspecto del canvas, reglas de la librería de componentes, patrones de layout, y icon reglas. |
| `progress-tracker.md` | `/state/progress-tracker.md` y `/state/feature-history.md` | Se separó el estado actual del historial de funcionalidades completadas, conservando decisiones de arquitectura y notas de sesión. |

## Política de preservación

- Se conservaron los hechos originales salvo cuando un registro de implementación posterior del mismo paquete los contradecía.
- Semantic corrections are recorded en [Notas de normalización](/references/normalization-notes.md).
- Los detalles de funcionalidades completadas no se resumieron hasta perder información; se conservan en [Historial de funcionalidades](/state/feature-history.md).


## Archivos de gestión de contexto

| Archivo OKF | Base de origen | Propósito |
| --- | --- | --- |
| `/README.md` | Derivado de la revisión de experiencia de desarrollo y de la estructura de enrutamiento existente. | Guía cercana para personas que revisan, editan y mantienen el paquete OKF sin exigir que entiendan primero la terminología de ingeniería de contexto. |
| `/context/index.md` | Derivado de los objetivos de optimización del usuario y de la estructura del paquete orientado a OKF. | Enruta caché estratégica, ingesta progresiva, estructura de layout, enlaces absolutos y comportamiento de auditoría. |
| `/context/cache-strategy.md` | Derivado del objetivo del usuario de aplicar caché estratégica al contexto. | Define clases de caché y reglas de invalidación. |
| `/context/progressive-ingestion.md` | Derivado del objetivo del usuario de aplicar ingesta progresiva. | Define perfiles de carga específicos por tarea para evitar sobrecargar la ventana de contexto. |
| `/context/audit-paths.md` | Derivado del objetivo del usuario de hacer explícitas las rutas de auditoría, junto con `log.md`, el mapa de fuentes y las notas de normalización existentes. | Define el comportamiento de rastreo de procedencia y actualizaciones. |
