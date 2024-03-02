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

## Despliegue en Producción

Para el despliegue en producción se ha utilizado Docker y Docker Compose, puede revisar el archivo docker-compose.yml para conocer los detalles de la configuración.
Asimismo no se olvide de modificar las variables de entono, en asi archivo .env

1. **Ejecutar el script de despliegue**
  
    ```bash
        sudo bash deploy.sh
    ```

## Contribuciones
Las contribuciones son bienvenidas. Siéntete libre de mejorar este proyecto, agregar nuevas características o corregir problemas identificados. Para contribuir, crea un Pull Request o abre un Issue.

## Licencia
Este proyecto está bajo la licencia MIT License.
