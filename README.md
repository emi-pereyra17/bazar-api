# 🛒 Bazar API - Proyecto Final Spring Boot

Este es un proyecto backend desarrollado con **Spring Boot**. Se trata de una API RESTful para la gestión de un bazar, incluyendo operaciones CRUD sobre entidades como productos, clientes, facturas y más.

---

## 🚀 Demo en Producción

👉 [Explorar Swagger UI (Documentación de la API)](https://bazar-api-production-8d6f.up.railway.app/swagger-ui/index.html)

---

## ⚙️ Tecnologías utilizadas

- ✅ Spring Boot
- ✅ Spring Data JPA (Hibernate)
- ✅ MySQL (Railway)
- ✅ Maven
- ✅ Lombok
- ✅ Swagger / OpenAPI 3
- ✅ Validaciones con `@Valid`
- ✅ Arquitectura por capas (Controller - Service - Repository)
- ✅ Manejo global de errores con `@ControllerAdvice`

---

## 🗃️ Endpoints disponibles

La documentación completa de los endpoints se encuentra en Swagger:

📎 [https://bazar-api-production-8d6f.up.railway.app/swagger-ui/index.html](https://bazar-api-production-8d6f.up.railway.app/swagger-ui/index.html)

Ejemplos:
- `GET /productos`
- `POST /clientes/create`
- `PUT /clientes/edit/{id}`
- `DELETE /productos/delete/{id}`
  *(entre otros)*

---

## 🛠️ Configuración de base de datos

La base de datos se encuentra alojada en **Railway**, conectada automáticamente mediante variables de entorno en `application.properties`.

```properties
spring.datasource.url=jdbc:mysql://${MYSQLHOST}:${MYSQLPORT}/${MYSQLDATABASE}
spring.datasource.username=${MYSQLUSER}
spring.datasource.password=${MYSQLPASSWORD}

## 👨‍💻 Autor

- Emiliano Pereyra  
- 📧 [Email: emiacebal2012@hotmail.com]  
- 💼 [LinkedIn: www.linkedin.com/in/emiliano-pereyra-52164a29b]



