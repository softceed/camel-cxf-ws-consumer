# camel-cxf-ws-consumer

Beispielprojekt für den Aufruf eines externen SOAP Web Services über SSL/TLS mit Apache CXF aus einer Apache Camel Anwendung heraus, die in einer Oracle WebLogic Server 11g-Umgebung läuft.

## Voraussetzungen ##

Java 8, Maven 3.2.3

## Projektstruktur ##

 - Root-Verzeichnis:
 Allgemeine pom.xml
 - webservice-beans:
 WSDL-Datei des externen Web Services
 CXF-Konfiguration für Nutzung des Web Services und Zugriff auf Keystore und Truststore für SSL-Kommunikation
Definition von CXF Interceptors
 - war:
Definition der Camel Route
WebLogic-spezifische Konfigurationsdateien
 - ear:
WebLogic-spezifische Konfigurationsdateien

## Build ##
Im Root-Verzeichnis Aufruf von

    mvn clean install

Erzeugt wird eine deploybare .ear-Datei.
