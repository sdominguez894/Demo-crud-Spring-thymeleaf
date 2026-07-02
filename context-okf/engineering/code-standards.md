---
type: código Standard
título: Ghost AI Estándares de código
description: Restricciones de implementación para TypeScript, Next.js, estilos, rutas de API, almacenamiento de datos y organización de archivos.
tags: [engineering, typescript, nextjs, api-rutas, almacenamiento, conventions]
timestamp: 2026-07-01T13:40:00Z
context_role: estable-concepto
cache_policy: núcleo-estable
related:
  - /architecture/system.md
  - /engineering/development-workflow.md
  - /design/ui-context.md
auditoría:
  source_map: /references/source-map.md
  normalization_notes: /references/normalization-notes.md
  update_log: /state/log.md
source_files:
  - código-standards.md
---

# Estándares de código

## Tarjeta de contexto

| Campo | Valor |
| --- | --- |
| Rol de contexto | Restricciones de implementación. |
| Clase de caché | Concepto estable. |
| Cuándo cargar | Writing o revisar TypeScript, Next.js, API rutas, almacenamiento logic, archivo organization, o styling. |
| Combinar con | `/architecture/system.md` para límites; `/design/ui-context.md` para UI styling. |
| Volatile dependency | Reload `/state/progress-tracker.md` before implementación work. |

## Claves de recuperación

- strict TypeScript
- React Server componentes
- thin ruta handlers
- CSS token styling
- Vercel Blob artefactos
- Prisma metadata
- archivo organization


## General

- Keep modules small y único-purpose.
- Fix raíz causa — do not layer workarounds.
- Do not mix unrelated concerns en one component o ruta.
- Respeta los límites del sistema definidos en [Arquitectura del sistema](/architecture/system.md).

## TypeScript

- El modo estricto es obligatorio en todo el proyecto.
- Evita `any`; usa interfaces explícitas o tipos de alcance bien acotado.
- Validate unknown external entrada at límites del sistema before trusting it.
- Usa `interface` para contratos de objetos.

## Next.js

- Default a React Server componentes.
- Añade `"use client"` solo cuando el componente necesite interactividad del navegador, hooks o estado en tiempo real.
- Keep ruta handlers focused on a único responsibility.
- Long-running work corresponde en segundo plano tareas, not en solicitud handlers.

## Estilos

- Usa tokens de propiedades CSS personalizadas definidos en `globals.css`; nada de clases de color Tailwind sin procesar como `zinc-*` ni valores hexadecimales hardcodeados.
- Reference tokens through their Tailwind utility names: `bg-base`, `text-copy-primary`, `border-surface-border`, `text-brand`, etc.
- Mantén la escala de radios: `rounded-xl` para elementos pequeños, `rounded-2xl` para tarjetas y `rounded-3xl` para modales.

## Rutas de API

- Validate y parse solicitud entrada before any logic runs.
- Enforce autenticación y project propiedad comprobaciones before any mutation.
- Return consistent, predicpestañale response shapes.
- Keep ruta handlers thin — push complexity into compartido modules o segundo plano tareas.

## Datos y almacenamiento

- Los metadatos y relaciones del proyecto pertenecen a PostgreSQL mediante Prisma.
- Los snapshots del canvas y las especificaciones generadas van en Vercel Blob; Prisma almacena solo la referencia a la URL del blob.
- No guardes contenido generado de gran tamaño directamente en la base de datos.
- Tarea run registros are first-class relational datos — treat propiedad y run IDs como verified before any token issuance.

## Organización de archivos

- `lib/` — compartido infraestructura: Prisma cliente, autenticación helpers, utilidades.
- `trigger/` — all durable segundo plano tareas y AI flujos de trabajo.
- `components/` — UI composition solo; no business logic.
- `app/api/` — ruta handlers para autenticación, lanzamiento, y persistencia.
- Nombra los archivos según la responsabilidad que contienen, no según la tecnología.

## Conceptos relacionados

- [Arquitectura del sistema](/architecture/system.md) define los límites que estos estándares protegen.
- [Contexto de UI](/design/ui-context.md) explica el sistema de tokens y las primitivas de layout referenciadas por los estándares de estilo.
- [Flujo de trabajo de desarrollo](/engineering/development-workflow.md) explica cómo mantener estos estándares sincronizados con el trabajo de implementación.
