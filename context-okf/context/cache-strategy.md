---
type: contexto Estrategia de caché
título: Ghost AI Caché estratégica de contexto
description: Política de caché para mantener activos los conceptos estables y evitar la carga innecesaria de estado volátil y detalle histórico extenso.
tags: [ingeniería de contexto, almacenamiento en caché, okf]
timestamp: 2026-07-01T13:40:00Z
context_role: caché-policy
cache_policy: núcleo-estable
related:
  - /index.md
  - /context/progresiva-ingesta.md
  - /state/progress-tracker.md
  - /state/log.md
auditoría:
  update_log: /state/log.md
---

# Caché estratégica de contexto

## Clases de caché

| Clase | Archivos | Comportamiento de caché | Motivo |
| --- | --- | --- | --- |
| Núcleo de enrutamiento | `/index.md`, `/context/index.md` | Mantener en caché de forma agresiva. | Es pequeño, estable y apunta al resto del contexto. |
| Conceptos estables | `/product/overview.md`, `/architecture/system.md`, `/engineering/code-standards.md`, `/engineering/development-workflow.md`, `/design/ui-context.md` | Mantener en caché por sesión o hasta que cambien los archivos fuente. | Define restricciones duraderas de producto, arquitectura, ingeniería y diseño. |
| Estado actual | `/state/progress-tracker.md` | Recargar al inicio de cada tarea. | Representa la fase actual, el objetivo, las preguntas abiertas y las notas de sesión. |
| Estado de auditoría | `/state/log.md`, `/references/source-map.md`, `/references/normalization-notes.md` | Cargar solo para auditoría, migración, o procedencia questions. | Es importante, aunque normalmente no hace falta para implementar. |
| Detalle histórico | `/state/feature-history.md` | Cargar de forma diferida solo cuando haga falta historial de implementación. | Aporta mucho valor, pero es demasiado largo para cargarlo por defecto. |

## Claves de caché

| Key | Valor |
| --- | --- |
| Bundle | `ghost-ai-okf` |
| OKF version | `0.1` |
| Producto | `Ghost AI` |
| Fuente del estado actual | `/state/progress-tracker.md` |
| auditoría log | `/state/log.md` |

## Reglas de invalidación de caché

- Invalida la caché de conceptos estables cuando cambie la marca temporal de un archivo conceptual.
- Recarga siempre `/state/progress-tracker.md` antes de cualquier trabajo de planificación o implementación.
- Añade siempre una entrada a `/state/log.md` cuando cambien la estructura del contexto, la semántica, el mapeo de fuentes, la normalización o las reglas de auditoría.
- No guardes `/state/feature-history.md` en caché por defecto; carga fragmentos solo cuando necesites evidencia de una funcionalidad.

## No sobrealimentar el contexto

- No cargues todo el historial de funcionalidades para preguntas rutinarias de código, UI o producto.
- No cargues referencias salvo que el usuario pregunte por migración, procedencia, normalización o audipestañailidad.
- No dupliques listas largas de archivos de detalle dentro de los índices.
