RedisClient
===========

A no nonsense Redis Client using pure scala


The approach is simple - keep interaction with Redis in Red-ish way. That means send simple and pure Redis command
and get simple and pure Redis response. Very simple but powerful Scala based Redis client without any thirdparty 
dependency. Users need not know about any special APIs but know about Redis commands and its responses - thats it. 
Philosophically converting CLI based interation or dynamic typed style into static is sort of like going backward. 
This library preserves that dynamic nature of Redis protocol and CLI. It adds asynchronous behavior on top of it without altering Redis single-threaded behavior. That means - your Scala/Java application can do other stuffs while Redis is processing the command(s) sent to it, and once Redis sends the response back, the future gets returned and canbe used by the calling client.

To run the program quickly - do a git clone and change to directory RedisClient and run sbt - make sure you have sbt 0.12 or higher.

run compile command from sbt shell

<pre>
SBT> compile 
SBT> console
scala> :paste
val c = new RedisClient()

val f = c.send("GET key1")

f onComplete {
  case Success(resp) => println(resp)
  case Failure(t) => { println("Something failed"); t.getMessage()}
}
</pre>

And watch it yourself :) 

Also look at this optional helper function 

<pre>
def handleResponse(f: Future[String], fun: Any => Any) {
    f onComplete {
      case Success(resp) => fun(resp)
      case Failure(t) => t.getMessage
    }
  }
</pre>
