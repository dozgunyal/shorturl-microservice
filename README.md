# Microservice for a short url service

## Endpoints:

* POST: /v1/shorten (Basic Auth) 

Example request:

    { "url": "http://github.com/dozgunyal"}
    
Example response:

    { "url": "http://localhost:8080/FMJWUbf5"}
    
* GET: /r/{id} (Public)


## Implementation

* Shorten Url

Uses redis. connection information is in application.properties. (redis.host, redis.port, redis.password)

Creates autoincremented long id's using a dedicated key. key is in application.properties. (shorturl.service.redis.idsequence.key)

Saves urls to redis using generated long id. key format is in application.properties (shorturl.service.redis.urlkey.format)

Converts long id to Base62 and generates redirect url. url format is in application properties (shorturl.service.url.format)

Protected by basic auth. username password is in application properties. (shorturl.service.basic.auth.user, shorturl.service.basic.auth.password)

* Redirect Url

Gets url param. Converts Base62 to long id. search redis with id. if found redirect. if not found return 404.
