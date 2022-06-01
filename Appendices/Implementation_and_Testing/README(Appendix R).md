# Semester-Project-Library-README

This client-server(and database) program is capable of creating multiple type of users (library users, librarians), adding and removing different multimedia items and borrowing them.

CONFIGURATION INSTRUCTIONS:
In order to use the program user has to download .zip file with all contents of the program.
After extracting it into desired location on user's computer several steps had to be done:
-download and properly connect java libraries required to run the program:
*put folder with 'javafx-sdk-17.0.2' Program Files -> Java
*open folder with program in IDE (for example Intellij IDEA)
*In IDE open File -> Project Structure -> Libraries and search for javafx-sdk-17.0.1
*Open folder Program files -> Java -> javafx-sdk-17.0.1 -> lib -> select all libraries and add them to the project.
*In the Project Structure -> Libraries -> search for postgresql-42.3.3 and add it to the Project Structure
*In the Project Structure -> Libraries -> search for remoteobserver.jar and add it to the Project Structure
*Setup JDK (Java Developement Kit) to 17.0.2 (or newer)
Java Project contains 3 modules (Client, Server, Shared), packages, classes and fxml files.
In order to run the program user has to open Library foler in IDE and start Server class located in module Server. After receiving information that server is running on port 1099 user has to run Application class located in module Client. After running these two classes the program will start working and will be fully operational.
In case of getting and error with message 'JavaFX runtime components are missing, and are required to run this application' user should follow these steps.
*Open Edit Run Configuration for Application.main() class
*Modify options(top-right corner)[Alt+M] -> Add VM options[Alt+V] -> in the new label for text add '--module-path "\path\to\javafx-sdk-17\lib" --add-modules javafx.controls,javafx.fxml' to run configuration.
*Apply changes and click 'ok'.
In case of wanting to run multiple clients on the same machine user should follow these steps.
*Open Edit Run Configuration for Application.main() class
*Modify options(top-right corner)[Alt+M] -> Allow multiple instances[Alt+U] -> Apply changes -> ok
In order to learn how to use program preperly it's creators would like to recommend reading USER GUIDE.








=============================================================================================================================================================================================
TROUBLESHOOTING
In case of any problems with running the program contact team 10.
Rafael Sánchez Córdoba 315212@via.dk
Rosa Briales Marfil 315250@via.dk
Alexandru Dulghier 315267@via.dk
María Ortiz Planchuelo 315266@via.dk
Franciszek Jan Nurkiewicz 318212@via.dk