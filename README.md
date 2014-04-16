Scala RedisClient
=================

A no nonsense Redis Client using pure scala


The approach is simple - keep interaction with Redis in Red-ish way. That means send simple and pure Redis command
and get simple and pure Redis response. Very simple but powerful Scala based Redis client without any thirdparty 
dependency. Users need not know about any special APIs but know about Redis commands and its responses - thats it. 
Philosophically converting CLI based interation or dynamic typed style into static is sort of like going backward. 
This library preserves that dynamic nature of Redis protocol and CLI. It adds asynchronous behavior on top of it without altering Redis single-threaded behavior. That means - your Scala/Java application can do other stuffs while Redis is processing the command(s) sent to it, and once Redis sends the response back, the future gets returned and canbe used by the calling client.

You are encouraged to downlaod the project and try it by following the instructions given. If you want to just embed/use in your project here is the simple thing you need to follow.

For SBT add this to build.sbt

    libraryDependencies += "com.chiradip.rediscl" % "redisclient_2.10" % "0.8"

You may also need to add the repository in your build file

    resolvers += "Sonatype OSS releases" at "https://oss.sonatype.org/service/local/repositories/releases/content/"

For Maven use this dependency:

    <dependency>
        <groupId>com.chiradip.rediscl</groupId>
        <artifactId>redisclient_2.10</artifactId>
        <version>0.8</version>
    </dependency>

You may want to add repository in maven like the follows:

    <repositories>
        ....
        <repository>
          <id>sonatype_releases</id>
          <url>https://oss.sonatype.org/service/local/repositories/releases/content/</url>
        </repository>
        ....
     </repositories>


You are encouraged to try these before you  use in your project
================================

To run the program quickly - do a git clone and change to directory RedisClient and run sbt - make sure you have sbt 0.12 or higher.

run compile command from sbt shell

    SBT> compile 
    SBT> console
    scala> :paste
    import scala.concurrent._
    import ExecutionContext.Implicits.global
    import scala.util.{Success, Failure}


You need the above lines in order to run it from sbt/scala console. 
    
    scala> :paste
    val c = new com.chiradip.rediscl.RedisClient()
    
    val f = c.send("GET key1")
    
    f onComplete {
      case Success(resp) => println(resp)
      case Failure(t) => { println("Something failed"); t.getMessage()}
    }


And watch it yourself :) 

Also look at this optional helper function 


    def handleResponse(f: Future[String], fun: Any => Any) {
        f onComplete {
          case Success(resp) => fun(resp)
          case Failure(t) => t.getMessage
        }
    }


Think about passing a function which sends the string received as the parameter (of type Any) to an actor. This can be done very easily without making this library dependent on Akka. Enjoy and feel free to enrich this library as long it is kept simple and elegant.

An example of using <code>handleResponse</code>


    scala> c.handleResponse(f, println)

