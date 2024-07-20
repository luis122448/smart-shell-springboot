#!/bin/bash

# Crenado directorio para certificados SSL
mkdir -p key

# Creando archivo .env
touch .env

# Configuracion del SSL
#export COUNTRY=PE
#export STATE=LIMA
#export CITY=LIMA
#export ORGANIZATION=''
#export ORGANIZATIONAL_UNIT=''
#export COMMON_NAME=''
#export EMAIL=''

# Generando certificados SSL
#openssl req -new -x509 -keyout ./key/key.pem -out ./key/cert.pem -days 365 -nodes -subj "/C=$COUNTRY/ST=$STATE/L=$CITY/O=$ORGANIZATION/OU=$ORGANIZATIONAL_UNIT/CN=$COMMON_NAME/emailAddress=$EMAIL"

# Generando un certificado PKCS12 en base a una llave publica y privada
#openssl pkcs12 -export -out luis122448.p12 -name "luis122448" -inkey ./key/key.pem -in ./key/cert.pem

# Mover certificado generado, al directorio al proyecto
#cp ./key/luis122448.p12 ./src/main/resources/key/luis122448.p12

# Configuracion de variables de entorno
export $(grep -v '^#' .env | xargs)

# Archivo de propiedades
archivo="./src/main/resources/application-pdn.properties"

# Reemplazar los valores
sed -i "s/\${POSTGRES_HOST}/$POSTGRES_HOST/g" "$archivo"
sed -i "s/\${POSTGRES_DATABASE}/$POSTGRES_DATABASE/g" "$archivo"
sed -i "s/\${POSTGRES_USERNAME}/$POSTGRES_USERNAME/g" "$archivo"
sed -i "s/\${POSTGRES_PASSWORD}/$POSTGRES_PASSWORD/g" "$archivo"
sed -i "s/\${MONGO_HOST}/$MONGO_HOST/g" "$archivo"
sed -i "s/\${MONGO_DATABASE}/$MONGO_DATABASE/g" "$archivo"
sed -i "s/\${MONGO_USERNAME}/$MONGO_USERNAME/g" "$archivo"
sed -i "s/\${MONGO_PASSWORD}/$MONGO_PASSWORD/g" "$archivo"
sed -i "s/\${REDIS_HOST}/$REDIS_HOST/g" "$archivo"
sed -i "s/\${REDIS_USERNAME}/$REDIS_USERNAME/g" "$archivo"
sed -i "s/\${REDIS_PASSWORD}/$REDIS_PASSWORD/g" "$archivo"

echo "Valores reemplazados correctamente en $archivo"
