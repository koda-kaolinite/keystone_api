

```plantuml
@startuml Enhanced Property Management Platform - C4 System Context  
!include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Container.puml  
!include <tupadr3/common>  
!include <tupadr3/font-awesome/cloud>  
!include <tupadr3/font-awesome/server>  
  
!theme sketchy-outline  
LAYOUT_TOP_DOWN()  
  
' Background styling  
skinparam backgroundColor #F5F5DC  
skinparam backgroundColorActivity #F5F5DC  
  
title Property Management Platform - System Context Diagram  
  
!define DIRECTION left to right direction  
  
' Define color scheme variables  
!$PRIMARY_COLOR = "#2E86AB"  
!$SECONDARY_COLOR = "#A23B72"  
!$EXTERNAL_COLOR = "#F18F01"  
!$PERSON_COLOR = "#C73E1D"  
  
' Define style classes  
AddElementTag("primary_user", $fontColor="white", $bgColor=$PERSON_COLOR, $borderColor=$PERSON_COLOR)  
AddElementTag("admin_user", $fontColor="white", $bgColor=$SECONDARY_COLOR, $borderColor=$SECONDARY_COLOR)  
AddElementTag("core_system", $fontColor="white", $bgColor=$PRIMARY_COLOR, $borderColor=$PRIMARY_COLOR, $sprite="server")  
AddElementTag("external_service", $fontColor="white", $bgColor=$EXTERNAL_COLOR, $borderColor=$EXTERNAL_COLOR, $sprite="cloud")  
  
left to right direction  
  
  
' Primary Users  
Person(broker, "Property Broker", "Real estate agents who manage property listings and facilitate transactions", $tags="primary_user")  
Person(buyer, "Potential Buyer/Renter", "Users searching for properties to purchase or rent", $tags="primary_user")  
Person(seller, "Property Seller", "Individuals or entities listing properties for sale or rent", $tags="primary_user")  
  
' Administrative Users  
Person(admin, "Platform Administrator", "Staff managing platform operations and user accounts", $tags="admin_user")  
  
' Core System  
System(api, "Property Management Platform", "Digital platform for property selling, renting, and management operations", $tags="core_system")  
  
' External Systems  
System_Ext(cache, "Cache Layer", "Caches frequently accessed data and user sessions", $tags="external_service")  
System_Ext(cloudService, "Cloud Storage Service", "Third-party cloud storage for property images and documents", $tags="external_service")  
System_Ext(dbService, "Database Storage Service", "Third-party database storage for platform data", $tags="external_service")  
System_Ext(smtpServer, "SMTP Server", "Third-party email service for notifications and communications", $tags="external_service")  
  
' Primary User Interactions  
Rel(broker, api, "Manages and approves property listings", "HTTPS/Web Interface")  
Rel(buyer, api, "Searches and views properties", "HTTPS/Web Interface")  
Rel(seller, api, "Lists properties for sale or rent", "HTTPS/Web Interface")  
Rel(admin, api, "Administers platform and manages users", "HTTPS/Web Interface")  
  
' External System Integrations  
Rel(api, cache, "Stores cached data", "HTTPS/API")  
Rel(api, cloudService, "Stores and retrieves media files", "HTTPS/API")  
Rel(api, dbService, "Stores and retrieves platform data", "HTTPS/API")  
Rel(api, smtpServer, "Sends email notifications", "HTTPS/API")  
  
SHOW_LEGEND()  
@enduml
```

