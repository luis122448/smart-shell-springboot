# Crenado directorio para certificados SSL
mkdir -p certs

# Configuracion del SSL
export COUNTRY=PE
export STATE=LIMA
export CITY=LIMA
export ORGANIZATION='luis122448'
export ORGANIZATIONAL_UNIT='luis122448'
export COMMON_NAME='luis122448'
export EMAIL='luis122448@gmail.com'

# Generando certificados SSL
openssl req -new -x509 -keyout ./certs/key.pem -out ./certs/cert.pem -days 365 -nodes -subj "/C=$COUNTRY/ST=$STATE/L=$CITY/O=$ORGANIZATION/OU=$ORGANIZATIONAL_UNIT/CN=$COMMON_NAME/emailAddress=$EMAIL"

openssl pkcs12 -export -out luis122448.p12 -name "luis122448" -inkey key.pem -in cert.pem
