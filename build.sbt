name := "RedisClient"

organization := "com.chiradip.rediscl"

version := "0.8"

scalaVersion := "2.10.3"

libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.6"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.1"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.1.0" % "test"

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/chiradip/RedisClient</url>
  <licenses>
    <license>
      <name>BSD-style</name>
      <url>http://www.opensource.org/licenses/bsd-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:chiradip/RedisClient.git</url>
    <connection>scm:git:git@github.com:chiradip/RedisClient.git</connection>
  </scm>
  <developers>
    <developer>
      <id>you</id>
      <name>Chiradip Mandal</name>
      <url>http://www.chiradip.com</url>
    </developer>
  </developers>
)

