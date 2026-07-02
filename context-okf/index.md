---
okf_version: "0.1"
type: índice
title: Paquete OKF de Ghost AI
description: Índice raíz de enrutamiento del paquete de contexto del espacio colaborativo de diseño de sistemas de Ghost AI.
timestamp: 2026-07-01T13:40:00Z
context_role: ancla-de-enrutamiento
cache_policy: núcleo-estable
progressive_ingestion: true
related:
  - /README.md
  - /context/index.md
  - /state/progress-tracker.md
  - /state/log.md
auditoría:
  source_map: /references/source-map.md
  normalization_notes: /references/normalization-notes.md
  update_log: /state/log.md
---

# Paquete OKF de Ghost AI

## Propósito

Capa de contexto canónica de Ghost AI: un espacio colaborativo en tiempo real para diseñar sistemas, donde los usuarios autenticados crean proyectos de arquitectura, trabajan juntos sobre un canvas compartido, usan IA para generar o ampliar diseños y guardan las especificaciones generadas en Markdown.

## Desarrolladores: empezar aquí

Si estás revisando este paquete como desarrollador, empieza por el recorrido mínimo útil:

| Objetivo | Leer | Por qué |
| --- | --- | --- |
| Entender la app rápidamente | `/README.md`, Después este archivo | Primero orientación del desarrollador; después mapa de enrutamiento agéntico. |
| Implementar una feature | `/state/progress-tracker.md`, `/engineering/development-workflow.md`, después el archivo de dominio correspondiente | Estado actual más reglas de implementación. |
| Cambiar arquitectura, almacenamiento, autenticación, tareas, etc | `/architecture/system.md`, `/engineering/code-standards.md` | Contienen los invariantes de mayor riesgo. |
| Modificar la interfaz o los elementos visuales | `/design/ui-context.md`, `/engineering/code-standards.md` | Mantiene consistentes las reglas de estilos, componentes, tipografia, colores y layout. |
| Revisar cambios del contexto | `/state/log.md`, `/references/normalization-notes.md`, `/references/source-map.md` | Rastro de auditoría y mapeo de fuentes. |

Regla práctica para desarrolladores: lee primero el estado actual y después el archivo del dominio correspondiente. 
No profundices más salvo que un enlace o requisito te lleve allí.

## Contrato de carga para agentes

| Situación | Cargar primero | Cargar después solo si hace falta |
| --- | --- | --- |
| Tarea cualquiera | `/index.md`, `/context/index.md`, `/state/progress-tracker.md` | Conceptos específicos de la tarea abajo |
| Decisión de producto o alcance | `/product/overview.md` | `/state/feature-history.md` para consultar evidencia de implementación |
| Decisión de arquitectura o almacenamiento | `/architecture/system.md` | `/engineering/code-standards.md`, `/state/feature-history.md` |
| Implementación de código | `/engineering/code-standards.md`, `/engineering/development-workflow.md` | `/architecture/system.md`, `/design/ui-context.md` |
| Implementación de UI | `/design/ui-context.md`, `/engineering/code-standards.md` | `/product/overview.md` |
| Estado, hoja de ruta o auditoría | `/state/progress-tracker.md`, `/state/log.md` | `/state/feature-history.md`, `/references/source-map.md` |

## Gestión de contexto

* [README para personas](/README.md) - Guía clara para revisar, editar y mantener el paquete.
* [Gestión de contexto índice](/context/index.md) - caché estratégica, ingesta progresiva, reglas de enlaces y reglas de auditoría.
* [Estrategia de caché](/context/cache-strategy.md) - estrategia de carga para distinguir entre núcleo estable y estado volátil.
* [Guía de ingesta progresiva](/context/progresiva-ingesta.md) - perfiles de carga de contexto basados en la tarea.
* [Rutas de auditoría](/context/audit-paths.md) - Cómo rastrear afirmaciones hasta sus fuentes, normalizaciones y entradas del registro de cambios.

## Conceptos de dominio

| Área | Punto de entrada | Contenido |
| --- | --- | --- |
| Producto | [Producto índice](/product/index.md) | Objetivos, flujo de usuario, alcance, criterios de éxito. |
| Arquitectura | [Arquitectura índice](/architecture/index.md) | Stack tecnológico, límites, modelo de almacenamiento, autenticación, colaboración, generación con IA e invariantes. |
| Ingeniería | [Ingeniería índice](/engineering/index.md) | Estándares de código y flujo de implementación guiado por especificaciones. |
| Diseño | [Índice de diseño](/design/index.md) | Estilos, theming, tipografía, colores y patrones de layout. |
| Estado | [Estado índice](/state/index.md) | Estado actual, historial de funcionalidades y registro cronológico de actualizaciones. |
| Referencias | [Referencias índice](/references/index.md) | Source map y notas de normalización. |

## Reglas de recuperación

- Usa `/README.md` para la orientación general y este archivo para el enrutamiento del paquete, no para detalles de implementación.
- Usa `/state/progress-tracker.md` para consultar el estado actual de la implementación.
- Usa `/state/feature-history.md` solo cuando necesites detalle histórico de una funcionalidad.
- Usa `/state/log.md` para la cronología de auditoría y los cambios del paquete de contexto.
- Para navegación interna, hace seguimiento de solo enlaces absolutos relativos al paquete que empiecen por `/`.
