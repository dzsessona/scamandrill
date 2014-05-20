import com.joypeg.graphdeps.neo4j.Neo4jGraphDependencies

Neo4jGraphDependencies.neo4jDepsSetting

name := "svc-x-web"

organization := "a.company"

version := "1.0"

scalaVersion := "2.9.2"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies ++= { Seq(
    "a.company"                 %% "svc-user-client"    % "1.0",
    "ch.qos.logback"            % "logback-classic"	% "1.0.6",
    "c3p0" 			% "c3p0"		% "0.9.1.2"
  )
}

neo4jInternalName := "ACOMPANY"

neo4jInternalOrgs := Seq("a.company")

neo4jTagsLabels  := Map("client" -> "Client", "web" -> "Web")
