#Property Manager

## REST API 



###Get the available Properties
```routes
GET /v1/properties 
```

###Create a new Property
```routes
POST /v1/properties 
```
example 
```bash
http --verbose POST http://localhost:9000/v1/properties address="Nice" postcode="06000" latitude=99999 longitude=999999 
```

###Modify a required attribute or add/delete/modify an optional attribute 
```routes
PATCH /v1/properties/$id
```

example: add a surface to a property (the property id is 2)
```bash
http --verbose PATCH http://localhost:9000/v1/properties/2 address="Nice" postcode="06000" latitude=99999 longitude=999999 surface=500
```

to remove it afterwards
```bash
http --verbose PATCH http://localhost:9000/v1/properties/2 address="Nice" postcode="06000" latitude=99999 longitude=999999 
```
The body of the PATCH request contains the ***diff*** 

###Delete a property 
```routes
DELETE /v1/properties/$id 
```

###Get the prices of a property
```routes
GET  /v1/properties/$id/prices 
```
### Add a price to e property
```routes
POST  /v1/properties/$id/prices 
```
example 
```bash
http --verbose POST http://localhost:9000/v1/properties/2/prices  date="2017-08-02" price="06000" 
```

## Tests 
The tests are not "advanced" (I am discovering the framework) but there is a bunch of them runnable by 
```bash
sbt  test
```

##Packaging 
Classic Docker Container, a simple script of creation available in scripts/dockerize.sh

##database 
simple H2 in memory database, using ***Slick*** plugin to avoid writing hardcoded SQL queries, which can simplify the migration from a db provider to another 

## UI 
the code is in different github repo 
https://github.com/fourat2018/property-manager-ui

but the ui build is available in projects/ and the available via the route /index.html 

The UI is not robust 

## deployment 
http://euphrate.sloppy.zone/properties


