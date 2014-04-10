name := "RedisClient"

organization := "com.chiradip.rediscl"

version := "0.8"

scalaVersion := "2.10.3"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.6"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.1"

credentials += Credentials("Sonatype Nexus Repository Manager", "nexus.scala-tools.org", "chiradip", "sonatype123")

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    //Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    Some("snapshots" at nexus + "content/repositories/snapshots")
}

