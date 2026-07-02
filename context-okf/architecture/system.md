---
type: Arquitectura del sistema
título: Ghost AI Arquitectura del sistema
description: Stack, límites del sistema, modelo de almacenamiento, modelo de autenticación, modelo de generación de IA, modelo de plantillas iniciales e invariantes de implementación.
tags: [arquitectura, stack, almacenamiento, autenticación, liveblocks, trigger-dev, vercel-blob]
timestamp: 2026-07-01T13:40:00Z
context_role: estable-concepto
cache_policy: núcleo-estable
related:
  - /product/overview.md
  - /engineering/code-standards.md
  - /engineering/development-workflow.md
  - /state/progress-tracker.md
auditoría:
  source_map: /references/source-map.md
  normalization_notes: /references/normalization-notes.md
  update_log: /state/log.md
source_files:
  - architecture-context.md
  - progress-tracker.md
---

# Contexto de arquitectura

## Tarjeta de contexto

| Campo | Valor |
| --- | --- |
| Rol de contexto | Límites del sistema e invariantes de implementación. |
| Clase de caché | Concepto estable. |
| Cuándo cargar | Cuando se tomen decisiones sobre arquitectura, almacenamiento, autenticación, colaboración, flujos de IA o persistencia. |
| Combinar con | `/engineering/code-standards.md` para reglas de implementación; `/state/progress-tracker.md` para el estado actual. |
| Ruta de auditoría | `/references/normalization-notes.md` explica la normalización de la terminología de almacenamiento. |

## Claves de recuperación

- Next.js 16
- Clerk
- Prisma PostgreSQL
- Liveblocks React Flow
- Trigger.dev
- Vercel Blob
- límites del sistema
- invariantes


## Stack

| Capa | Tecnología | Rol |
| --- | --- | --- |
| Framework | Next.js 16 + TypeScript | App full-stack con límites servidor/cliente. |
| UI | Tailwind + shadcn/ui | Composición de componentes y estilos. |
| autenticación | Clerk | Identidad de usuario y protección de rutas. |
| base de datos | Prisma + PostgreSQL | Metadatos relacionales: proyectos, colaboradores, especificaciones, ejecuciones de tareas. |
| Canvas | Liveblocks + React Flow | Canvas colaborativo en tiempo real, presencia y cursores. |
| segundo plano tareas | Trigger.dev | Flujos duraderos de generación de IA. |
| Almacenamiento de artefactos | Vercel Blob | snapshots del canvas y especificaciones Markdown generadas. |

## Límites del sistema

- `app/api` — manejadores de solicitudes autenticadas: validación de entrada, comprobaciones de propiedad, lanzamiento de tareas y persistencia.
- `trigger` — trabajos en segundo plano de larga duración: generación de diseños con IA y generación de especificaciones.
- `lib` — infraestructura compartida: cliente Prisma, helpers de control de acceso y utilidades.
- `components` — composición de UI: superficies de canvas, barras laterales, diálogos y elementos interactivos.
- `prisma` — esquema de base de datos y salida del cliente generado.
- `data` — directorio local heredado. No se usa para artefactos nuevos.

## Modelo de almacenamiento

- **base de datos**: metadatos, propiedad, relaciones y registros de ejecución de tareas.
- **Vercel Blob**: artefactos generados — snapshots del canvas en `canvas/{projectId}.json` y especificaciones en `specs/{projectId}/{specId}.md`.
- Los registros de proyecto, especificación y ejecución de tareas pertenecen a PostgreSQL.
- El contenido del canvas y la salida Markdown se almacenan en Vercel Blob y se recuperan desde allí.
- La URL del blob del canvas se almacena en el registro del proyecto como `canvasBlobUrl`.
- generado especificación blob references are guardados on especificación registros como `filePath`.
- El contenido generado de gran tamaño no debe guardarse directamente en PostgreSQL.

## Modelo de autenticación y colaboración

- Cada proyecto tiene un único propietario, identificado por el ID de usuario de Clerk.
- Los proyectos pueden incluir colaboradores adicionales.
- Solo los usuarios autenticados pueden acceder a rutas protegidas.
- Solo el propietario o un colaborador pueden modificar recursos del proyecto.
- Los tokens de salas de Liveblocks solo se emiten después de verificar la pertenencia al proyecto.

## Diseños iniciales de sistema

- Las plantillas predefinidas son snapshots estáticos del canvas guardados en el código.
- Al importar una plantilla, se carga en la sala activa de Liveblocks.
- La importación puede hacerse al crear el canvas o desde el editor en cualquier momento.
- Los datos de las plantillas siguen el mismo esquema de nodos y aristas que el contenido del canvas creado por el usuario.
- Las plantillas no requieren un registro separado en base de datos; se resuelven por ID de plantilla en el momento de importarlas.

## Modelo de generación de IA

### Generación de diseño

- **Input**: prompt del usuario, contexto del proyecto y estado actual del canvas.
- **Ejecución**: tarea duradera en segundo plano mediante Trigger.dev.
- **Salida**: actualizaciones estructuradas de nodos y aristas escritas en la sala compartida de Liveblocks.

### Generación de especificaciones

- **Entrada**: grafo actual del canvas y contexto del proyecto.
- **Ejecución**: tarea duradera en segundo plano mediante Trigger.dev.
- **Salida**: especificación técnica en Markdown guardada en Vercel Blob y vinculada al proyecto en PostgreSQL.

## Invariantes

1. solicitud handlers do not run long-lived AI work — that corresponde en segundo plano tareas.
2. Los metadatos y los artefactos generados de gran tamaño se guardan en capas separadas.
3. La autenticación y la propiedad se aplican en cada punto de mutación.
4. Los componentes de cliente se usan solo cuando hacen falta interactividad en el navegador o estado en tiempo real.
5. El esquema del canvas debe mantenerse consistente entre el contenido creado por el usuario y las plantillas importadas.

## Conceptos relacionados

- [Estándares de código](/engineering/code-standards.md) translates these límites into reglas de implementación.
- [Flujo de trabajo de desarrollo](/engineering/development-workflow.md) explica cómo deben proteger estos límites los pasos de implementación.
- [Rastreador de progreso](/state/progress-tracker.md) registra el estado actual implementado de esta arquitectura.
