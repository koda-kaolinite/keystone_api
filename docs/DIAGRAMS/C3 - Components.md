
```plantuml
@startuml C3 Components - Property Management Platform (Revised)
!include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Component.puml
!include <tupadr3/common>
!include <tupadr3/font-awesome/cloud>
!include <tupadr3/font-awesome/server>
!include <tupadr3/font-awesome/globe>
!include <tupadr3/font-awesome/cogs>
!include <tupadr3/font-awesome/exchange>

!theme sketchy-outline
skinparam backgroundColor #F5F5DC

title Property Management Platform - Component Diagram (Revised)

!$PRIMARY_COLOR = "#2E86AB"
!$EXTERNAL_COLOR = "#F18F01"
!$PERSON_COLOR = "#C73E1D"
!$CONTAINER_COLOR = "#6A994E"
!$SERVICE_COLOR = "#8B5A2B"
!$QUEUE_COLOR = "#7209B7"
!$API_COLOR = "#1B5E20"

AddElementTag("primary_user", $fontColor="white", $bgColor=$PERSON_COLOR, $borderColor=$PERSON_COLOR)
AddElementTag("web_container", $fontColor="white", $bgColor=$CONTAINER_COLOR, $borderColor=$CONTAINER_COLOR, $sprite="globe")
AddElementTag("api_container", $fontColor="white", $bgColor=$API_COLOR, $borderColor=$API_COLOR, $sprite="server")
AddElementTag("business_component", $fontColor="white", $bgColor=$SERVICE_COLOR, $borderColor=$SERVICE_COLOR, $sprite="cogs")
AddElementTag("queue_component", $fontColor="white", $bgColor=$QUEUE_COLOR, $borderColor=$QUEUE_COLOR, $sprite="exchange")
AddElementTag("external_service", $fontColor="white", $bgColor=$EXTERNAL_COLOR, $borderColor=$EXTERNAL_COLOR, $sprite="cloud")
AddElementTag("gateway", $fontColor="white", $bgColor=$PRIMARY_COLOR, $borderColor=$PRIMARY_COLOR, $sprite="server")

left to right direction

' External Actors
Person(user, "User", "Consumes the Application", $tags="primary_user")

' External Systems
System_Ext(cloudInfra, "Cloud Infrastructure", "GCP/SAAS (Storage, SQL, etc.)", $tags="external_service")

' System Containers
Container(spa, "Web Application", "Vue.js", "Provides the user interface.", $tags="web_container")
Component(gateway, "API Gateway", "GCP API Gateway", "Routes API requests, handles cross-cutting concerns like auth.", $tags="gateway")

System_Boundary(c1, "Modular Monolith API") {
    Container(api, "API Application", "Spring Boot, Java", "Hosts all business modules and exposes the REST API.", $tags="api_container") {
        Component(webLayer, "Web Layer", "Spring MVC", "Exposes REST endpoints.", $tags="api_container")
        Component(commandDispatcher, "Command Dispatcher", "Spring Bean", "Dispatches commands to appropriate handlers within modules.", $tags="business_component")
        Component(queryDispatcher, "Query Dispatcher", "Spring Bean", "Dispatches queries to appropriate handlers within modules.", $tags="business_component")
        Component(modules, "Business Modules", "Spring Modulith", "Contains all business logic (Command/Query Handlers, Domain/Read Models).", $tags="business_component")
        ComponentQueue(eventBus, "Event Bus", "In-Memory", "Enables asynchronous communication between modules.", $tags="queue_component")
    }
}

' Relationships
Rel(user, spa, "Uses", "HTTPS")
Rel(spa, gateway, "Makes API calls to", "HTTPS/JSON")
Rel(gateway, webLayer, "Forwards requests to", "HTTPS")

' CQRS Flows
Rel(webLayer, commandDispatcher, "Sends Commands to")
Rel(webLayer, queryDispatcher, "Sends Queries to")

Rel(commandDispatcher, modules, "Dispatches Commands to")
Rel(queryDispatcher, modules, "Dispatches Queries to")

Rel(modules, eventBus, "Publishes Domain Events to (from Command Side)")
Rel(eventBus, modules, "Consumes events (e.g., for persistence/read model updates)", "TransactionalEventListener")

' External Dependencies
Rel(modules, cloudInfra, "Reads/Writes data, stores files", "JDBC, HTTPS/API")

SHOW_LEGEND()
@enduml
```
