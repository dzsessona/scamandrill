name := "scamandrill"

organization := "com.joypeg"

version := "1.0"

scalaVersion := "2.10.2"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq("spray repo" at "http://repo.spray.io/")

libraryDependencies ++= {
  val akkaV = "2.2.3"
  val sprayV = "1.3.0"
  Seq(
    "io.spray"            %   "spray-can"        % sprayV,
    "io.spray"            %   "spray-routing"    % sprayV,
    "io.spray"            %%  "spray-json"       % "1.2.5",
    "io.spray"            %   "spray-testkit"    % sprayV,
    "io.spray"            %   "spray-client"     % sprayV,
    "com.typesafe.akka"   %%  "akka-actor"       % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"     % akkaV,
    "com.typesafe"        %   "config"           % "1.2.1",
    "ch.qos.logback"      %   "logback-classic"  % "1.0.6",
    "org.specs2"          %%  "specs2"           % "2.2.3"    % "test"
  )
}

//seq(Revolver.settings: _*)
