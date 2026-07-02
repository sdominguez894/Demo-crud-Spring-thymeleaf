---
type: Sistema de diseño
título: Ghost AI Contexto de UI
description: Tema exclusivamente oscuro, sistema de tokens, tipografía, escala de radios, visuales del canvas, reglas de biblioteca de componentes y patrones de layout.
tags: [ui, diseño-system, tailwind, shadcn-ui, canvas]
timestamp: 2026-07-01T13:40:00Z
context_role: estable-concepto
cache_policy: núcleo-estable
related:
  - /product/overview.md
  - /engineering/code-standards.md
auditoría:
  source_map: /references/source-map.md
  normalization_notes: /references/normalization-notes.md
  update_log: /state/log.md
source_files:
  - ui-context.md
---

# Contexto de UI

## Tarjeta de contexto

| Campo | Valor |
| --- | --- |
| Rol de contexto | Restricciones de implementación visual y de UI. |
| Clase de caché | Concepto estable. |
| Cuándo cargar | Al construir o revisar UI, visuales del canvas, layout, tokens, tipografía o uso de componentes. |
| Combinar con | `/engineering/code-standards.md` para reglas de estilos; `/product/overview.md` para la intención del usuario. |
| Evitar cargar | detalle de arquitectura salvo que el trabajo de UI toque estado del canvas, autenticación, persistencia o flujos de IA. |

## Claves de recuperación

- dark-solo theme
- CSS custom properties
- Tailwind tokens
- Geist Sans
- Geist Mono
- radius scale
- Canvas con React Flow
- shadcn/ui
- Lucide React


## Tema

Solo modo oscuro. Sin modo claro. El lenguaje visual es un espacio de trabajo técnico oscuro: fondos casi negros, superficies por capas y colores de acento vivos para elementos interactivos.

Todos los colores se definen como propiedades CSS personalizadas en `globals.css` y se asignan a tokens de Tailwind mediante `@theme inline`. Los componentes deben usar estos tokens: nada de valores hexadecimales hardcodeados ni clases de color Tailwind sin procesar como `zinc-*`.

| Rol             | CSS Variable           | Hex / Valor               |
| ---------------- | ---------------------- | ------------------------- |
| Page segundo plano  | `--bg-base`            | `#080809`                 |
| Surface          | `--bg-surface`         | `#111114`                 |
| Elevated surface | `--bg-elevated`        | `#18181c`                 |
| Subtle surface   | `--bg-subtle`          | `#1e1e23`                 |
| Default border   | `--border-default`     | `#2a2a30`                 |
| Subtle border    | `--border-subtle`      | `#3a3a42`                 |
| Primary text     | `--text-primary`       | `#f0f0f4`                 |
| Secondary text   | `--text-secondary`     | `#c0c0cc`                 |
| Muted text       | `--text-muted`         | `#808090`                 |
| Faint text       | `--text-faint`         | `#505060`                 |
| Brand accent     | `--accent-primary`     | `#00c8d4` (cyan)          |
| Brand dim        | `--accent-primary-dim` | `rgba(0, 200, 212, 0.12)` |
| AI accent        | `--accent-ai`          | `#6457f9` (indigo-purple) |
| AI text          | `--accent-ai-text`     | `#8b82ff`                 |
| Error            | `--state-error`        | `#ff4d4f`                 |
| éxito          | `--state-success`      | `#34d399`                 |
| Warning          | `--state-warning`      | `#fbbf24`                 |

Los nombres de las utilidades de Tailwind se corresponden con estas variables. Usa `bg-base`, `bg-surface`, `text-copy-primary`, `text-copy-muted`, `border-surface-border`, `text-brand`, `bg-accent-dim`, etc.

## Tipografía

| Rol      | Font       | CSS Variable        |
| --------- | ---------- | ------------------- |
| UI text   | Geist Sans | `--font-geist-sans` |
| código/mono | Geist Mono | `--font-geist-mono` |

Ambas fuentes se cargan mediante `next/font/google` y se aplican como variables CSS en el elemento `<html>`. El `body` base usa Geist Sans con `antialiased`.

## Radio de borde

Radius increases con surface depth — smaller para inner elementos, larger para outer containers.

| contexto           | Clase         |
| ----------------- | ------------- |
| Inline / small UI | `rounded-xl`  |
| Cards / panels    | `rounded-2xl` |
| Modal / overlay   | `rounded-3xl` |

## Canvas

### Paleta de colores de nodos

Hay 8 pares de colores definidos. Cada par especifica un relleno oscuro para el nodo y un color de texto vivo con buen contraste, ajustado para ser legible sobre el canvas oscuro. Están definidos en `types/canvas.ts` como `NODE_COLORS`.

| nodo fill | Text color | Character              |
| --------- | ---------- | ---------------------- |
| `#1F1F1F` | `#EDEDED`  | Neutral dark (default) |
| `#10233D` | `#52A8FF`  | Blue                   |
| `#2E1938` | `#BF7AF0`  | Purple                 |
| `#331B00` | `#FF990A`  | Orange                 |
| `#3C1618` | `#FF6166`  | Red                    |
| `#3A1726` | `#F75F8F`  | Pink                   |
| `#0F2E18` | `#62C073`  | Green                  |
| `#062822` | `#0AC7B4`  | Teal                   |

Default nodo color: `#1F1F1F` con `#EDEDED` text.

### Estilo de aristas

Smooth-step path con an arrow marker. Default arista color: `#f8fafc`. Stroke width is thin — aristas are visually secondary a nodos.

### Formas de nodos

6 supported shapes, definido en `types/canvas.ts` como `NODE_SHAPES`. Complex shapes (diamond, hexagon, cylinder) are rendered como inline SVGs rather than CSS borders.

- `rectangle` — default general-purpose nodo
- `diamond` — decision / gateway
- `circle` — event / endpoint
- `pill` — service / process
- `cylinder` — base de datos / almacenamiento
- `hexagon` — sistema externo / límite

### Conectores

Small white circular handles, hidden by default, revealed on nodo pasar el cursor. Appear at all four sides de a nodo.

### Fondo del canvas

Componente `<Background>` de React Flow. El canvas se apoya sobre el color base de fondo.

## Biblioteca de componentes

shadcn/ui se usa sobre Tailwind. No hay un sistema de diseño personalizado. Los componentes viven en `components/ui/`. Usa la CLI de `shadcn` para añadir componentes nuevos en lugar de escribirlos desde cero.

## Patrones de layout

- Espacio de trabajo del editor: layout a pantalla completa, barra lateral flotante superpuesta a la izquierda, canvas centrado y barra lateral de IA deslizable a la derecha.
- barras laterales: floating overlay con dark semi-transparent segundo plano y subtle border.
- Modals y diálogos: centered overlay, `rounded-3xl`, dark segundo plano con backsoltar blur.
- Navbar: superior bar con dark segundo plano y bottom border.

## Iconos

Lucide React. Stroke-based icons solo — no filled variants. Icon sizes: `h-4 w-4` para inline, `h-5 w-5` para buttons, `h-8 w-8` para funcionalidad icons en estados vacíos.


## Conceptos relacionados

- [Resumen de producto](/product/overview.md) explica la experiencia de espacio de trabajo que esta UI debe soportar.
- [Estándares de código](/engineering/code-standards.md) explica las reglas de implementación para el uso de tokens y los límites de componentes.
