
## Problem
A “trustline” is a way to keep track of debt between two parties. The trustline balance starts at 0 and both parties independently track their views of the balance. For example, if Alice owes Bob $10, then Alice sees a balance of -10 and Bob sees a balance of 10. If Alice increases her debt to Bob by $10 more, then her balance would be -20, and Bob’s balance would be 20.

Write a program that implements a trustline across two servers, each exposing a RESTful API to allow a remote client to operate against the API. Then, using any tool of your choice (JUnit/TestNG, Command Line Interface (CLI), Web-Browser, etc), implement a client program that can send debt to the other party in the trustline. After each client-initiated operation, the sending server should emit the amount sent, and then emit the new trustline balance. At the same time, the receiving server should emit the amount received, and then emit the new trustline balance. 

With two terminal windows open, you should be able to operate your trustline client against either endpoint, multiple times, and have the trustline balances update properly on each server.

### Example Terminal Output

#### Alice's Server (e.g., Port 8080)

```sh
(Server Starts...)
$> Welcome to the Trustline
$> Trustline balance is: 0
(Execute Client Send from Alice to Bob...)
$> Paying 10 to Bob...
$> Sent
$> Trustline balance is: -10
```

#### Bob's Server (e.g., Port 8081)

```sh
(Server Starts...)
$> Welcome to the Trustline
$> Trustline balance is: 0
(Execute Client Send from Alice to Bob...)
$> You were paid 10!
$> Trustline balance is: 10
```

### Constraints

- Each user keeps track of their own balance.
- Assume the users are on different computers.
- State does not have to persist between sessions.
- State for a given server should not be tracked/stored remotely (i.e. in a remote database or other remote server).
- You may ignore authentication/authorization
- Log _formatting_ may deviate from the example output.