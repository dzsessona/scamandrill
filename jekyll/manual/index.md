---
layout: manual
active: manual
title: Sbt Neo Dependencies - manual
---

<h2 id="installation">Installation</h2>

**The artifacts of this plugin are uploaded in Maven Central.** 

As from the [sbt documentation](http://www.scala-sbt.org/release/docs/Detailed-Topics/Resolvers.html) 
the DefaultMavenRepository is the main Maven repository at http://repo1.maven.org/maven2/ and is included by default. 
Therefore you don't need to add any resolvers to your build definition; you can simply add the plugin the *project/plugins.sbt* file as follow: 

``` addSbtPlugin("com.github.dzsessona" %% "sbt-neo-dependencies" % "version")  ```

Replace **version** with the last version of this plugin. All versions for this plugin can be found [here]({{ site.url }}/versions). In order to enable
the tasks of this plugin in your project you also have to modify your build definition; An sbt build definition can contain files ending in .sbt, located in the base directory, 
and files ending in .scala, located in the project/ subdirectory the base directory. In case you are confused about the difference between build.sbt and Build.scala read [this](http://www.scala-sbt.org/release/docs/Getting-Started/Full-Def.html).


<h3 id="buildsbt" style="margin-top: 20px;">build.sbt</h4>

In your build definition you need to import the settings defined in the plugin, and override the settings keys for **neo4jInternalName, neo4jInternalOrgs, neo4jTagsLabels** as in this example snippet:

```
import com.joypeg.graphdeps.neo4j.Neo4jGraphDependencies

Neo4jGraphDependencies.neo4jDepsSetting

neo4jInternalName := "ACOMPANY"

neo4jInternalOrgs := Seq("a.company")

neo4jTagsLabels  := Map("service" -> "Service")
```

Look up the section **Settings** for more information about what each of this setting represent.

<h3 id="buildscala" style="margin-top: 20px;">project/Build.scala</h4>

If you have a full definition (maybe with multiple subproject) you can import the settings defined 
in the plugin (and override the settings keys for **neo4jInternalName, neo4jInternalOrgs, neo4jTagsLabels**) for all the
subproject as follow:

```
import sbt._
import Keys._
import com.joypeg.graphdeps.neo4j.Neo4jGraphDependencies
import com.joypeg.graphdeps.neo4j.Neo4jGraphDependencies._

object BuildSettings {

  val neoSettings = neo4jDepsSetting ++ Seq(
    neo4jInternalName := "ACOMPANY",
    neo4jInternalOrgs := Seq("a.company"),
    neo4jTagsLabels   := Map("client" -> "Service_Client", "web" -> "Web")
  )

  val buildSettings = Defaults.defaultSettings ++ Seq(
    ...
  ) ++ neoSettings
}
```

Note that if your build.scala contains multiple projects the previous snippet to apply to each of the subproject. 
Because of the way that the plugin loads the dependencies into the graph database though, 
I will suggest you to run the plugin for multiple project definition not from the root project, 
but by selecting the project and call the task, rather than calling the task on the root project. 

For example, for the [full Build.scala](http://github.com/dzsessona/sbt-neo-dependencies/blob/docs/examples/svc-user/project/Build.scala) definition of the svc-user example, 
that contains the subproject core, service and client, I load the dependencies with the following [script](http://github.com/dzsessona/sbt-neo-dependencies/blob/docs/examples/load_acompany.sh)

Look up the **Settings** section for more information about what each of this setting.

<h2 id="tasks">Tasks</h2>

Installing the plugin will add to your project 3 new tasks, **neo4jShowDependencies** , **neo4jWriteDependencies** and **neo4jLoadDependencies** . By the way, you can see all the 
tasks available in the sbt console by running 

```sbt> tasks -V```

<img src="{{ site.url }}/assets/img/taskv.png" width="600">

### neo4jShowDependencies
This task will print on screen the cypher statements that would be used to create the nodes (projects) and relations (dependencies) in the neo4j database. 
This task is expecially useful for development, but also is a good way to explore how the graph would be generated for a particular project.  

### neo4jWriteDependencies
This task will print on file the cypher statements that would be used to create the nodes (projects) and relations (dependencies) in the neo4j database. 
Calling this task just create the file, the location of which is defined in the plugin itself as:

```neo4jCypherScript := (baseDirectory in Compile).value / "neodependencies" / "nodesandrelations.cyp"```

Also note that the task to load the dependencies **dependsOn** this task. 

### neo4jLoadDependencies
This is the task that creates the nodes (projects) and relations (dependencies) in the neo4j database. 
All it does is calling the neo4jWriteDependencies and than passing that file to the neo4j console. Because of that it assumes that 

1. Your neo4j server is running
2. You have set a variable **NEO4J_HOME** in your environment

If any of these requirements is not satisfied you will get a message of error and you won't be able to load your dependencies in the graph. 

<h2 id="settings">Settings</h2>

The plugin defines some settings that allow you to customize how your dependency graph will look like, these settings are:

1. **neo4jInternalName** the name of your company
2. **neo4jInternalOrgs** a list of packages names used to distinguish between internal and external dependencies
3. **neo4jTagsLabels** a map of tags to apply to the internal dependencies

We will look at each of these settings in details, referencing the example projects svc-x and svc-user. For convenience the build definition
can be found n these links: [svc-x build.sbt](https://github.com/dzsessona/sbt-neo-dependencies/blob/docs/examples/svc-x/build.sbt)
 and [svc-user Build.scala](https://github.com/dzsessona/sbt-neo-dependencies/blob/docs/examples/svc-user/project/Build.scala)

The resulting graph of these two project it is in the picture below:

<img src="{{ site.url }}/assets/img/reference.png" width="700">

### neo4jInternalName

As you can see from the picture the dependencies are divided in Internal and External. Overriding the setting 
**neo4jInternalName** will basically create the node with of type specified. In short, the name of your company
will appear for all the nodes (projects) that are internal. In this example the setting has been defined as:

```neo4jInternalName := "ACOMPANY"``` 

I strongly advise you to ovveride this settings in your definition, considering that the default is set to *"Company"*

### neo4jInternalOrgs

Reading the previous paragraph you might have asked yourself how can you tell the plugin what is an internal node and what is not.
The **neo4jInternalOrgs** does exactly that; it is a Sequence of string that match your organization name(s). In the example I basically
just want to tell the plugin that every project that start with the organization name *a.company* is an internal project. So I defined as:

```neo4jInternalOrgs := Seq("a.company")```

Note that you can add more organization names if you have more than one, and also that is a good rule to use domain names
for the organizations, which are unique, therefore the plugin matches everything that *startsWith* "a.company" as opposed to
*contains*. Also note that by default this sequence is empty.

### neo4jTagsLabels

You might have noticed that in the picture there are different types of Internal nodes, such as web (purple) and client (blue). 
Forget about the colors for the moment (we will talk about it later in the usage section), the important bit is that the plugin
allows you to set a few rules based on the name of the project to **tag** it. If you are wise and choose a consistent naming strategy
for your projects than you can basically say (as in this example)

- add the tag **Web** to every internal project that contains the word *web* its name
- add the tag **Client** to every internal project that contains the word *client* its name

In this way you have a visual representation of the different internal projects dependencies. To achieve this in this example I 
declared the map of **neo4jTagsLabels** as:

```neo4jTagsLabels   := Map("client" -> "Client", "web" -> "Web")```

Note that by default this map is empty.