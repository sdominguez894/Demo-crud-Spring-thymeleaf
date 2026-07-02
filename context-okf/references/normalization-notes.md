---
type: Reference
título: Notas de normalización
description: Correcciones semánticas aplicadas al migrar los archivos de contexto de Ghost AI a OKF.
tags: [migración, normalization, semantic-limpioup]
timestamp: 2026-07-01T13:40:00Z
context_role: auditoría-reference
cache_policy: carga-diferida
related:
  - /references/source-map.md
  - /architecture/system.md
  - /product/overview.md
  - /state/progress-tracker.md
source_files:
  - project-overview.md
  - architecture-context.md
  - progress-tracker.md
---

# Notas de normalización

## Redacción sobre almacenamiento

El resumen de producto original decía que los snapshots del canvas se persistían en el sistema de archivos. Registros posteriores de arquitectura y progreso establecen Vercel Blob como la capa de almacenamiento de artefactos para snapshots del canvas y especificaciones Markdown generadas. El paquete OKF usa Vercel Blob de forma consistente.

## Nomenclatura de campos de base de datos

El contexto de arquitectura original hacía referencia a `canvasJsonPath` como puntero del artefacto del proyecto. El historial de progreso registra que este campo se renombró a `canvasBlobUrl`. El paquete OKF usa:

- `canvasBlobUrl` para referencias al blob del snapshot del canvas en los registros de proyecto.
- `filePath` para generado especificación blob references on especificación registros.

## Persistencia de especificaciones

El contexto de arquitectura original decía que las especificaciones generadas se guardaban en el sistema de archivos. Registros posteriores establecen que las especificaciones generadas se suben a Vercel Blob y se representan con registros `ProjectSpec` en PostgreSQL. El paquete OKF usa Vercel Blob más metadatos en PostgreSQL de forma consistente.

## Fase actual

El rastreador de progreso original indicaba la Funcionalidad 28 como fase actual completada y la Funcionalidad 29 como pendiente, pero el registro completado incluye la Funcionalidad 29 como completada y no hay trabajo listado en progreso. Por eso, el rastreador OKF fija la fase actual como Funcionalidad 29 completada y el objetivo actual como Por definir.

## Orden de funcionalidades

El registro de funcionalidades completadas incluye la Funcionalidad 29 antes de la Funcionalidad 28. La migración OKF conserva las etiquetas y detalles originales en lugar de renumerar registros históricos.


## Optimización de ingeniería de contexto

| Cambio | Motivo | Pérdida de información |
| --- | --- | --- |
| Se añadieron archivos de gestión en `/context/*`. | Hace explícitas la política de caché, los perfiles de ingesta y las rutas de auditoría que antes eran implícitas. | Ninguno; los archivos derivan de metadatos de enrutamiento. |
| Se añadieron tarjetas de contexto y claves de recuperación a archivos conceptuales centrales. | Prioriza la recuperación estructural sobre la prosa narrativa, manteniendo el detalle de fuentes debajo. | Ninguna. |
| Converted interno links a bundle-relative absolute paths. | Prevents ambiguity cuando archivos are moved o cargado outside their directorio contexto. | Ninguna. |
| Se conservaron las funcionalidades completadas en `/state/feature-history.md`, no en la raíz ni en el progreso actual. | Evita sobrecargar la ventana de contexto y conserva toda la evidencia histórica. | Ninguna. |
| Se añadió frontmatter a los índices de directorio. | Hace que los enrutadores de directorio sean legibles semánticamente por máquina sin perder el comportamiento de índice OKF. | Ninguna. |

## Pasada de experiencia de desarrollo

| Cambio | Motivo | Pérdida de información |
| --- | --- | --- |
| Se añadió `/README.md` como punto de entrada para personas. | Da a los desarrolladores un recorrido claro por el paquete sin debilitar `/index.md` como ancla de enrutamiento para agentes. | Ninguna. |
| Se añadió una sección “Desarrolladores: empezar aquí” a `/index.md`. | Separa la exploración humana del contrato de carga para agentes. | Ninguna. |
| Tightened starter template wording en `/product/overview.md`. | Distinguishes implementado plantillas desde future template categories so agents do not infer missing funcionalidades como already built. | Ninguno; ambition was conservado como future alcance. |
| Se añadieron reglas de mantenimiento de contexto a `/state/progress-tracker.md`. | Facilita la gestión continua del paquete para personas y conserva la recuperación del estado actual. | Ninguna. |
