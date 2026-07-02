---
type: Estado del proyecto
título: Ghost AI Rastreador de progreso
description: Fase actual de implementación, objetivo activo, preguntas abiertas, decisiones de arquitectura y notas de sesión.
tags: [estado, progress, hoja de ruta, decisions]
timestamp: 2026-07-01T13:40:00Z
context_role: actual-state
cache_policy: recargar-en-cada-tarea
related:
  - /state/feature-history.md
  - /architecture/system.md
  - /engineering/development-workflow.md
auditoría:
  source_map: /references/source-map.md
  normalization_notes: /references/normalization-notes.md
  update_log: /state/log.md
source_files:
  - progress-tracker.md
---

# Rastreador de progreso

## Tarjeta de contexto

| Campo | Valor |
| --- | --- |
| Rol de contexto | Verdad actual de implementación. |
| Clase de caché | Estado actual volátil. |
| Cuándo cargar | Al iniciar cualquier tarea de planificación, implementación, hoja de ruta o estado. |
| Combinar con | `/state/feature-history.md` solo cuando evidencia histórica de implementación is needed. |
| Regla de actualización | Actualizar this archivo whenever actual phase, active funcionalidad, implementación state, o open questions change. |

## Claves de recuperación

- actual phase
- actual goal
- en progress
- next up
- open questions
- decisiones de arquitectura
- session notes


Actualiza este archivo cada vez que cambie la fase actual, la funcionalidad activa o el estado de implementación.

## Fase actual

- Funcionalidad 29 (Integración de UI de especificaciones) — completada.

## Objetivo actual

- Por definir.

## Registro de funcionalidades completadas

El registro de funcionalidades completadas se dividió en [Historial de funcionalidades](/state/feature-history.md) para que este archivo se mantenga enfocado en el estado actual.

## En progreso

- Ninguna.

## Siguiente

- Por definir.

## Preguntas abiertas

- Ninguna por ahora.

## Decisiones de arquitectura

- shadcn/ui over Tailwind v4 (CSS-based token config mediante @theme inline en globals.css, no tailwind.config.js).
- Dark-solo theme: all shadcn:raíz variables set a dark values directamente — no.dark class switching.
- Do not modify generado componentes/ui/* archivos after shadcn installation.
- Next.js 16 usa proxy.ts (no middleware.ts) — misma API, renombrado a reflect its purpose.

## Notas de sesión

- Next.js 16.2.4 con React 19 y Tailwind CSS v4.
- shadcn version 4.5.0 was used; it auto-detected Tailwind v4.
- lucide-react ^1.11.0 instalado como a direct dependency.
- @clerk/nextjs ^7.2.7 y @clerk/ui ^1.6.7 instalado.
- @liveblocks/nodo instalado junto a los campos existentes @liveblocks/cliente, @liveblocks/react, @liveblocks/react-flujo, @liveblocks/react-ui. Liveblocks cliente usa lazy init (getLiveblocks()) a avoid key validación errors at build time.
- @vercel/blob ^2.3.3 instalado. BLOB_READ_WRITE_TOKEN set en.env.local.
- @trigger.dev/sdk ^4.4.4 instalado. trigger.config.ts lee la referencia del proyecto desde la variable de entorno TRIGGER_PROJECT_REF. TRIGGER_SECRET_KEY debe estar definida en .env.local para lanzar tareas desde código de servidor. Ejecuta `npx trigger.dev@latest dev` para local development; deploy con `npx trigger.dev@latest deploy`.
- Prisma 7.8.0 — generado cliente goes a app/generado/prisma/; importar PrismaClient desde @/app/generado/prisma/cliente (no índice.ts en v7). Constructor always requiere { adapter } argument. @prisma/adapter-pg used para all connections.
- prisma.config.ts usa `schema: "prisma/"` (esquema multiarchivo) y lee DATABASE_URL desde .env mediante dotenv.


## Reglas de mantenimiento de contexto

| Tipo de cambio | Actualizar | Notas |
| --- | --- | --- |
| Cambió la fase actual, objetivo activo, trabajo en progreso, siguiente trabajo o pregunta abierta | Este archivo | Mantén este archivo breve y actualizado. |
| Funcionalidad completada con implementación detail | `/state/feature-history.md` | Conserva allí el registro completo de la funcionalidad; aquí resume solo el estado actual. |
| Cambió algún invariante de arquitectura, almacenamiento, autenticación, colaboración, tareas o canvas | `/architecture/system.md` | Actualiza también este archivo si el cambio afecta al estado actual. |
| Cambió una convención de código, regla de API, regla de organización de archivos o regla de estilos | `/engineering/code-standards.md` o `/design/ui-context.md` | Mantén las reglas para desarrolladores cerca del dominio donde se aplican. |
| paquete de contexto structure, migración, o semantic normalization cambió | `/state/log.md` y optionally `/references/normalization-notes.md` | Usa el registro para chronology y notas de normalización para semantic corrections. |

Regla práctica de mantenimiento: el estado actual va aquí; la verdad conceptual duradera va en el archivo de dominio correspondiente; el historial largo va en el historial de funcionalidades; y la evidencia de cambios del paquete va en el registro.

## Conceptos relacionados

- [Historial de funcionalidades](/state/feature-history.md) conserva el registro completo de completada-funcionalidad registry.
- [Flujo de trabajo de desarrollo](/engineering/development-workflow.md) define cuándo y cómo debe actualizarse este archivo.
