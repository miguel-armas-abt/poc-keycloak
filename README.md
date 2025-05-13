> 📌 Utilice una shell compatible con Unix e instale `yq` y `jq` para ejecutar los scripts `.sh`

---

## 📋 Pre requisitos
Configurar las variables `MAVEN_HOME`, `MAVEN_REPOSITORY`, `JAVA_HOME` en el archivo [variables.env](scripts/variables.env).

## ▶️ Menú de opciones
> 1. Iniciar Keycloak (up)
> 2. Configurar Keycloak mediante scraping
> 3. Detener Keycloak (stop)
> 4. Eliminar Keycloak (delete)
> 5. Recrear contenedor (recreate) - *Requiere el nombre del contenedor como argumento*
>
> ```shell script
> cd ./scripts
> ./main.sh
> ```

---

[chromedriver versions](https://googlechromelabs.github.io/chrome-for-testing/#stable)