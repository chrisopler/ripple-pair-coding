
# Getting Started

simple scripts are provided to start the servers on localhost. 
terminal 1> start-bob
terminal 2> start-alice

Also simple scripts using curl are provided to pay alice and bob.

in a third terminal you could invoke those scripts for example:
pay-alice 500.23
pay-bob 12.1

You will see on the console log that follow the format provided in the challange.

## APIs

These are the API's that were implemented. 

```
POST {base_uri}/trustline/debit
	Deduct from the balance of the trust of the local trustline and perform a deposit
	on the peer. the payload is of the form:
		{
   		  "debit": "10"
		}
	On success HTTP 200 is returned with the balance  
		{
		  "balance": "10"
		}
POST {base_uri}/trustline/credit
	Add to the balance of the local trustline. The payload is of the form:
		{
   		  "credit": "10"
		}
	On success HTTP 200 is returned with the balance  
		{
		  "balance": "10"
		}
	
GET  {base_uri}/trustline/balance
	Return the current balance for the local trustline (Not really needed).
	
	On success HTTP 200 is returned with the balance  
		{
		  "balance": "10"
		}
```

Since we only pay one peer which is identified at boot time we could have used a url parameter to pass the credit/debit amount but I opted to use a json http request body instead.


### Build

```
mvn clean package
```

## Manual steps

```
java -Dserver.port=<port_number>  -jar --name <name> --peer <peer_ip:peer_port>
```
## Start Alic's Trustline

```
java -Dserver.port=8080 -Dname=Bob -Dpeer="http://127.0.0.1:8081" -jar application/target/application-0.0.1-SNAPSHOT.jar
```
## Start Bob's Trustline
```
java -Dserver.port=8081 -Dname=Alice -Dpeer="http://127.0.0.1:8080" -jar application/target/application-0.0.1-SNAPSHOT.jar
```


## NOTE
Idealy we would not need to use IP addresses but since a service registery/DNS is not part of this challange we will add IP addresses options to keep it simple.

### To send money from Alice to Bob.
```
curl -H "Accept: application/json" -H "Content-type:  application/json" -X POST -d '{"debit":"1.5"}' localhost:8080/trustline/debit

```

### To send money from Bob to Alice

```
curl -H "Accept: application/json" -H "Content-type:  application/json" -X POST -d '{"debit":"1.5"}' localhost:8081/trustline/debit

```
