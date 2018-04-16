# magneto-dna-analyzer
Ejercicio de prueba de Mercado Libre

Prerequisitos
-Para instalación:
* Java 8
* Maven
-Para ejecucion:
* Java 8
* Instancia de mongodb

Instalación en el lugar donde baje el código puede ejecutar el .bat o .sh (según su entorno) eso crea una instancia de esta aplicación.

Instalación en un entorno:
- Correr el comando mnv package para generar el lote el .jar
- Llevarlo a su entorno y ejecutarlo. Al ser una aplicación preparada con spring boot cuenta con un tomcat embebido que escucha en el puerto 8080 por lo que tendrá que tenerlo libre al momento de la ejecución.


Métodos de API

[POST] /mutant
Body param Json con el campo dna con un array de String que contienen únicamente las letras A T C G
Ejemplo:
{ "dna":["ATACGA","CCGTGC","TGATGT","AGAAGT","CTATAA","TCACTG"] }

Respuesta 200 = Mutante
Respuesta 403 = Humano
Respuesta 500 = Error


[GET] /stats
Muestra y compara los análisis realizados
Ejemplo de respuesta:
{
ratio: 2.5,
count_mutant_dna: 5,
count_human_dna: 2
}
Respuesta 200 = Informe 
Respuesta 404 = No hay estadística

[GET] /healthCheck
Salud de la aplicación.
Respuesta 200 = si esta viva. Muestra el timestamp del servidor
