#!/bin/bash

# Guardar valores actuales de las variables de entorno
backup_POSTGRES_HOST=$POSTGRES_HOST
backup_POSTGRES_DATABASE=$POSTGRES_DATABASE
backup_POSTGRES_USERNAME=$POSTGRES_USERNAME
backup_POSTGRES_PASSWORD=$POSTGRES_PASSWORD
backup_MONGO_HOST=$MONGO_HOST
backup_MONGO_DATABASE=$MONGO_DATABASE
backup_MONGO_USERNAME=$MONGO_USERNAME
backup_MONGO_PASSWORD=$MONGO_PASSWORD
backup_REDIS_HOST=$REDIS_HOST
backup_REDIS_USERNAME=$REDIS_USERNAME
backup_REDIS_PASSWORD=$REDIS_PASSWORD

# Archivo de propiedades
archivo="./src/main/resources/application-pdn.properties"

# Restaurar los valores originales
sed -i "s/$backup_POSTGRES_HOST/\${POSTGRES_HOST}/g" "$archivo"
sed -i "s/$backup_POSTGRES_DATABASE/\${POSTGRES_DATABASE}/g" "$archivo"
sed -i "s/$backup_POSTGRES_USERNAME/\${POSTGRES_USERNAME}/g" "$archivo"
sed -i "s/$backup_POSTGRES_PASSWORD/\${POSTGRES_PASSWORD}/g" "$archivo"
sed -i "s/$backup_MONGO_HOST/\${MONGO_HOST}/g" "$archivo"
sed -i "s/$backup_MONGO_DATABASE/\${MONGO_DATABASE}/g" "$archivo"
sed -i "s/$backup_MONGO_USERNAME/\${MONGO_USERNAME}/g" "$archivo"
sed -i "s/$backup_MONGO_PASSWORD/\${MONGO_PASSWORD}/g" "$archivo"
sed -i "s/$backup_REDIS_HOST/\${REDIS_HOST}/g" "$archivo"
sed -i "s/$backup_REDIS_USERNAME/\${REDIS_USERNAME}/g" "$archivo"
sed -i "s/$backup_REDIS_PASSWORD/\${REDIS_PASSWORD}/g" "$archivo"

echo "Archivo $archivo restaurado a su estado original"
