---
type: Resumen de producto
título: Ghost AI
description: Espacio colaborativo en tiempo real para diseño de sistemas con arquitectura generada por IA y especificaciones Markdown.
tags: [producto, alcance, usuario-flujo, ghost-ai]
timestamp: 2026-07-01T13:40:00Z
context_role: estable-concepto
cache_policy: núcleo-estable
related:
  - /architecture/system.md
  - /design/ui-context.md
  - /state/progress-tracker.md
auditoría:
  source_map: /references/source-map.md
  normalization_notes: /references/normalization-notes.md
  update_log: /state/log.md
source_files:
  - project-overview.md
---

# Ghost AI

## Tarjeta de contexto

| Campo | Valor |
| --- | --- |
| Rol de contexto | Producto intención y alcance. |
| Clase de caché | Concepto estable. |
| Cuándo cargar | Planning funcionalidades, validating alcance, writing especificaciones, o comprobación criterios de éxito. |
| Combinar con | `/architecture/system.md` para feasibility; `/state/progress-tracker.md` para estado actual de implementación. |
| Evitar cargar | Full funcionalidad historial unless implementación evidencia is necesaria. |

## Claves de recuperación

- `Ghost AI`
- espacio colaborativo de diseño de sistemas
- objetivos de producto
- core flujo de usuario
- funcionalidad alcance
- criterios de éxito


## Resumen

Ghost AI es un espacio colaborativo en tiempo real para diseñar sistemas. Los usuarios describen un sistema en lenguaje natural, un agente de IA lo plasma en un canvas compartido, los colaboradores refinan la arquitectura y la app genera una especificación técnica a partir del grafo resultante.

## Objetivos

1. Let usuarios autenticados create y manage arquitectura projects.
2. Ofrecer un canvas colaborativo en tiempo real para diseño de sistemas.
3. Permitir que los usuarios importen diseños iniciales predefinidos al canvas.
4. Let AI generar an initial arquitectura desde a natural language prompt.
5. Permitir que los colaboradores refinen la arquitectura generada.
6. Convertir el grafo final en una especificación técnica persistente en Markdown.

## Flujo principal de usuario

1. El usuario inicia sesión.
2. El usuario crea o selecciona un proyecto.
3. El usuario entra en el espacio de trabajo del proyecto.
4. Opcionalmente, el usuario importa una plantilla inicial de diseño de sistema al canvas.
5. El usuario pide a la IA generar o ampliar el diseño del sistema.
6. La IA genera nodos y aristas en el canvas compartido.
7. Los colaboradores editan y refinan el diseño.
8. El usuario inicia la generación de especificaciones.
9. La app persiste la especificación generada en Markdown.
10. El usuario revisa o descarga la especificación.

## Funcionalidades

### Autenticación y proyectos

- Inicio de sesión de usuario y protección de rutas.
- Creación de proyectos, propiedad y acceso de colaboradores.
- Lista de proyectos y navegación del espacio de trabajo.

### Canvas colaborativo

- Canvas compartido en tiempo real con Liveblocks y React Flow.
- Live cursors, presence indicators, y nodo/arista editing.
- Los snapshots del canvas se persisten en Vercel Blob y se referencian desde PostgreSQL.

### Diseños iniciales de sistema

- Biblioteca curada de plantillas predefinidas de diseño de sistemas.
- Los usuarios pueden importar una plantilla inicial al canvas en cualquier momento de la edición.
- Las plantillas son snapshots estáticos del canvas que se cargan directamente en la sala activa.
- Las plantillas implementadas actualmente incluyen Arquitectura de Microservicios, Pipeline CI/CD y Sistema orientado a eventos.
- En el futuro, las categorías de plantillas podrían incluir monolito, serverless y otros patrones de sistemas comunes.

### Generación de arquitectura con IA

- AI genera a diseño de sistemas desde a usuario-supplied prompt.
- La salida se estructura como nodos y aristas del canvas escritas en la sala compartida.
- generación runs como a durable segundo plano tarea.

### Generación de especificaciones

- El grafo actual del canvas se convierte en una especificación técnica Markdown.
- Las especificaciones se persisten como archivos Markdown en Vercel Blob y se vinculan al proyecto en PostgreSQL.
- usuarios can view y download generado especificaciones.

## Alcance

### Dentro del alcance

- Authentication y ruta protection.
- Creación y propiedad de proyectos.
- Collaborator access by project.
- Starter diseño de sistemas template library y importar.
- Canvas compartido en tiempo real con nodos, aristas y presencia.
- AI-powered arquitectura generación desde prompts.
- Generación de especificaciones Markdown con IA a partir del grafo del canvas.
- Persistent almacenamiento para metadatos del proyecto y artefactos generados.
- especificación download.

### Fuera de alcance

- Billing y subscription systems.
- Enterprise permission tiers beyond propietario y collaborator.
- Versioned especificación historial y review flujos de trabajo.
- Productoion object almacenamiento migración.
- Mobile-native applications.

## Criterios de éxito

1. A signed-en usuario can create y open a project.
2. Varios usuarios pueden colaborar simultáneamente en el mismo canvas.
3. Un usuario puede importar un diseño inicial predefinido al canvas.
4. La IA puede generar una arquitectura en la sala compartida a partir de un prompt.
5. El grafo puede convertirse en una especificación Markdown persistida.
6. Los metadatos del proyecto y los artefactos generados se almacenan en las capas correctas.

## Conceptos relacionados

- [Arquitectura del sistema](/architecture/system.md) explica el stack, las capas de almacenamiento, el modelo de autenticación y los invariantes de implementación de este producto.
- [Contexto de UI](/design/ui-context.md) explica la experiencia visual oscura del espacio de trabajo usada por el producto.
- [Rastreador de progreso](/state/progress-tracker.md) registra el estado actual de implementación.
