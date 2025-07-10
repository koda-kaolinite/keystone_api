
```plantuml
@startuml C2 Containers - Property Management Platform  
!include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Container.puml  
!include <tupadr3/common>  
!include <tupadr3/font-awesome/cloud>  
!include <tupadr3/font-awesome/server>  
!include <tupadr3/font-awesome/database>  
!include <tupadr3/font-awesome/globe>  
  
!theme sketchy-outline  
LAYOUT_TOP_DOWN()  
  
' Background styling  
skinparam backgroundColor #F5F5DC  
skinparam backgroundColorActivity #F5F5DC  
  
title Property Management Platform - Container Diagram  
  
!define DIRECTION left to right direction  
  
' Define color scheme variables  
!$PRIMARY_COLOR = "#1B5E20"  
!$SECONDARY_COLOR = "#A23B72"  
!$EXTERNAL_COLOR = "#F18F01"  
!$PERSON_COLOR = "#C73E1D"  
!$CONTAINER_COLOR = "#6A994E"  
  
' Define style classes  
AddElementTag("primary_user", $fontColor="white", $bgColor=$PERSON_COLOR, $borderColor=$PERSON_COLOR)  
AddElementTag("admin_user", $fontColor="white", $bgColor=$SECONDARY_COLOR, $borderColor=$SECONDARY_COLOR)  
AddElementTag("web_container", $fontColor="white", $bgColor=$CONTAINER_COLOR, $borderColor=$CONTAINER_COLOR, $sprite="globe")  
AddElementTag("api_container", $fontColor="white", $bgColor=$PRIMARY_COLOR, $borderColor=$PRIMARY_COLOR, $sprite="server")  
AddElementTag("external_service", $fontColor="white", $bgColor=$EXTERNAL_COLOR, $borderColor=$EXTERNAL_COLOR, $sprite="cloud")  
  
left to right direction  
  
' External Actors
Person(broker, "Property Broker", "Real estate agents who manage property listings and facilitate transactions", $tags="primary_user")  
Person(buyer, "Potential Buyer/Renter", "Users searching for properties to purchase or rent", $tags="primary_user")  
Person(seller, "Property Seller", "Individuals or entities listing properties for sale or rent", $tags="primary_user")  
Person(admin, "Platform Administrator", "Staff managing platform operations and user accounts", $tags="admin_user")  
  
' System Boundary  
System_Boundary(c1, "Property Management Platform") {  
    Container(webApp, "Web Application", "Angular, TypeScript", "Single-page application providing user interface for property management", $tags="web_container")  
    Container(API, "API", "Web Application", "Central API layer handling authentication, routing, file uploads and business logic", $tags="api_container")  
}  
  
' External Systems
System_Ext(cache, "Cache Layer", "Caches frequently accessed data and user sessions", $tags="external_service")  
System_Ext(cloudStorage, "Cloud Storage Service", "Third-party cloud storage for property images and documents", $tags="external_service")  
System_Ext(dbStorage, "Database Storage Service", "Third-party database storage for platform data", $tags="external_service")  
System_Ext(smtpServer, "SMTP Server", "Third-party email service for notifications and communications", $tags="external_service")  
  
' User Interactions  
Rel(broker, webApp, "Manages and approves property listings", "HTTPS/Web Interface")  
Rel(buyer, webApp, "Searches and views properties", "HTTPS/Web Interface")  
Rel(seller, webApp, "Lists properties for sale or rent", "HTTPS/Web Interface")  
Rel(admin, webApp, "Administers platform and manages users", "HTTPS/Web Interface")  
  
' Internal System Communications  
Rel(webApp, API, "API requests", "HTTPS/REST")  
  
' External System Communications  
Rel(API, cache, "Session & data caching", "TCP/Redis")  
Rel(API, cloudStorage, "Stores and retrieves media files", "HTTPS/API")  
Rel(API, dbStorage, "Stores and retrieves platform data", "HTTPS/API")  
Rel(API, smtpServer, "Sends email notifications", "HTTPS/API")  
  
SHOW_LEGEND()  
@enduml
```

