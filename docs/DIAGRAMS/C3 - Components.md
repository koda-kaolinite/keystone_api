
```plantuml
@startuml C3 Components - Property Management Platform  
!include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Component.puml  
!include <tupadr3/common>  
!include <tupadr3/font-awesome/cloud>  
!include <tupadr3/font-awesome/server>  
!include <tupadr3/font-awesome/globe>  
!include <tupadr3/font-awesome/cogs>  
!include <tupadr3/font-awesome/exchange>  
!include <tupadr3/font-awesome/code>  
!include <tupadr3/font-awesome/sitemap>  
  
!theme sketchy-outline  
  
' Background styling  
skinparam backgroundColor #F5F5DC  
  
title Property Management Platform - Component Diagram  
  
' Define color scheme variables  
!$PRIMARY_COLOR = "#2E86AB"  
!$EXTERNAL_COLOR = "#F18F01"  
!$PERSON_COLOR = "#C73E1D"  
!$CONTAINER_COLOR = "#6A994E"  
!$SERVICE_COLOR = "#8B5A2B"  
!$QUEUE_COLOR = "#7209B7"  
!$API_COLOR = "#1B5E20"  
!$CONTROLLER_COLOR = "#4527A0"  
  
' Define style classes  
AddElementTag("primary_user", $fontColor="white", $bgColor=$PERSON_COLOR, $borderColor=$PERSON_COLOR)  
AddElementTag("web_container", $fontColor="white", $bgColor=$CONTAINER_COLOR, $borderColor=$CONTAINER_COLOR, $sprite="globe")  
AddElementTag("api_component", $fontColor="white", $bgColor=$PRIMARY_COLOR, $borderColor=$PRIMARY_COLOR, $sprite="server")  
AddElementTag("api_layer", $fontColor="white", $bgColor=$API_COLOR, $borderColor=$API_COLOR, $sprite="code")  
AddElementTag("controller_component", $fontColor="white", $bgColor=$CONTROLLER_COLOR, $borderColor=$CONTROLLER_COLOR, $sprite="sitemap")  
AddElementTag("business_component", $fontColor="white", $bgColor=$SERVICE_COLOR, $borderColor=$SERVICE_COLOR, $sprite="cogs")  
AddElementTag("queue_component", $fontColor="white", $bgColor=$QUEUE_COLOR, $borderColor=$QUEUE_COLOR, $sprite="exchange")  
AddElementTag("external_service", $fontColor="white", $bgColor=$EXTERNAL_COLOR, $borderColor=$EXTERNAL_COLOR, $sprite="cloud")  
  
' Layout from left to right  
left to right direction  
  
' External Actors  
Person(user, "User", "Person who consumes the Application", $tags="primary_user")  
  
Boundary(webApplication, "Web Application") {  
    ' Web Application Container  
    Container(spa, "Web Application", "Angular, TypeScript", "Responsive single-page application providing a comprehensive property management interface with dashboard and forms.", $tags="web_container")  
  
    Boundary(systemDesign, "BackEnd System Design") {  
        Component(gateway, "API Gateway", "SAAS", "Central entry point providing request routing, load balancing, and rate limiting.", $tags="api_component")  
        Component(cloud, "Cloud Service", "SAAS", "Provides scalable and robust for various types of application necessities.", $tags="external_service")  
  
        ' API Gateway Container detailed in components  
        Boundary(apiGateway, "BackEnd API Container") {  
            ' API Layer  
            Component(restApi, "REST API", "Spring Boot, Spring Web", "RESTful API layer exposing endpoints for property management operations with standardized request/response handling.", $tags="api_layer")  
  
            ' Controllers Layer  
            Component(Controller, "Controller", "Spring MVC", "Handles incoming HTTP requests, validates input, and delegates business logic to appropriate service components.", $tags="controller_component")  
  
            ' Business Logic Modules  
            Component(MODULE, "Business Logic Module", "Spring Boot", "Encapsulates core business logic, processing data and executing domain-specific operations.", $tags="business_component")  
  
            ' Infrastructure Components  
            ComponentQueue(eventBus, "Event Bus", "In-Memory", "Asynchronous message broker enabling loose coupling and reliable event-driven communication.", $tags="queue_component")  
        }  
    }  
}  
  
  
' User Interactions  
Rel(user, spa, "Accesses property management features", "HTTPS/TLS 1.3")  
  
' Internal Container Communications  
Rel(spa, gateway, "API requests for business operations", "REST API/JSON over HTTPS")  
Rel(gateway, restApi, "Routes requests to API layer", "HTTP/Load Balancing")  
  
' API to Controllers flow  
Rel(restApi, Controller, "Delegates requests", "HTTP")  
  
' Controllers to Event Bus  
Rel(Controller, eventBus, "Publishes domain events", "Command Messages")  
  
' Event-driven architecture - Event Bus to services  
Rel(eventBus, MODULE, "Executes business logic based on events", "Event Subscription")  
  
' External System Communications  
Rel(MODULE, cloud, "Manages data and media assets", "REST API/S3 Protocol")  
  
SHOW_LEGEND()  
@enduml
```
