# PIServerThreaded
Creation of an interaction between Raspberry PI Server and java clients, receiving temperature readings from the raspberry PI

The assignment was split into three different parts server, client and GUI (graphical user
interface), the code was designed so that the client can connect to multiple servers and using
the GUI data can be acquired in real time from the servers. The servers are set up on the
raspberry PI.

In the code it was designed so that the server contains a server connection handler which
allows for multiple server connections to be established using multiple threads, this was not
the case for the client the client contains only one thread but when the constructer is run it
predefines how many sockets are opened on the client which allows the client to connect to
multiple servers. The data is then used to represent in a more user friendly on the GUI.
