----------------------------------------------------------------------------------------------

# Index / Indice

1. English manual
2. Manual en español

----------------------------------------------------------------------------------------------

# DDBB manager with JavaFx

## Authors

- Samuel Yúnez Hernández (Masu17)
- Ignacio Barrios Hernández (idevcm)

# Description

This is a desktop application that manages CRUD operations within a MySQL relational database.
We have made the program with dynamic functionalities so that it can work with any SQL document that the user wants to
introduce.

### What was our motivation?

Our motivation was born with the purpose of learning how to use GUI development frameworks, in this case JavaFx, as well
to familiarize ourselves with the use of
databases in the cloud. In addition, it strengthened our organizational skills and teamwork.

### What was our objective?

The objective was to create a desktop application that would allow CRUD operations within a database with a friendly and
dynamic graphical user interface. This application should allow the user to choose whether they want
to work with a local or cloud database, as well as allow them to choose the SQL file they want to use.

## Recognition and contributions

Thanks to this project, carried out in the 1st year of the higher cycle of Multiplatform Application Development, we
received distinguished mentions from our programming teacher, as well as the highest grade.

### Acknowledgments

We would like to thank our programming
teacher, [Josè Neftalí Guillén Peréz](https://www.linkedin.com/in/jos%C3%A8-neftal%C3%AD-guill%C3%A9n-p%C3%A9rez-466567100/),
for his determination and dedication when teaching us how to program. In September 2022 we had hardly any programming
knowledge and thanks to his dedication and good work we have obtained a very high level of abstraction, as well as
logical structuring. He has also provided us with the necessary tools to be able to continue learning on our own and to
be able to continue growing as programmers.

## Technologies used

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Cloud](https://img.shields.io/badge/Cloud-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![LINUX](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)

### Programming languages

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

We have used Java as the main programming language, since it is the one we have learned in the first year of the higher
cycle of Multiplatform Application Development. We have also used it because it is a multiplatform language, which
allows us to create applications that can be executed on any operating system.

![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

We have used MySQL as the main database manager, since it is the one we have learned in the first year of the higher
cycle of Multiplatform Application Development. We have also used it because it is a multiplatform database manager,
which allows us to create applications that can be executed on any operating system.

### Frameworks

![JavaFx](https://img.shields.io/badge/JavaFX-5382A1?style=for-the-badge&logo=Java&logoColor=white)

We used JavaFx as the main framework for creating the graphical user interface, since it is the one we learned in the
first year of the higher cycle of Multiplatform Application Development. We have also used it because it is a
multiplatform framework, which allows us to create applications that can be executed on any operating system.

### Tools

![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

We have used IntelliJ IDEA as the main IDE for the development of the application, since it is the one we have learned
in the first year of the higher cycle of Multiplatform Application Development. We noticed that it is a very complete
IDE, which allows us to develop applications in a very comfortable way. Also, it allow us to use the JavaFx framework
in a very simple way.

## Possible improvements

This program was completed within one week, so it was not possible to perform a complete optimization of the code. We 
are aware that many improvements could be made, as well as in the graphical user interface, but due to
the short time available we have not been able to make the improvements that we would like to make, and 
we will be very grateful if you use our program and, if possible, point us improvements that could be made or bugs that could be solved 
in order to improve our code.
 
### Upgrades

+ First of all, the graphical user interface could be improved, since it was not possible to create a user-friendly 
  and fully responsive interface due to the limited time available.


+ In addition, the class structure could have been improved, since we could have created an abstract class containing the common methods of the classes that inherit from it,
  since we have many classes that have common methods.


+ In addition, the security of the application must be improved, since at present no type of secure access measure has been implemented in the application.
  To solve this we could have created a binary cipher to store user passwords outside the base code of the application in .


+ We would also like to remove all use of Java Swing and replace it with JavaFx, due to time constraints it was easier for us to use JavaFX panels,
  since Java Swing panels are not so time cosuming to create. This decision was taken knowing that the joint of JavaFx and Java Swing can cause system crashes that will probably cause 
  incorrect operation functionalities on the aplication.


+ If the user clicks on "Log Out" button, after the .SQL file has been loaded, will appear some elements that will
  not recharge after the user logs in again. Also the windows size will not be the appropiate, so this could be
  annoying and confusing for the user. This is something we have to work on in the future.


+ We believe with certainty that the packages created within our application have helped us to structure the code in a simpler and more orderly way.
  But we strongly believe that we could have improved the structure of the same, since in some cases we have created quite poor
  packages, which is not very efficient, besides, there are methods that could have been included and modularized in other packages.
  in order to have a better encapsulation and to have improved the security and the efficiency of the security and efficiency of the application.

## Cybersecurity

CRUD operations are performed using parameterized queries, which avoids SQL code injection.

## How to execute the code

Below are the requirements and instructions for running the project. If the requirements are not met, the program will 
not run correctly. If the instructions are not followed, the program may generate unexpected errors.

### Requirements

To run the program, you must have the Java JVM installed and updated to the latest version. 
To check if it is installed or updated, open your computer's terminal and type the following command:

```
java -version
```

If the command is not recognized or your JVM is under 18 version, it means that you do not have the JVM 
installed on your computer or not updated. To install the latest version, please follow the link below:

https://www.oracle.com/es/java/technologies/downloads/

![image](https://user-images.githubusercontent.com/110684532/230032068-d0c2d320-e0d0-463f-bfc0-ec7040409c21.png)

## Project features

As previously mentioned, this project works dynamically with any relational database, 
being this its most outstanding attribute.

### Execution instructions

To run the program, you must first access the folder where the project is located:

```
src/main/java/Loggin/interfaces/Loggin.java
```

In case we want to connect through the cloud database, we must make a series of changes to the following file changes 
in the following file:

```
src/main/java/Loggin/sqlExecs/Conection.java
```

Nos dirigiremos al método **retriveCloudData** situado en la línea 56.

    private static Map<String, String> retriveCloudData(){
        Map<String, String> mp = new HashMap<>();
        //Fill this with your cloud credentials.
        mp.put("host","localhost"); // Your DDBB server hostName (localhost by default)
        mp.put("port","3306"); //BBDD port (3306 for Mysql)
        mp.put("database","DatabaseName"); //BBDD name (Ex. test)
        mp.put("username","root"); //Root user from database (Ex. root)
        mp.put("password","root"); //Database password (Ex. root)
        return mp;
    }

Where we must change

* "localhost" for the name of our database
* "3306" for the access port of our database (3306 is the default port used by MySQL)
* "DatabaseName" for our database name
* "root" for the user of the database
* "root" for the password of the database

### Program flow

Once the program is executed, a new login window will be shown, where we will have to enter the user password, and the user 
will have to choose the type of connection we want to use, either local or cloud. Once we have entered the data, we
will have to click on the "Login" button to access the main window of the program.

In case we choose the local connection, a new window will be shown where we will have to enter some credentials to
access the local database. In case that we choose to connect through the cloud, the program will connect directly to
the cloud database.

Inside the aplication window we have the option of draging and droping the .sql file that we want to execute in 
the main window of the program. Once the file is fully charged on the program, we will have to click on the 
"Load data" button to correctly exectute the file. After this file is executed, a serie of buttons will appear,
each one of them representing a table of the database. This buttons offer us the possibility of clicking on them,
switching the user to a new window, and this will execute the methods that scan the database making a dynamic table with 
all its corresponding data. In this window we will be able to perform CRUD operations on the table.


To work with the integred CRUD operations, we will have to click on the "Add", "Edit" or "Delete" buttons which will 
show us a serie of textfields where we will have to enter the data we want to add, edit or delete. 
Once all the data have been introduced, we will have to click on the "Execute" button to execute the operation.

Once this has been done we will be able to automaticly see the changes in the table.

----------------------------------------------------------------------------------------------

# Gestor de bases de datos con JavaFx

## Autores

- Samuel Yúnez Hernández (Masu17)
- Ignacio Barrios Hernández (idevcm)

## Descripción

Se trata de una aplicación de escritorio que gestiona operaciones CRUD dentro de una base de datos relacional MySQL.
hemos realizado el programa con funcionalidades dinamicas para que este pueda trabajar con cualquier documento .SQL
que el usuario quiera introducir.

### ¿Cual fue nuestra motivacion?

Nuestra motivacion nace con el fin de aprender a utilizar frameworks de desarrollo de interfaces
gráficas de usuario, en este caso JavaFx, así como familiarizarnos con el uso de bases de datos en la nube. Además,
fortaleció nuestra capacidad de organización y trabajo en equipo.

### ¿Cual era el objetivo?

El objetivo fue crear una aplicación de escritorio que permitiera realizar operaciones CRUD dentro de una base de datos
con una interfaz gráfica de usuario amigable y dinámica. Dicha aplicación debía permitir al usuario elegir si quería
trabajar con una base de datos local o en la nube, así como también permitirle elegir el archivo .SQL que quería
utilizar.

Todo ello debía realizarse en el plazo máximo de una semana sin conocimientos previos del funcionamiento de JavaFx,
por lo que las labores de aprendizaje, estructuración e implementación debían llevarse a cabo casi de forma simultánea.

Por ultimo tambien queriamos realizar una aplicacion que fuera capaz de trabajar con cualquier base de datos relacional,
es decir queriamos que utilizara metodos genericos y realizara funciones dinamicas dependiendo de la base de
datos introducida por el usuario.

## Reconocimientos y contribuciones

Gracias a este proyecto, realizado en el 1º curso del ciclo superior de Desarrollo de Aplicaciones Multiplataforma,
recibimos menciones distinguidas por parte de nuestro profesor de programación, así como la máxima calificación.

### Agradecimientos

Quisieramos agradecer a nuestro profesor de
programacion, [Josè Neftalí Guillén Peréz](https://www.linkedin.com/in/jos%C3%A8-neftal%C3%AD-guill%C3%A9n-p%C3%A9rez-466567100/),
por su determinación y dedicación a la hora de enseñarnos a programar. En septiembre del año 2022 apenas teníamos
conocimientos de programación y gracias a su dedicación y buenhacer hemos obtenido un muy alto nivel de abstracción,
asi como de estructuración logico. Ademas nos ha aportado las herramientas necesarias para poder seguir aprendiendo por
cuenta propia y poder así seguir creciendo como programadores.

## Tecnologías utilizadas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Cloud](https://img.shields.io/badge/Cloud-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![LINUX](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)

### Lenguajes de programación

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)

Se ha optado por usar el lenguaje de programación Java por su versatilidad y facilidad de uso, además de ser un lengueje
orientado a objetos, lo que nos permite crear una estructura de clases que se adapte a las necesidades del proyecto.

![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

Hemos optado por utilizar MySQL como gestor de bases de datos ya que no habiamos trabajado con el anteriormente y
queriamos
utilizarlo con el fin de aprender un nuevo lenguaje de SQL.

### Frameworks

![JavaFx](https://img.shields.io/badge/JavaFX-5382A1?style=for-the-badge&logo=Java&logoColor=white)

Hemos optado por utilizar JavaFx como framework de desarrollo de interfaces gráficas de usuario ya que es el que se nos
recomendo en clase y el que mas nos llamo la atencion. Ademas, nos parecio una buena oportunidad para aprender a
utilizar un framework de desarrollo de interfaces gráficas de usuario.

### Herramientas

![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

IntelliJ IDEA es nuestro IDE de desarrollo, ya que a lo largo de el año pasado y tras probar diferentes IDEs, nos dimos
cuenta de que IntelliJ IDEA era el que mejor se adaptaba a nuestras necesidades. Además, nos permite trabajar con
JavaFx de unaáforma mas sencilla.

## Posibles optimizaciones

Este programa se ha realizado en el plazo de una semana, por lo que no se ha podido realizar una optimización completa
del mismo. Somos conscientes de que se podrían realizar muchas mejoras en el código, así como en la interfaz gráfica de
usuario, pero debido al poco tiempo del que disponíamos no hemos podido realizarlas. A continuación se detallan las
mejoras que se podrían realizar y estariamos muy agradecidos que de utilizar nuestro programa indicaran posibles
mejoras que se podrían realizar o fallos que encuentren con el fin de mejorar nuestro codigo.

### Mejoras

+ En primer lugar se podría mejorar la interfaz gráfica de usuario, ya que no se ha podido realizar una interfaz
  amigable y completamente responsive debido al poco tiempo del que se disponía.


+ Ademas se podría mejorar la estructura de clases, ya que se podría haber creado una clase abstracta que contuviera los
  métodos comunes de las clases que heredan de ella, ya que tenemos muchas clases que tienen métodos comunes.


+ Ademas se debe mejorar la seguridad de la aplicación, ya que en la actualidad no se ha implementado ningún tipo de
  medida de acceso seguro en el aplcativo. Para solucionar esto podriamos haber creado un espacio de cifrado binario
  para guardar las contraseñas de los usuarios fuera del codigo base del aplicativo.


+ Tambien quisieramos retirar todo el uso de Java Swing y sustituirlo por JavaFx, ya que por cuestiones de tiempo
  nos era mas sencillo utilizar paneles de Java Swing ya construidos que crear nuestro propios paneles de error. Esta
  decisión fue tomada a sabiendas que el uso conjunto de JavaFx y Java Swing puede provocar fallos de sistema que
  provocan un funcinamiento incorrecto del aplicativo.


+ En caso de pulsar el botón "Cerrar sesión" tras haber introducido un archivo .SQL, existen ciertos elementos que
  no se recargan y esto hace que la experiencia de usuario no sea la mejor, además el tamaño de la ventana no es
  responsive por lo que es el propio usuario el que tendrá que ajustar el tamaño de la misma
  

+ Creemos con certeza que los paquetes creados dentro de nuestro aplicativo nos han ayudado a estructurar el codigo de
  una forma mas sencilla y ordenada, pero creemos que se podria haber mejorado la estructura de los mismos, ya que en
  algunos casos se han creado paquetes bastante pobres, lo que no es muy eficiente ademas, que hay metodos que podrian 
  haberse incluido y modularizado en otros paquetes, con el fin de tener un mejor encapsulamiento y haber mejorado la 
  seguridad y la efeciencia del aplicativo.

## Ciberseguridad

Las operaciones CRUD se realizan mediante consultas parametrizadas, lo que evita la inyección de código SQL.

## ¿Cómo ejecutar el proyecto?

A continuación se muestran los requisitos y las instrucciones para ejecutar el proyecto. En caso de que no se
cumplan los requisitos, el programa no se ejecutará correctamente. En caso de que no se sigan las instrucciones, el
programa podria generar fallos inesperados.

### Requisitos

Para ejecutar el programa, debes tener instalado la JVM de java o disponer de sus últimas versiones, para comprobar si
lo tienes instalado o actualizado, abre la terminal de tu pc y escribe el siguiente comando:

```
java -version
```

En el caso de que no reconozca el comando o la versión de tu JVM sea inferior a la 18, quiere decir que no tenemos la
JVM instalada en nuestro pc o que bien disponemos de una desactualizada, para arreglar esto siga el siguiente enlace:

https://www.oracle.com/es/java/technologies/downloads/

![image](https://user-images.githubusercontent.com/110684532/230032068-d0c2d320-e0d0-463f-bfc0-ec7040409c21.png)

### Instrucciones de ejecución

Para ejecutar el programa, debes dirigirte al punto de entrada de la aplicación, que se sitúa en la siguiente ruta:

```
src/main/java/Loggin/interfaces/Loggin.java
```

En caso que queramos conectarnos a través de la base de datos en la nube, deberemos realizar una serie de
cambios en el siguiente archivo:

````
src/main/java/sqlExecs/Connection.java
````

Nos dirigiremos al método **retriveCloudData** situado en la línea 56.

    private static Map<String, String> retriveCloudData(){
        Map<String, String> mp = new HashMap<>();
        //Fill this with your cloud credentials.
        mp.put("host","localhost"); // Your DDBB server hostName (localhost by default)
        mp.put("port","3306"); //BBDD port (3306 for Mysql)
        mp.put("database","DatabaseName"); //BBDD name (Ex. test)
        mp.put("username","root"); //Root user from database (Ex. root)
        mp.put("password","root"); //Database password (Ex. root)
        return mp;
    }

Donde deberemos cambiar

- "localhost" por el nombre de nuestra base de datos
- "3306" por el puerto de acceso de nuestra base de datos (3306 es el puerto por defecto que usa MySQL)
- "DatabaseName" por el nombre de nuestra base de datos
- "root" por el usuario de la base de datos
- "root" por la contraseña de la base de datos

### Flujo del programa

Una vez ejecutamos el programa se nos mostrará la ventana de inicio de sesión, en la cual debemos introducir el usuario 
y la contraseña, así como elegir el tipo de base de datos con la que queremos trabajar, local o en la nube.

En caso que escojamos trabajar con una base de datos local, se nos mostrará una ventana en la que debemos introducir
las credenciales necesarias para conectarnos a la base de datos, en caso de que no exista la base de datos. Por el
contrario, si decidimos trabajar con una base de datos en la nube, se nos cargará de forma automática y nos mostrará
la ventana principal de la aplicación.

Deberemos arrastrar y soltar un fichero .SQL en el recuadro derecho de la ventana principal, para que se nos muestre
el botón de "Cargar datos". Una vez cargados, nos aparecerán una serie de botones que nos permitirán seleccionar cada
una de las tablas de la base de datos. A la derecha de la ventana, se nos mostrarán los datos de la tabla seleccionada.

Para realizar una operación, deberemos hacer click sobre el botón correspondiente, el cual nos mostrará una serie de
campos de texto en los que deberemos introducir los datos necesarios para realizar la operación. Es importante que los
introduzcamos correctamente para que se desbloquee el botón de "Ejecutar operación".

Una vez hecho esto, se nos mostrará automaticamente los cambios realizados en la tabla seleccionada.









