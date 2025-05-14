# 📌 Instrucciones

[← Regresar](../../README.md) <br>

---
## 📎 Variables
- `REALM_NAME`: poc-management
- `TOKEN_EXPIRATION`: 30'
- `USER_NAME`: admin
- `USER_PASSWORD`: admin
- `ROLE_NAME`: partners
- `CLIENT_NAME`: front-poc-app

--- 


Acceder a la web `http://localhost:8091`, seleccionar la opción `Administration Console` e iniciar sesión con las credenciales (username=`admin`, password=`admin`).

> Crear un nuevo realm y asígnele el nombre `$REALM_NAME`.

> **Realm Settings**: Seleccionar el tab `Keys` y ubicar la llave pública `RS256`. 📌 Reservar esta llave para que sea utilizada en su backend.


> **Realm Settings**: Seleccionar el tab `Tokens`, ubicar la propiedad `Access Token Lifespan` y cambiar el tiempo de expiración a `$TOKEN_EXPIRATION`.

> **User**: Crear un nuevo user con username=`$USER_NAME`, presionar el botón `save`. A continuación, seleccionar el tab`Credentials` y asignar password=`$USER_PASSWORD` y temporary=`off`.

> **Roles**: Crear un nuevo rol (rolename=`$ROLE_NAME`).

> **User**: Seleccionar el tab `Role Mappings` y agregue el rol previamente creado `$ROLE_NAME` .

> **Clients**: Crear un nuevo cliente (clientid=`$CLIENT_NAME`, client-protocol=`openid-connect`)

> **Clients**: Ubicar la propiedad y actualizarla con `Valid Redirect URIs`=`*`.