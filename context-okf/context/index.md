---
type: Gestión de contexto índice
título: Ghost AI Gestión de contexto
description: Capa de enrutamiento para caché estratégica, ingesta progresiva, estructura, enlaces absolutos y rastros de auditoría.
tags: [ingeniería de contexto, okf, almacenamiento en caché, ingesta, auditoría]
timestamp: 2026-07-01T13:40:00Z
context_role: ancla-de-enrutamiento
cache_policy: núcleo-estable
related:
  - /context/cache-strategy.md
  - /context/progresiva-ingesta.md
  - /context/audit-paths.md
  - /state/log.md
auditoría:
  source_map: /references/source-map.md
  update_log: /state/log.md
---

# Gestión de contexto

## Propósito

Hacer que el paquete sea eficiente para ingeniería de contexto separando el enrutamiento estable, el estado actual, los conceptos específicos de la tarea, el detalle histórico y la evidencia de auditoría.

## Principios operativos

| Principio | Bundle implementación |
| --- | --- |
| Caché estratégica de contexto | estable enrutamiento y core concepto archivos are separated desde volatile progress y log archivos. |
| progresiva ingesta | raíz y directorio índices point a detallado archivos instead de duplicating detail. |
| Estructura clara | Las tablas, listas de invariantes, bloques de reglas y contratos de carga aparecen antes del detalle narrativo. |
| Enlaces internos absolutos | Los enlaces internos usan rutas desde la raíz del paquete, como `/architecture/system.md`. |
| Rutas de auditoría explícitas | mapa de fuentes, notas de normalización, y `log.md` provide traceability para every migración decision. |

## Archivos de gestión

* [Estrategia de caché](/context/cache-strategy.md) - Qué cachear, qué recargar y qué evitar cargar salvo que se solicite.
* [Guía de ingesta progresiva](/context/progresiva-ingesta.md) - Tarea-specific carga perfiles.
* [Rutas de auditoría](/context/audit-paths.md) - rastreo de afirmaciones, registro de actualizaciones y reglas de preservación de fuentes.

## Secuencia inicial predeterminada del agente

1. Cargar `/index.md`.
2. Cargar `/context/index.md`.
3. Cargar `/state/progress-tracker.md`.
4. Select one tarea perfil desde `/context/progressive-ingestion.md`.
5. Carga solo los archivos conceptuales indicados por ese perfil.
6. Cargar `/state/feature-history.md` o `/references/*` solo para evidencia, depuración, migración review, o auditoría tareas.
