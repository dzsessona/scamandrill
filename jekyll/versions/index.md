---
layout: nothome
active: versions
title: Sbt Neo Dependencies - versions
---
## 1.1.0 <small> Released on 2/4/2014</small> 

Set the version of the project in the node properties, also add extra properties (as the cross compiled version) 
when adding a new project. If the node exists already (maybe it is a dependency of another project), no extra nodes
will be created, instead the same node will be matched, adding the properties. 
This is done using the cypher syntax MERGE - ON CREATE - ON MATCH

[Maven central for scala 2.10 link](http://central.maven.org/maven2/com/github/dzsessona/sbt-neo-dependencies_2.10_0.13/1.1.0/)

[Maven central for scala 2.9 link](http://central.maven.org/maven2/com/github/dzsessona/sbt-neo-dependencies_2.9.2_0.12/1.1.0/)


## 1.0.0 <small> Released on 1/4/2014</small> 

If a project is cross compiled for multiple versions of scala, the plugin will add the list of versions as a property of the node.

[Maven central for scala 2.10 link](http://central.maven.org/maven2/com/github/dzsessona/sbt-neo-dependencies_2.10_0.13/1.0.0/)

[Maven central for scala 2.9 link](http://central.maven.org/maven2/com/github/dzsessona/sbt-neo-dependencies_2.9.2_0.12/1.0.0/)

## 0.1.2 <small> Released on 29/3/2014</small> 

This is the first version, with the tags to show, write and load the nodes and relations
into neo4j. It also adds the extra information on each dependency. For sbt 0.12.x and 0.13.x , scala 2.9 and 2.10

[Maven central for scala 2.10 link](http://central.maven.org/maven2/com/github/dzsessona/sbt-neo-dependencies_2.10_0.13/0.1.2/)

[Maven central for scala 2.9 link](http://central.maven.org/maven2/com/github/dzsessona/sbt-neo-dependencies_2.9.2_0.12/0.1.2/)
