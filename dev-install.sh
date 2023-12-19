# Crenado directorio para certificados SSL
mkdir -p key

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
openssl pkcs12 -export -out luis122448.p12 -name "luis122448" -inkey ./key/key.pem -in ./key/cert.pem

# Mover certificado generado, al directorio al proyecto
cp ./key/luis122448.p12 ./src/main/resources/key/luis122448.p12