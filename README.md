# Java Client-Server

## Summary
This is a Java Client-Server application for distribution of work amongst "Workers". Client-Server-Workers communication uses TCP sockets.

* The application hanndles the serialization problem of sending a lamda Function Object over a TCP socket.
	
## To run the app:
1. Run ./src/server/MultiServer.java.
2. Run ./src/client/Client.java as many times as needed, while the server is running.