RedisClient
===========

A no nonsense Redis Client using pure scala


The approach is simple - keep interaction with Redis is Redis-ish way. That means send simple and pure Redis command
and get simple and pure Redis response. Very simple but powerful Scala based Redis client without any thirdparty 
dependency. Users need not know about any special APIs but know about Redis commands and its responses - thats it. 
Philosophically converting CLI based interation or dynamic typed style into static is sort of like going backward. 
This library preserves that dynamic nature of Redis protocol and CLI. It adds asynchronous behavior on top of it without altering Redis single-threaded behavior. That means - your Scala/Java application can do other stuffs while Redis is processing the command(s) sent to it, and once Redis sends the response back, the future gets returned and canbe used by the calling client.
