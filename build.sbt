import SonatypeKeys._

sonatypeSettings

name := "scamandrill"

organization := "com.github.dzsessona"

profileName := "com.github.dzsessona"

description := "Scala client for Mandrill api"

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4") //, "2.9.3")

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq("spray repo" at "http://repo.spray.io/")

parallelExecution in Test := true

libraryDependencies ++= {
  val akkaV = "2.2.3"
  val sprayV = "1.3.0"
  Seq(
    "io.spray"          % "spray-can"       % sprayV,
    "io.spray"          % "spray-routing"   % sprayV,
    "io.spray"          %% "spray-json"     % "1.2.5",
    "io.spray"          % "spray-testkit"   % sprayV,
    "io.spray"          % "spray-client"    % sprayV,
    "com.typesafe.akka" %% "akka-actor"     % akkaV,
    "com.typesafe.akka" %% "akka-testkit"   % akkaV,
    "com.typesafe"      % "config"          % "1.2.1",
    "ch.qos.logback"    % "logback-classic" % "1.0.6"
  ) ++ Seq(
    "org.specs2"        %%  "specs2"        % "2.2.3"    % "test",
    "org.scalatest"     %%  "scalatest"     % "2.1.6"    % "test->*"
  )
}
//
//unmanagedSourceDirectories in Compile <+= (sourceDirectory in Compile, scalaVersion) {
//  (src,ver) => if (ver startsWith "2.9"){
//    src / "scala-2.9"
//  } else {
//    src / "scala-2.10"
//  }
//}
//
//libraryDependencies <<= scalaVersion { (scalaVer: String) =>
//  if (scalaVer startsWith "2.9") {
//    val akkaV = "2.0.5"
//    val sprayV = "1.0.1"
//    Seq(
//      "io.spray"            % "spray-can"       % sprayV,
//      "io.spray"            % "spray-routing"   % sprayV,
//      "io.spray"            %% "spray-json"     % "1.2.5",
//      "io.spray"            % "spray-testkit"   % sprayV,
//      "io.spray"            % "spray-client"    % sprayV,
//      "com.typesafe.akka"   % "akka-actor"      % akkaV,
//      "com.typesafe.akka"   % "akka-testkit"    % akkaV,
//      "com.typesafe"        % "config"          % "1.2.1",
//      "ch.qos.logback"      % "logback-classic" % "1.0.6"
//    ) ++ Seq(
//      //"org.specs2"          %%  "specs2"        % "2.2.3"    % "test",
//      //"org.scalatest"       %%  "scalatest"     % "2.1.6"    % "test->*"
//    )
//  } else {
//    val akkaV = "2.2.3"
//    val sprayV = "1.3.0"
//    Seq(
//      "io.spray"          % "spray-can"       % sprayV,
//      "io.spray"          % "spray-routing"   % sprayV,
//      "io.spray"          %% "spray-json"     % "1.2.5",
//      "io.spray"          % "spray-testkit"   % sprayV,
//      "io.spray"          % "spray-client"    % sprayV,
//      "com.typesafe.akka" %% "akka-actor"     % akkaV,
//      "com.typesafe.akka" %% "akka-testkit"   % akkaV,
//      "com.typesafe"      % "config"          % "1.2.1",
//      "ch.qos.logback"    % "logback-classic" % "1.0.6"
//    ) ++ Seq(
//      "org.specs2"        %%  "specs2"        % "2.2.3"    % "test",
//      "org.scalatest"     %%  "scalatest"     % "2.1.6"    % "test->*"
//    )
//  }
//}


publishArtifact in Test := false

publishMavenStyle := true

pomIncludeRepository := { _ => false }

pgpPublicRing := file("/Users/dzsessona/Documents/mykeys/diegopgp.asc")

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