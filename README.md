# intercorpretail-challenge
Repositorio que actua como solución del desafio técnico propuesto por Intercorp Retail.

URL publica de la app:

http://intercorpretailchallenge-env.eba-fzrhkgvt.us-east-1.elasticbeanstalk.com/

# Características del proyecto:
- Proyecto Maven hosteao en AWS con integración de SpringBoot que utiliza el administrador de base de datos en memoria H2 (DBMS), y a su vez,integración de SWAGGER. La versión de Java utilizada en este proyecto es la 1.8.

# Estructura
##### package com.intercorpretail.challenge.controller
Capa que contiene toda la lógica del controlador en donde se implementan y gestionan las APIs de la aplicación. Cada API tiene su información detallada en el Javadoc.

###### Las APIs creadas en esta capa son:

-/creaCliente POST API que crea una entidad de tipo 'Cliente' y la persiste en la base de datos. La API recibe un objeto json como body de la solicitud para crear el objeto 'Cliente'.
- Un ejemplo del objeto de tipo Json que requiere el body de la solicitud:
```sh
    {
    "nombre":"Andres",
    "apellido" : "Lizarraga",
    "fechaNacimiento" : "1990-01-01",
    "edad": "30"
    }
```        
- En donde los campos:  
       -"nombre" : Es el nombre del cliente a persistir. Este campo es de tipo de dato String. Este campo no puede ser null.  
	   -"apellido" : Es el apellido del cliente a persistir. Este campo es de tipo de dato String. Este campo no puede ser null.  
	   -"fechaNacimiento" : Es la fecha de nacimiento del cliente a persistir. Este campo es de tipo de dato Date. Es excluyente que este campo de tipo de fecha sea enviado usando el formato "yyyy-MM-dd". Este campo no puede ser null.  
	   -"edad": Es la edad del cliente a persistir. Este campo es de tipo de dato Integer. Es excluyente que este campo de tipo Integer sea enviado usando los formatos de ejemplo: "30" o 30. Este campo no puede ser null.  
	   
   De ejecutarse la solicitud de manera correcta, endpoint responde con un objeto json con el siguiente formato: 
```sh
   {
    "status": "OK",
    "mensaje": "Se creó un nuevo objeto cliente correctamente.",
    "error": "0"
}
```    
-/kpideclientes GET API que realiza el calculo del promedio de las edades de los clientes persistidos en la base de datos, así como también, la desviación estandar de las edades. Si existen objetos de tipo clientes persistidos en la base de datos, el endpoint responde con un objeto json con el siguiente formato:
 ```sh
 {
    "status": "OK",
    "mensaje": "Se realizaron los calculos correctamente.",
    "error": "0",
    "promedioClientes": "52.0",
    "desviacionClientes": "2.5495097567963922"
}
```    
-/listclientes GET API que retorna la lista de todos los clientes persistidos en la base de datos, a su vez mostrando un campo adicional, el correspondiente a la fecha estimada de muerte de cada uno. La fecha estimada de muerte es calculada tomando como referencia la edad de mortalidad promedio de Peru al 2021, la cual es de 76 años.  Si existen objetos de tipo clientes persistidos en la base de datos, el endpoint responde con un objeto json con el siguiente formato:
 ```sh
 [
    {
        "nombre": "Daniel",
        "apellido": "Rodriguez",
        "edad": "31",
        "fechaNacimiento": "1989-12-31",
        "fechaProbableMuerte": "2065-12-31"
    },
    {
        "nombre": "Andres",
        "apellido": "Lizarraga",
        "edad": "36",
        "fechaNacimiento": "1994-12-31",
        "fechaProbableMuerte": "2070-12-31"
    }
]
```    
##### package com.intercorpretail.challenge.config
Capa que contiene las clases de configuracion necesaria para la implementación de SWAGGER. 

##### package com.intercorpretail.challenge.model
Capa que contiene la definición de la entidad 'Cliente' la cual representa al objeto del negocio cliente.  
Clase 'Cliente'  
 Representa la entidad 'Cliente'. Dicha entidad esta conformada por los campos:  
nombre: Representa el nombre del cliente. Este campo es de tipo de dato String. Este campo no puede ser null.  
apellido: Representa el apellido del cliente. Este campo es de tipo de dato String. Este campo no puede ser null.  
edad: Representa la edad del cliente. Este campo es de tipo Integer. Este campo no puede ser null.  
fechaNacimiento: Representa la edad del cliente. Este campo tipo de dato es Date. Este campo no puede ser null y únicamente admite el formato: "yyyy-MM-dd".  
fechaProbableMuerte: Representa la edad probable de muerte del cliente. Este valor es calculado tomando como referencia la edad de mortalidad de Peru al 2021 y se toma en cuenta la fecha de nacimiento del cliente.  
esperanzaVidaPeru: este campo es de tipo de dato int, es una variable estatica que representa la edad de mortalidad promedio de Peru al 2021.  

Clase 'ApiResponse' 
  Representa un objeto que mapea la respuesa del endpoint '/creaCliente'. 
  
Clase 'CalculationApiResponse'
  Representa un objeto que mapea la respuesta del endpoint '/kpideclientes'

Clase 'ClientesApiResponse'
  Representa un objeto que mapea la respuesta del endpoint '/listclientes'
  
##### package com.intercorpretail.challenge.repository
Capa que contiene la interfaz que implementara la entidad 'Cliente' para establecer la conexión a la capa de persistencia.

Clase 'DaoCliente'
 Interfaz que extiende de la librería Java Persistence API y establece la conexión de la entidad 'Cliente' para realizar diferentes operaciones con la base de datos.

##### package com.intercorpretail.challenge.service
Capa que contiene la interfaz que representa el servicio que realizara las diferentes operaciones con la entidad 'Cliente'.

##### package com.intercorpretail.challenge.service.impl
Capa que contiene la implementación de la interfaz que representa el servicio que realizara las diferentes operaciones con la entidad 'Cliente'.

# ¿Cómo ejecutar la aplicación?
- Si ya se descargó el proyecto y se importó a un nuevo workspace utilizando un IDE, como por ejemplo, Eclipse, solo basta con dirigirse al paquete 'com.intercorpretail.challenge', hacer click derecho en la clase 'ChallengeApplication.java', elegir la opción 'run as' , y a continuación seleccionar 'Java Application'.
- Si se desea ejecutar la aplicacion desde la consola de Windows, basta con dirigirse al directorio principal donde se encuentra contenido el proyecto desde el cmd y ejecutar el comando: mvnw spring-boot:run.

# ¿Con quién me comunico en caso de consultas?
- Contactarse con el dueño y administrador del proyecto, Andrés Lizarraga.
- Correo electrónico: andresalv7@gmail.com