
```plantuml
@startuml C3 Components - Single Module  
' C4 Model  
!include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Component.puml  
  
' Styling & Icons  
!include <tupadr3/common>  
!include <tupadr3/font-awesome/server>  
!include <tupadr3/font-awesome/database>  
!include <tupadr3/font-awesome/cogs>  
!include <tupadr3/font-awesome/exchange>  
  
!theme sketchy-outline  
  
' Background styling  
skinparam backgroundColor #F5F5DC  
  
title Component Diagram: Property Management Module  
  
' Define color scheme variables from saved preferences  
!$Domain_Color = "#613E1D"  
!$Application_Color = "#8B5A2B"  
!$Infrastructure_Color = "#B5885C"  
!$IntegrationEvents_Color = "#D6AE8A"  
!$DATABASE_COLOR = "#F18F01"  
!$QUEUE_COLOR = "#7209B7"  
!$API_COLOR = "#1B5E20"  
  
' Define style tags for the remaining elements  
AddElementTag("database", $fontColor="white", $bgColor=$DATABASE_COLOR, $borderColor=$DATABASE_COLOR, $sprite="database")  
AddElementTag("events_bus", $fontColor="white", $bgColor=$QUEUE_COLOR, $borderColor=$QUEUE_COLOR, $sprite="exchange")  
AddElementTag("Domain_Layer", $fontColor="white", $bgColor=$Domain_Color, $borderColor=$Domain_Color, $sprite="cogs")  
AddElementTag("Application_Layer", $fontColor="white", $bgColor=$Application_Color, $borderColor=$Application_Color, $sprite="cogs")  
AddElementTag("Infrastructure_Layer", $fontColor="white", $bgColor=$Infrastructure_Color, $borderColor=$Infrastructure_Color, $sprite="cogs")  
AddElementTag("IntegrationEvents_Layer", $fontColor="white", $bgColor=$IntegrationEvents_Color, $borderColor=$IntegrationEvents_Color, $sprite="cogs")  
  
  
Boundary(propertyApi, "BackEnd API Container") {  
    ComponentQueue(eventsBus, "Events Bus", "In memory", $tags="events_bus")  
  
    Boundary(propertyManagementModule, "Property Management Module") {  
        Component(propMgmtInfrastructure, "PropertyManagement.Infrastructure", "Spring Framework", "Handles data access and external services communication.", $tags="Infrastructure_Layer")  
        Component(propMgmtApplication, "PropertyManagement.Application", "Spring Framework", "Implements use cases and orchestrates domain logic.", $tags="Application_Layer")  
        Component(propMgmtDomain, "PropertyManagement.Domain", "Spring Framework", "Contains business entities and core domain logic.", $tags="Domain_Layer")  
        Component(propMgmtIntegrationEvents, "PropertyManagement.IntegrationEvents", "Spring Framework", "Defines events for cross-module communication.", $tags="IntegrationEvents_Layer")  
    }  
}  
  
' External Database  
ComponentDb(propMgmtData, "Database", "SAAS", "Cloud Based Database Cluster", $tags="database")  
  
  
' Module relationships  
' Lay event bus above the application component  
Rel(eventsBus, propMgmtApplication, "Subscribes to events")  
  
Rel(propMgmtApplication, propMgmtDomain, "Uses")  
Rel(propMgmtApplication, propMgmtInfrastructure, "Uses")  
Rel(propMgmtApplication, propMgmtIntegrationEvents, "Uses")  
Rel(propMgmtInfrastructure, eventsBus, "Publishes to")  
  
Lay_D(eventsBus, propMgmtApplication)  
  
' Relationship to the external database  
Rel(propMgmtInfrastructure, propMgmtData, "Reads/Writes to")  
  
  
SHOW_LEGEND()  
@enduml
```