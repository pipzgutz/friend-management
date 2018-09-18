# Friend Management API
This is an API where you can manage friends, add, remove, block, etc.

# Technology Stack
* Spring Boot
* Spring JPA
* Spring MVC
* Swagger

# Databases
* development - h2 (embedded)
* production - PostgreSQL

# Development
Open the project with your preferred IDE

To build execute the following command:
```
./mvnw clean install
```

To run execute the following command:
```
./mvnw spring-boot:run
```

# Production

## Pivotal Cloud Foundry
Continuously deliver any app to every major private and public cloud with a single platform

## Deployment to Pivotal Cloud Foundry
TODO

## Deployment to Docker
TODO

# Accessing Swagger UI
Open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) to access the Swagger UI

# Rest Endpoints

## /friend/add-connection
Create a friend connection between two email addresses
* Input:
```
{ 
    "friends": [ "andy@example.com", "john@example.com" ]
}
```
* Output:

Status: 200
```
{
    "success": true
}
```

## /friend/friends-list
Retrieve the friends list for an email address
* Input:
```
{ 
	"email": "john@example.com"
}
```
* Output:

Status: 200
```
{
    "friends": [
        "andy@example.com"
    ],
    "count": "1",
    "success": true
}
```

## /friend/friends-list-common
Retrieve the common friends list between two email addresses
* Input:
```
{ 
	"friends": [ "andy@example.com", "john@example.com" ]
}
```
* Output:

Status: 200
```
{
    "friends": [
        "jessy@example.com"
    ],
    "count": "1",
    "success": true
}
```

## /friend/subscribe
Subscribe to updates from an email address

* Input:
```
{
	"requestor": "andy@example.com",
	"target": "john@example.com"
}
```
* Output:

Status: 200
```
{
    "success": true
}
```

## /friend/unsubscribe
Unsubscribe to updates from an email address

* Input:
```
{
	"requestor": "andy@example.com",
	"target": "john@example.com"
}
```
* Output:

Status: 200
```
{
    "success": true
}
```

## /friend/block
Block updates from an email address

* Input:
```
{
	"requestor": "andy@example.com",
	"target": "john@example.com"
}
```
* Output:

Status: 200
```
{
    "success": true
}
```

## /friend/unblock
Unblock updates from an email address

* Input:
```
{
	"requestor": "andy@example.com",
	"target": "john@example.com"
}
```
* Output:

Status: 200
```
{
    "success": true
}
```

## /friend/unblock
Retrieve all email addresses that can receive updates from an email address

* Input:
```
{
	"requestor": "andy@example.com",
	"target": "john@example.com"
}
```
* Output:

Status: 200
```
{
    "success": true
}
```

# General Error Handling
Any errors in cannot handle will return the following:
* Status: 500

```
{
    "errorMessage": "An internal error occurred, kindly email the admins"
}
```