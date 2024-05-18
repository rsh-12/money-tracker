# generate the private key and csr
openssl req -new -newkey rsa:2048 -nodes -keyout "$(dirname "$0")"/backend.key -out "$(dirname "$0")"/backend.csr

# generate crt with the key and the csr
openssl x509 -req -days 36500 -in "$(dirname "$0")"/backend.csr -signkey "$(dirname "$0")"/backend.key -out "$(dirname "$0")"/backend.crt

# convert crt to pem
openssl x509 -in "$(dirname "$0")"/backend.crt -out "$(dirname "$0")"/backend.pem