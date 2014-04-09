/**
 * Created with IntelliJ IDEA.
 * User: chiradip
 * Date: 4/5/14
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */


import java.net.Socket
import java.io._

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

class RedisClient(host: String = "127.0.0.1", port: Int = 6379, idx: Int = 0) {
  val socket = new Socket(host, port)
  val out = new PrintWriter(socket.getOutputStream, true)
  if(idx!=0)
    send(s"USE $idx")

  val in = new BufferedReader(new InputStreamReader(socket.getInputStream))

  def send(command: String): Future[String] = {
    out.println(command)
    val sb = new StringBuilder(1)

    future {
      while(in.ready) {
        sb+in.read.asInstanceOf[Char]
      }
      val string = sb.toString()
      println(s""" $string """)
      val l = string.split("\r\n").toList
      val list = l.filter(s=> !s.startsWith("$"))

      val finalString = decode(list).mkString("\n")
      println(finalString)
      finalString
      //string
    }
  }

  def decode(list: List[String]): List[String] = {
    val token = list.reverse.find(_.startsWith("*")).getOrElse("*-1") //Last StarredElem

    if(token == "*-1") list
    else {
      val numElems = token.substring(1,token.length).toInt

      def lastIndexOf(list: List[String], token: String) = {
        list.length -1 - list.reverse.indexOf(token)
      }

      val lastIndex = lastIndexOf(list, token)

      val firstSlice = list.slice(0,lastIndex)

      println("firstSlice: " + firstSlice.mkString(" || "))

      val secondSlice = list.slice(lastIndex+1, lastIndex + 1 + numElems)

      println("2ndSlice: " + secondSlice.mkString(" || "))
      
      val thirdSlice = list.slice(lastIndex + 2 + numElems, list.length)

      println("3rdSlice: " + thirdSlice.mkString(" || "))

      val innerStr = "{[" + secondSlice.mkString(", ") + "]}"

      val finalList = firstSlice ::: (innerStr :: thirdSlice)
      
      decode(finalList)
    }
  }

  case class RedisClientException(exc: Exception)

  def handleResponse(f: Future[String], fun: Any => Any) {
    f onComplete {
      case Success(resp) => fun(resp)
      case Failure(t) => t.getMessage
    }
  }
}

