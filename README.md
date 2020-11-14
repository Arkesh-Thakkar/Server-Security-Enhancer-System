# Server-Security-Enhancer-System
A java based desktop application which enhances server security by various utilities like scanning and providing the list of open ports within the system, Detecting brute-force attack on a portal and blocking that account, and detecting DOS (Denial of Service) attack and blocking it for required time being.

Download all the folders and make sure to have mysql-connector-java-3.1.12 jar or upper version for database connection properly configured.
Compile by javac mainMenu/HomePage.java
In terminal run HomePage by following command: java mainMenu/HomePage

Select the utility and give appropriate inputs.

BruteForce:
--> Make a login table in test database with username,password,disabled,wrong_try fields.
--> Make appropriate entry in database.
--> Then run bruteForce from main page and give username and password if given correct it leads to welcome page and if given wrong 10 times for a particular username it would block the account and only admin can unblock it from database.

DOS:
--> First run server.java from dos folder.
--> Select dos utility from main page and send the packet to server.
--> If server is flooded with more than 10 packets within a minute then it would block user for 10 seconds.

Portscanner:
--> Run portscanner utility from main menu.
--> Enter the url (ex: localhost) in the specified field and scan it.
--> It provides the list of openports for the given url.
