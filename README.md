# Simple Commerce - A spring boot example e-commerce backend project


## Requirements

- Java 17 JDK
- MySQL 8+ or MariaDB Server available on `localhost:3306`
  - Database should be created with `CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci`


## Environment Variables

Please set the following environment variables:

- `SC_ADMIN_PASS=12345678`
- `SC_DB_NAME=sc_base`
- `SC_DB_USER=sc_user_1`
- `SC_DB_PASS=12345678`
- `SC_JWT_EXPIRY=36000000`
- `SC_JWT_SECRET=lDO3/35D/5UidbfvG0oZlyQyMIdY0IA5FHjHvZwVGHM=`

**Bash:**
```sh
export SC_SERVER_PORT="8101"
export SC_ADMIN_PASS="1234"
export SC_DB_NAME="scdb"
export SC_DB_PASS="12345678"
export SC_DB_USER="root"
export SC_JWT_EXPIRY="36000000"
export SC_JWT_SECRET="lDO3/35D/5UidbfvG0oZlyQyMIdY0IA5FHjHvZwVGHM="
```

**Powershell:**
```ps
$env:SC_SERVER_PORT="8101"
$env:SC_ADMIN_PASS='1234'
$env:SC_DB_NAME='scdb'
$env:SC_DB_PASS='12345678'
$env:SC_DB_USER='root'
$env:SC_JWT_EXPIRY='36000000'
$env:SC_JWT_SECRET='lDO3/35D/5UidbfvG0oZlyQyMIdY0IA5FHjHvZwVGHM='
```

**CMD:**
```cmd
setx SC_SERVER_PORT="8101"
setx SC_ADMIN_PASS "1234"
setx SC_DB_NAME "scdb"
setx SC_DB_PASS "12345678"
setx SC_DB_USER "root"
setx SC_JWT_EXPIRY 36000000
setx SC_JWT_SECRET "lDO3/35D/5UidbfvG0oZlyQyMIdY0IA5FHjHvZwVGHM="
```


## Starting the Application

- clone repo: `git clone https://github.com/samax-dx/simple-commerce.git`
- cd into simple-commerce: `cd simple-commerce`.
- start application:
  - Bash: `./mvnw spring-boot:run`
  - PowerShell: `.\mvnw spring-boot:run`
  - CMD: `mvnw spring-boot:run`


## Features
- Secure user registration and login system ✔️
- Product listing with descriptions and images ⏳
- Shopping cart functionality with add, remove, and update features ❌
- Order processing system with confirmation and tracking ❌
- User account management for viewing order history and tracking orders ❌


## License

This project is licensed under the terms of the MIT license.


## Contributing

If you would like to contribute, please fork the repository and use a feature branch. Pull requests are warmly welcome.
