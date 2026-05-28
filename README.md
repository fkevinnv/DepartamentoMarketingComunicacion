#  Departamento de Marketing y Comunicación

Aplicación web empresarial híbrida diseñada para la gestión interna de publicaciones, campañas informativas y canales de atención a solicitudes del equipo de Marketing.

---

##  Arquitectura del Proyecto

El sistema está dividido en dos grandes bloques modulares que se comunican entre sí y comparten recursos de diseño centralizados:

1. **Entorno Java**
   * **Servidor:** Apache Tomcat v9.0 (Puerto `8080`).
   * **Tecnologías:** Servlets (Controladores), DAOs (Acceso a Datos), JSPs (Vistas Dinámicas).
   * **Función:** Control total del flujo CRUD de las publicaciones internas del departamento y renderizado de la interfaz de administración.

2. **Módulo de Campañas en PHP**
   * **Servidor:** Servidor web integrado / Docker contenedor (Puerto `6644`).
   * **Tecnologías:** PHP Estricto, `pg_connect`.
   * **Función:** Procesamiento de solicitudes de contacto externas y almacenamiento seguro en el motor relacional.

3. **Base de Datos**
   * **Motor:** PostgreSQL (pgAdmin).
   * **Tablas principales:** `publicaciones` y `formulario_php`.

---

##  Estilos del proyecto 

Para mantener la consistencia de marca (colores corporativos `#1a73e8` y `#0d47a1`), el diseño visual se centraliza desde el servidor Java. El módulo PHP consume directamente las hojas de estilo mediante enlaces absolutos:

* **Hoja de Estilos Principal:** `http://localhost:8080/DepartamentoMarketingComunicacion/css/styles.css`
* **Validaciones JavaScript:** Sistema nativo implementado en `js/alertas.js` para la interceptación de formularios mediante cuadros de diálogo de confirmación (`confirm()`).

---

### 1. Clonar el repositorio y acceder a la rama de desarrollo
```bash
git clone <url-del-repositorio>
git checkout damx
