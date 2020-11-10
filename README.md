#Message Hash Application

## Prerequisites
Required Software
- Java 1.8
- Maven

## How to run this application
- Navigate to base directory (calculatorstream folder)
- Run following commands
    ````
    mvn install
    ````
    ````
    mvn spring-boot:run
    ````
    Above command will start the tomcat server with the application

- Message Hash APIs

    ##API to create message hash
    ###/messages
    ````
     POST http:\\localhost:8080\messages
  
     Request Payload:
     {
       "message" : "this is a sample message!"
     }
  
     Response Payload:
     {
         "digest": "bdfcba37390f1dc3d871011777098dab32c8dd9542b56291268ed950c8b58ba7"
     }
    ````
  
    ##API to get message by hash
    ###/messages/{hash}
    ````
     GET http:\\localhost:8080\messages\bdfcba37390f1dc3d871011777098dab32c8dd9542b56291268ed950c8b58ba7
       
     Response Payload:
     {
         "digest": "bdfcba37390f1dc3d871011777098dab32c8dd9542b56291268ed950c8b58ba7"
     }
    ````
  
    ##API to delete message by hash
    ###/messages/{hash}
    ````
     DELETE http:\\localhost:8080\messages\bdfcba37390f1dc3d871011777098dab32c8dd9542b56291268ed950c8b58ba7
     
     Response HTTP Status Code : 200 OK      
    ```` 
    
    ##API to get metrics
    ###/metrics
    ````
     GET http:\\localhost:8080\metrics
     
     Response Payload:
     {
         "message": "Number of request hits per 1 hour",
         "metrics": {
             "DELETE /messages/<hash>": 1,
             "GET /messages/<hash>": 2,
             "POST /messages": 2
         }
     }
    ````
