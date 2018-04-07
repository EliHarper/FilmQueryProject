#FilmQuery Project

###Eli Harper

The FilmQuery project applies lessons I've learned so far using the following technologies:
 * MySQL
 * MAMP
 * JDBC/Java (Eclipse)
 * Maven
 * Shell

 Issues Experienced:
 * Recursion causing SQLExceptions, solved by removing self-calls within methods and implementing proper try/catch blocks
 * Getting ahead of myself and writing larger amounts of code without proper tests along the way

 This project takes a database schema that contains several tables that revolve around films, their information (actors, languages, ratings etc.) film stores (locations, addresses of staff, customer information etc.) and joins the tables to create a functioning yet simple UI that returns information about movies when searched by a given keyword or id.

 By taking user input through an Eclipse console, the methods, contained within the FilmQueryApp, match information by taking MySQL command Strings in Java (in the DatabaseAccessorObject class) and utilizing PreparedStatements and Bind Variables (to practice implementing standard security measures) before execution of their commands. 

 I used Object-Relational Mapping to translate the entities and attributes within the database to more tangible Java Objects. Each time a query was executed (successfully), a Film or Actor was constructed, which was then able to simply display their encapsulated states with their getters in a more aesthetically pleasing way with a separated startUserInterface() method that exists within the class of the App.
