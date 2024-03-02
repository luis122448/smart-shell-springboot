![Logo del Projecto](./resources/logo.png)

# Despliegue del Backend de Smart Shell

Repositorio con el código fuente y los scripts necesarios para el despliegue del servidor de API REST del proyecto Smart Shell.

## Características
- Implementa doble capa de seguridad con Spring Security y JWT.
- API RESTFULL con autenticación JWT y Hateoas.
- API REST para subida y descarga de archivos y reportes.
- Optimización de caching con Redis.
- Gestión de dependencias con Maven.
- Conexión simultanea a tres bases de datos Postgres, Mongo y Redis.
- Automatización del despliegue con Docker y Docker-Compose.

## Repositorios Relacionados

### Repositorio Actual
- [Smart-Shell-SpringBoot](https://github.com/luis122448/smart-shell-springboot)

### Repositorios Relacionados

Repositorio referido al FRONTEND de la aplicación.
- [Smart-Shell-Angular](https://github.com/luis122448/smart-shell-angular)

Repositorio relacionado con la automatización del despliegue de las Bases de Datos.
- [Smart-Shell-Bash](https://github.com/luis122448/smart-shell-bash)

Repositorios relacionados con las bases de datos del proyecto.
- [Smart-Shell-Postgres](https://github.com/luis122448/smart-shell-postgres)
- [Smart-Shell-Mongo](https://github.com/luis122448/smart-shell-mongo)
- [Smart-Shell-Redis](https://github.com/luis122448/smart-shell-redis)

## Configuración del Entorno

1. **Clonar el Repositorio**
    ```bash
        git clone https://github.com/luis122448/smart-shell-springboot.git
    ```

2. **Ingresar al directorio del proyecto**

    ```bash
        cd smart-shell-springboot
    ```

3. **Ejecutar el script de instalación**
  
    ```bash
        sudo bash dev-install.sh
    ```

4. **Defina las credenciales en el archivo .env**

    ```bash
        nano .env
    ```

    ```env
        POSTGRES_USERNAME=''
        POSTGRES_PASSWORD=''
        POSTGRES_DATABASE=''
    ```

5. **Crear (si no existe) el network**

    ```bash
        sudo docker network create smart-shell-net
    ```

## Despliegue en Producción

Para el despliegue en producción se ha utilizado Docker y Docker Compose, puede revisar el archivo docker-compose.yml para conocer los detalles de la configuración.
Asimismo no se olvide de modificar las variables de entono, en asi archivo .env

1. **Ejecutar el script de despliegue**
  
    ```bash
        sudo bash deploy.sh
    ```

## Revisión del Despliegue

1. **Verificar logs y backups**
    Verificar los registros y respaldos generados durante el arranque del contenedor dentro del directorio de tu proyecto:

    ```bash
        smart-shell-postgres/
        ├── app/
        │   └── smart-shell.jar
        ├── logs/
        │   ├── init-2023-12-02.log
        │   └── log-2023-12-02.log
        ├── backups/
        │   └── backup-20231202_134657.sql
        └── ...
    ```

## Contribuciones
Las contribuciones son bienvenidas. Siéntete libre de mejorar este proyecto, agregar nuevas características o corregir problemas identificados. Para contribuir, crea un Pull Request o abre un Issue.

## Licencia
Este proyecto está bajo la licencia MIT License.
