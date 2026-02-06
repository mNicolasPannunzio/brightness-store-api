# Brightness Store API

## 📌 Introducción

Este repositorio documenta el desarrollo completo del proyecto **Brightness Store API**, una API REST construida en Java con Spring Boot, cuyo objetivo principal fue **aprender de forma práctica y progresiva** cómo diseñar, implementar y robustecer un backend realista para un e‑commerce.

El proyecto fue desarrollado de manera iterativa, dividido en **fases numeradas (1.x, 2.x, … 8.x)**. Cada fase agregó nuevas funcionalidades, refactorizaciones, validaciones, manejo de errores y mejoras de diseño.

Un punto central de este proyecto es el **uso consciente de Inteligencia Artificial (ChatGPT)** como:

* tutor técnico
* herramienta de debugging
* apoyo conceptual
* acompañamiento en buenas prácticas

La IA **no reemplazó el razonamiento**, sino que funcionó como un par más experimentado que ayudó a:

* detectar errores
* entender por qué algo fallaba
* proponer soluciones explicadas
* mejorar la calidad del código

Este README busca dejar evidencia clara de:

* qué se construyó
* cómo evolucionó
* qué errores aparecieron
* qué aprendizajes surgieron en cada etapa

---
---

## 🧱 Fase 1 – Fundamentos del proyecto

### 🎯 Objetivo

Crear la base del proyecto backend, entendiendo la estructura general de una API REST con Spring Boot.

### 📚 Aprendizajes clave

* Creación de un proyecto Spring Boot desde cero
* Estructura típica en capas:

  * `controller`
  * `service`
  * `repository`
  * `model / entity`
* Uso de Maven como gestor de dependencias
* Primeros endpoints simples para verificar que la API levanta correctamente

### 🤖 Uso de IA

La IA se utilizó para:

* entender la estructura estándar de un proyecto Spring
* aclarar conceptos como *Controller vs Service*
* validar si la arquitectura inicial era correcta

---
---

## 🧱 Fase 2 – Entidades y persistencia

### 🎯 Objetivo

Modelar el dominio del negocio y persistir datos utilizando JPA/Hibernate.

### 📚 Aprendizajes clave

* Creación de entidades (`@Entity`)
* Uso de anotaciones JPA:

  * `@Id`, `@GeneratedValue`
  * `@Column`
* Creación de repositorios con `JpaRepository`
* Conexión a base de datos (H2 / MySQL según entorno)
* Persistencia básica de datos

### ⚠️ Problemas encontrados

* Errores de mapeo
* Campos que no se persistían como se esperaba

### 🤖 Uso de IA

* Interpretación de errores de Hibernate
* Ajuste de anotaciones
* Explicaciones sobre cómo funciona JPA internamente

---
---

## 🧱 Fase 3 – CRUD de Productos

### 🎯 Objetivo

Implementar un CRUD completo para la entidad **Producto**.

### 📚 Aprendizajes clave

* Endpoints REST:

  * `GET /productos`
  * `GET /productos/{id}`
  * `POST /productos`
* Uso correcto de `ResponseEntity`
* Separación clara de responsabilidades
* Pruebas con `curl`

### ⚠️ Problemas encontrados

* Confusión entre IDs inexistentes y errores 500
* Endpoints que funcionaban parcialmente

### 🤖 Uso de IA

* Corrección de endpoints mal mapeados
* Explicación de cuándo devolver 404 vs 500

---
---

## 🧱 Fase 4 – Pedidos y lógica de negocio

### 🎯 Objetivo

Introducir la entidad **Pedido** y comenzar a trabajar con lógica de negocio real.

### 📚 Aprendizajes clave

* Relación entre Pedido e Items
* Validaciones de negocio (pedido con items obligatorios)
* Descuento de stock al crear pedidos
* Diferencia entre validaciones técnicas y de negocio

### ⚠️ Problemas encontrados

* Pedidos que se creaban aunque no hubiera stock
* Uso incorrecto de `IllegalArgumentException`

### 🤖 Uso de IA

* Identificación de errores lógicos
* Discusión sobre excepciones personalizadas

---
---

## 🧱 Fase 5 – Excepciones personalizadas

### 🎯 Objetivo

Reemplazar excepciones genéricas por excepciones propias del dominio.

### 📚 Aprendizajes clave

* Creación de excepciones personalizadas:

  * `BadRequestException`
  * `PedidoNotFoundException`
  * `StockInsuficienteException`
  * `ResourceNotFoundException`
* Por qué **no** usar `IllegalArgumentException` en una API REST
* Relación entre excepciones y HTTP status

### 🤖 Uso de IA

* Explicaciones conceptuales profundas sobre manejo de errores
* Buenas prácticas profesionales

---
---

## 🧱 Fase 6 – GlobalExceptionHandler

### 🎯 Objetivo

Centralizar el manejo de errores de la aplicación.

### 📚 Aprendizajes clave

* Uso de `@RestControllerAdvice`
* Uso de `@ExceptionHandler`
* Construcción de respuestas de error consistentes
* Diferencia entre errores controlados y errores inesperados

### ⚠️ Problemas encontrados

* Error 500 genérico cuando faltaba mapear una excepción
* Inconsistencia en formatos de error

---
---

## 🧱 Fase 7 – Validaciones

### 🎯 Objetivo

Validar datos de entrada antes de llegar a la lógica de negocio.

### 📚 Aprendizajes clave

* Uso de anotaciones:

  * `@NotNull`
  * `@NotBlank`
  * `@Positive`
* Manejo de `MethodArgumentNotValidException`
* Diferencia entre validación y excepción

---
---

## 🧱 Fase 8.1 – – Manejo de pedidos y stock

### 🎯 Objetivo

Convertir la API en un backend robusto y predecible.

### 📚 Aprendizajes clave

* Manejo correcto de stock insuficiente.
* Respuestas HTTP coherentes.
* Uso de una clase `ApiError` para estandarizar errores
* Corrección de endpoints que devolvían 500 cuando debían devolver 404

### 📚 Aprendizaje clave sobre excepciones

Inicialmente, algunas validaciones críticas (como pedidos sin ítems o stock insuficiente) utilizaban IllegalArgumentException. Si bien esta excepción funciona a nivel de Java, se aprendió que no es adecuada para exponer errores de negocio en una API REST, ya que:
* No representa semánticamente el error
* Termina siendo capturada por el Exception.class
* Provoca respuestas 500 genéricas

**A partir de este problema, se refactorizó el código para:**
* Eliminar IllegalArgumentException
* Crear excepciones específicas como BadRequestException y StockInsuficienteException
* Mapearlas explícitamente en el GlobalExceptionHandler

Esto permitió que la API devuelva errores claros, controlados y coherentes con HTTP.

### ⚠️ Problemas encontrados

* Durante las primeras pruebas con curl, se detectó un comportamiento incorrecto: el sistema permitía crear pedidos incluso cuando la cantidad solicitada superaba ampliamente el stock disponible.
* A pesar de que la aplicación compilaba correctamente y los endpoints respondían, el error no se manifestaba como una respuesta HTTP controlada, sino que el pedido se creaba de todos modos o devolvía un error genérico 500 sin información clara.

**Este problema llevó a revisar en profundidad:**
1) La lógica de validación dentro del PedidoService
2) El tipo de excepciones que se estaban lanzando
3) Cómo eran capturadas (o no) por el GlobalExceptionHandler



### 🤖 Uso de IA

* Debugging paso a paso
* Discusión sobre diseño de errores
* Refactorizaciones guiadas

---

## 🧱 Fase 8.2 – Manejo de errores y respuestas HTTP.

### ⚠️ Problemas encontrados

Error sutil en el endpoint GET /productos/{id}
Durante la fase 8.2 se detectó un error inesperado: al consultar un producto inexistente por ID, el endpoint devolvía un 500 Internal Server Error, mientras que el listado general funcionaba correctamente.

**Esto llevó a analizar:**
* El flujo Controller → Service → Repository
* El uso de Optional
* La interacción con el GlobalExceptionHandler

**Se comprendió que:**
* Devolver directamente un Optional desde el Service delega la decisión al Controller
* Pero si en otra parte del sistema se lanza ResourceNotFoundException, esta debe estar correctamente mapeada

### 📚 Aprendizajes clave

* Centralizar errores de recursos inexistentes
* Mapear explícitamente ResourceNotFoundException
* Evitar que estos casos caigan en el handler genérico de 500

### Decisión sobre el formato de errores (ApiError) !!!

Durante el manejo de excepciones surgió la duda de por qué algunos handlers devolvían Map<String, Object> y otros un objeto ApiError.
**A través del análisis se aprendió que:**

* Map es útil para respuestas rápidas o validaciones simples
* ApiError permite un formato consistente, tipado y reutilizable

**Por este motivo, se decidió:**

* Utilizar ApiError para errores estructurales (404, conflictos, errores de dominio)
* Mantener Map solo en casos puntuales como validaciones de campos

Esta decisión mejora la mantenibilidad y claridad de la API a largo plazo.


---


## 🧱 Fase 8.3 – Manejo global de excepciones y errores HTTP

En esta fase se trabajó sobre uno de los aspectos más importantes de una API profesional: el manejo consistente de errores y excepciones.
Hasta este punto, la aplicación funcionaba correctamente en los casos válidos, pero ante errores (por ejemplo, recursos inexistentes o datos inválidos) podían aparecer respuestas inconsistentes o errores 500 genéricos, dificultando el uso de la API y el debugging.

### 🎯 Objetivos de la fase

* Centralizar el manejo de errores en un único lugar.
* Evitar el uso de IllegalArgumentException como mecanismo de control de flujo.
* Devolver respuestas HTTP claras, coherentes y predecibles.
* Diferenciar correctamente entre errores del cliente (4xx) y errores del servidor (5xx).

### 🧩 Implementación realizada
1. GlobalExceptionHandler
Se creó un GlobalExceptionHandler usando @RestControllerAdvice, que permite interceptar excepciones lanzadas desde cualquier controlador o servicio.

**Se implementaron manejadores específicos para:**

* PedidoNotFoundException → Respuesta 404 Not Found cuando un pedido no existe.

* BadRequestException → Respuesta 400 Bad Request para errores de validación de negocio.

* MethodArgumentNotValidException → Manejo de errores de validación automática (@Valid), devolviendo un mapa con los campos inválidos y sus mensajes.

* Exception (genérica) → Respuesta 500 Internal Server Error para errores inesperados, evitando exponer detalles internos.

Esto permitió eliminar respuestas genéricas poco claras y mejorar la experiencia de consumo de la API.

### 🔁 Refactor de excepciones en servicios

Se reemplazaron usos de IllegalArgumentException por excepciones propias del dominio, como:
* BadRequestException
* ResourceNotFoundException
* PedidoNotFoundException

**Esto mejoró:**
* La legibilidad del código
* La intención de cada error
* El mapeo correcto a códigos HTTP

### 🧪 Verificación con curl

Se validó el comportamiento esperado utilizando curl, comprobando que:
* Buscar un recurso inexistente devuelve 404.
* Enviar datos inválidos devuelve 400.
* Los errores de negocio no generan más respuestas 500.

### 📚 Aprendizaje clave

Una API profesional no solo debe “funcionar”, sino fallar bien.

**Esta fase reforzó la importancia de:**
* Separar errores técnicos de errores de negocio
* No usar excepciones genéricas
* Ofrecer respuestas claras y consistentes al cliente


---

## 🧱 Fase 8.4 – Definición del contrato y consistencia de la API

La Fase 8.4 **no introdujo cambios directos en el código**, sino que se enfocó en una decisión arquitectónica clave: definir el contrato de la API antes de continuar agregando funcionalidades.

Esta fase simula una práctica real en entornos profesionales, donde no todo avance se mide en líneas de código.

### 🎯 Objetivos de la fase

* Definir criterios claros para las respuestas de la API.
* Evitar inconsistencias entre endpoints.
* Preparar el proyecto para refactors controlados.
* Documentar decisiones antes de implementar cambios estructurales.

### 📐 Decisiones tomadas

Durante esta fase se definieron los siguientes lineamientos:

**Las respuestas de error deben:**
* Incluir status, error, message y timestamp
* Ser consistentes en todos los endpoints

**Las excepciones de negocio deben:**
* Mapearse a códigos HTTP correctos (400, 404, 409)
* Ser manejadas exclusivamente en el GlobalExceptionHandler

**Los controladores deben:**
* Delegar toda la lógica al servicio
* No lanzar excepciones genéricas

Se evaluó el uso de un objeto ApiError como contrato estándar para errores, en lugar de mapas sueltos, priorizando claridad y mantenibilidad.

### 📚 Aprendizaje clave

No todo commit agrega funcionalidad visible:
algunos reducen deuda técnica futura.

**Esta fase refuerza la idea de que:**
* Documentar decisiones es parte del desarrollo
* Definir contratos temprano ahorra tiempo después
* Trabajar con una IA como herramienta permite detectar problemas antes de que ocurran

### 🤖 Uso de IA como herramienta de aprendizaje

Durante esta fase, la IA se utilizó como:

* Guía para detectar errores de diseño
* Apoyo para entender flujos de excepciones
* Validación conceptual antes de escribir código
* Acompañamiento en debugging real con logs y curl

El proceso fue iterativo: probar, fallar, analizar, corregir y documentar, simulando un entorno profesional real.


---
---
## 🧱 Fases desde 8.5 a 9.3 - ⚠️Error de consistencia de la IA⚠️

**En estas fases planteadas por ChatGPT me di cuenta que me pedia realizar cosas que ya habiamos hecho previamente**

* Fase 8.5: no hay nada nuevo que hacer en la “Fase 8.5” tal como la planteé recién, porque:
* @Transactional ✔️
* Validaciones de negocio ✔️
* Excepciones correctas ✔️
* Orden lógico correcto ✔️
* Rollback probado con curl ✔️

👉 Todo eso YA está implementado y funcionando en tu código actual.

**Una vez cerrada la Fase 8.5 la IA me propuso la fase 9.0, pero esta tambien tenia inconsistencias y me pedia cosas que ya estaban implementadas en mi codigo**

### Respuesta de la IA (ChatGPT) ante la inconsistencia:

Lo que hiciste ya está un nivel más arriba de lo que yo te propuse como 9.0. De hecho, lo que mostrás corresponde a varias fases adelantadas juntas, y bien hechas.

Voy por partes, con calma y orden, como pediste 👇

1️⃣ Diagnóstico honesto de la situación
Lo que YA TENÉS implementado

Por lo que mostrás, tu proyecto ya incluye:

✔ Estados de pedido avanzados
CREADO → PAGADO → ENVIADO
CREADO → CANCELADO


Esto supera:

* la fase “confirmar pedido”

* la fase “cancelar pedido”

* la fase “transiciones simples”

✔ DTO específico para cambio de estado
PedidoEstadoRequest


Esto es nivel profesional:

* no usás strings sueltos

* validás con @NotNull

* desacoplás API ↔ dominio

✔ Lógica de máquina de estados en el Service
cambiarEstado(Long id, EstadoPedido nuevoEstado)


Y además:

* reglas claras

* excepción de negocio (BadRequestException)

* método transaccional

👉 Esto no es fase 9.0 básica
👉 Esto es 9.2 / 9.3 tranquilamente

**2️⃣ Dónde estuvo la inconsistencia (mea culpa)**

Yo asumí este orden lógico:

8.x → 9.0 confirmar → 9.1 cancelar → 9.2 estados


**Pero vos ya habías implementado directamente:**

Estados + transiciones + DTO + validación


O sea:

* saltaste fases intermedias (bien)

* el código no está mal

* el problema fue el orden conceptual, no tu implementación

👉 Gracias por frenar y pedir revisión antes de seguir. Eso es exactamente lo que hace alguien que quiere un proyecto serio.

3️⃣ Reordenamiento REAL de las fases (desde ahora)

Voy a reordenar las fases según el código real que ya existe, no según un temario teórico.

✅ Fases ya COMPLETADAS (aunque no todas estén en el README)

8.1 – Validaciones y excepciones

8.2 – GlobalExceptionHandler

8.3 – ResourceNotFound + errores consistentes

8.4 – Documentación (README parcial)

8.5 – Excepciones específicas (PedidoSinItems, Stock, Cantidad)

9.0 – Estados de pedido

9.1 – Transiciones válidas

9.2 – DTO para cambio de estado

9.3 – Reglas de negocio en Service

### ⚠️Evidencia que la IA no siempre puede encargarse de todo un proyecto de este calibre. Tengo que estar atento a cada respuesta y comprobar cada dato por mi cuenta para mantener la consistencia y seriedad del proyecto.⚠️

---
---
## 🧱 Fase 9.4 – Cancelación de pedidos y reversión de stock

**En esta fase se implementó la cancelación de pedidos, incorporando reglas de negocio reales y consistentes con un sistema de e-commerce profesional.**

### 🧩 Funcionalidad implementada:
* Se agregó un endpoint PATCH para cancelar pedidos existentes.
* La cancelación solo es válida si el pedido se encuentra en estado CREADO.
* Al cancelar un pedido: 
   * Se revierte el stock de cada producto involucrado.
   * El pedido pasa a estado CANCELADO.
* Se validaron transiciones de estado inválidas, devolviendo errores controlados (400 Bad Request).

### ⚠️ Problema real detectado

Durante las pruebas, la cancelación funcionaba correctamente a nivel lógico y de base de datos, pero el endpoint devolvía un error 500 al intentar serializar la respuesta JSON.

El problema se debía a una **relación bidireccional entre Pedido y PedidoItem**, que generaba una recursión infinita durante la serialización (pedido → items → pedido → …).

**Solución aplicada:**
* Se identificó el problema como un bug de serialización, no de lógica.
* Se corrigió utilizando @JsonIgnore en la referencia inversa (PedidoItem → Pedido), evitando que Jackson vuelva a serializar el objeto padre.
* Esta solución permitió:
   * Respuestas JSON correctas
   * Eliminación del error 500
   * Mantener intacta la lógica de negocio y JPA

### 📚 Aprendizajes clave

* Importancia de no exponer entidades JPA directamente sin control.
* Manejo de relaciones bidireccionales en APIs REST.
* Diferenciación clara entre:
   * feature (nueva funcionalidad)
   * fix (corrección de bug)
   * refactor (mejora interna sin cambio de comportamiento)

Esta fase marcó un punto importante en la comprensión de problemas reales de backend que no suelen aparecer en ejemplos académicos.

---
---




---
---


## 📌 Conclusión

Este proyecto no solo resultó en una API funcional, sino en un **proceso de aprendizaje profundo** sobre:

* arquitectura backend
* diseño de APIs REST
* manejo profesional de errores
* uso responsable de inteligencia artificial como herramienta de trabajo

**El desarrollo por fases permitió:**

* cometer errores
* entenderlos
* corregirlos conscientemente

### 🤖 Uso de IA durante el desarrollo

Este proyecto fue desarrollado utilizando Inteligencia Artificial como **herramienta activa de aprendizaje**, no como generador automático de código.

**La IA fue utilizada para:**

* Analizar errores reales surgidos durante pruebas con curl
* Razonar sobre comportamientos inesperados
* Discutir decisiones de diseño (excepciones, handlers, arquitectura)
* Comprender conceptos de Spring Boot y REST desde la práctica

**El desarrollo fue iterativo:** El código se probó, falló, se corrigió y se refactorizó, siempre con el objetivo de entender el porqué de cada decisión.


### Este README busca dejar evidencia del proceso completo, incluyendo errores, dudas y correcciones, como parte fundamental del aprendizaje.


## ⚠️ Este README seguirá creciendo a medida que el proyecto avance.