# Visor de referencia OKF

Este documento explica cómo generar y abrir un visor visual para el bundle de contexto ubicado en `/context-okf`.

El visor sirve para explorar la estructura del contexto, ver relaciones entre documentos y detectar si el bundle se está volviendo difícil de navegar.

No es obligatorio usarlo para trabajar en el día a día, pero puede ser útil cuando se reorganiza el contexto o cuando alguien nuevo necesita entender la estructura general.

## Herramienta de referencia

La herramienta de referencia de OKF está en el repositorio `knowledge-catalog` de Google Cloud Platform:

```text
https://github.com/GoogleCloudPlatform/knowledge-catalog/tree/main/okf
```

Dentro de ese repositorio hay ejemplos de bundles OKF y código para generar un visor HTML.

## Generar el visor

Desde una copia local del repositorio de herramientas OKF, ejecuta un comando parecido a este:

```bash
python -m reference_agent visualize \
  --bundle /ruta/a/tu/proyecto/context-okf \
  --out /ruta/a/tu/proyecto/context-okf/viz.html \
  --name "Contexto del proyecto"
```

El resultado será un fichero HTML generado en:

```text
/context-okf/viz.html
```

## Abrir el visor

Puedes abrir `viz.html` directamente en el navegador.

Si el navegador bloquea alguna funcionalidad por estar abriendo un fichero local, sirve la carpeta con un servidor local sencillo:

```bash
cd /ruta/a/tu/proyecto/context-okf
python -m http.server 8080
```

Después abre esta URL en el navegador:

```text
http://localhost:8080/viz.html
```

## Cuándo regenerarlo

Regenera el visor cuando cambie de forma significativa la estructura del contexto.

Por ejemplo:

- se añaden ficheros nuevos;
- se renombran o mueven documentos;
- cambian enlaces importantes;
- se reorganizan secciones completas;
- se quiere revisar si la navegación sigue siendo clara.

## ¿Se debe subir `viz.html` al repositorio?

Por defecto, recomendamos no subir `viz.html` al repositorio, porque es un fichero generado.

Lo habitual sería versionar los Markdown de `/context-okf` y dejar que cada persona genere el visor cuando lo necesite.

En ese caso, añade esto a `.gitignore`:

```gitignore
context-okf/viz.html
```

Puede tener sentido subirlo si el equipo quiere mantener una instantánea estática del visor, pero entonces hay que acordar cuándo se regenera y quién se encarga de mantenerlo al día.
