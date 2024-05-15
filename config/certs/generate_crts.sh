# generate the private key and csr
openssl req -new -newkey rsa:2048 -nodes -keyout backend.key -out backend.csr

# generate crt with the key and the csr
openssl x509 -req -days 36500 -in backend.csr -signkey backend.key -out backend.crt

# convert crt to pem
openssl x509 -in backend.crt -out backend.pem