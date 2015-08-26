import SonatypeKeys._

sonatypeSettings

name := "scamandrill"

organization := "com.github.dzsessona"

profileName := "com.github.dzsessona"

description := "Scala client for Mandrill api"

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.10.5", "2.11.7")

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq("spray repo" at "http://repo.spray.io/")

parallelExecution in Test := true

libraryDependencies ++= {
  val akkaV = "2.3.12"
  val sprayV = "1.3.2"
  Seq(
    "io.spray"          %% "spray-json"       % "1.3.2",
    "com.typesafe.akka" %% "akka-actor"       % akkaV,
    "com.typesafe.akka" %% "akka-http-experimental" % "1.0",
    "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "1.0",
    "com.typesafe"      % "config"            % "1.2.1",
    "org.slf4j"         % "slf4j-api"         % "1.7.7"
  ) ++ Seq(
    "org.specs2"        %%  "specs2"          % "2.3.13"    % "test",
    "org.scalatest"     %%  "scalatest"       % "2.1.6"     % "test->*",
    "com.typesafe.akka" %% "akka-testkit"     % akkaV % "test"
  )
}

publishArtifact in Test := false

publishMavenStyle := true

pomIncludeRepository := { _ => false }

//pgpPublicRing := file("/Users/dzsessona/Documents/mykeys/diegopgp.asc")

pomExtra := (
  <url>http://github.com/dzsessona/scamandrill</url>
    <licenses>
      <license>
        <name>Apache License 2.0</name>
        <url>http://opensource.org/licenses/Apache-2.0</url>
      </license>
    </licenses>
    <scm>
      <connection>scm:git:github.com/dzsessona/scamandrill.git</connection>
      <developerConnection>scm:git:git@github.com:dzsessona/scamandrill.git</developerConnection>
      <url>github.com/dzsessona/scamandrill</url>
    </scm>
    <developers>
      <developer>
        <id>dzsessona</id>
        <name>Diego Zambelli Sessona</name>
        <url>https://www.linkedin.com/in/diegozambellisessona</url>
      </developer>
    </developers>
  )
