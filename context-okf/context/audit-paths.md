---
type: auditoría Path Guide
título: Ghost AI Rutas de auditoría
description: Reglas de trazabilidad para preservar fuentes, normalizar semántica y registrar actualizaciones cronológicamente.
tags: [auditoría, procedencia, source-map, okf]
timestamp: 2026-07-01T13:40:00Z
context_role: auditoría-policy
cache_policy: carga-diferida
related:
  - /references/source-map.md
  - /references/normalization-notes.md
  - /state/log.md
auditoría:
  source_map: /references/source-map.md
  normalization_notes: /references/normalization-notes.md
  update_log: /state/log.md
---

# Rutas de auditoría

## Fuentes de auditoría

| Necesidad | Archivo |
| --- | --- |
| Relacionar archivos migrados con los archivos de contexto originales | `/references/source-map.md` |
| Explain semantic fixes made during migración | `/references/normalization-notes.md` |
| See chronological contexto-bundle changes | `/state/log.md` |
| Confirm estado actual de implementación | `/state/progress-tracker.md` |
| Inspect full completada-funcionalidad evidencia | `/state/feature-history.md` |

## Comportamiento de actualización requerido

- Añade una entrada a `/state/log.md` siempre que cambien la estructura del contexto, la semántica, la normalización o el enrutamiento de archivos.
- Actualiza `/references/source-map.md` cuando los archivos se renombren, dividan, fusionen o se creen a partir de material existente.
- Actualiza `/references/normalization-notes.md` cuando se resuelvan afirmaciones contradictorias de las fuentes.
- Conserva los hechos de origen salvo que un estado de implementación posterior los sustituya explícitamente.

## Patrón de trazabilidad de afirmaciones

1. Identifica el archivo conceptual que contiene la afirmación.
2. Leer its `source_files` frontmatter.
3. Consulta `/references/source-map.md` para ver el mapeo de migración.
4. Consulta `/references/normalization-notes.md` para ver los cambios semánticos.
5. Consulta `/state/log.md` para ver los cambios cronológicos del contexto.
6. Usa `/state/feature-history.md` solo cuando haga falta evidencia de implementación.
