# SpringBootJpaDockerEasyStarter

This is a simple project to understand microservice project structure.  
This microservice deals with user details.  

Counsumer can CREATE?SELECT/DELETE/UPDATE user details    

**SELECT**  

GET http://localhost:8080/users

**CREATE**

POST: http://localhost:8080/user    

     {   
        "name": "Prasobh kollattuu",  
        "email": "Prasobh@test.com"  
    }  

**UPDATE    

PUT: http://localhost:8080/user    
test
    {  
    	"id":"2",  
      "name": "Prasobh kollattuu",  
      "email": "Prasobh@test.com"  
    }  

**DELETE**    

DELETE: http://localhost:8080/user/{id}



