---
layout: manual
active: manual
title: Sbt Neo Dependencies - manual
---

<h2 id="installation">Installation</h2>

**The artifacts of this project are uploaded in Maven Central.** 

As from the [sbt documentation](http://www.scala-sbt.org/release/docs/Detailed-Topics/Resolvers.html) 
the DefaultMavenRepository is the main Maven repository at http://repo1.maven.org/maven2/ and is included by default. 
Therefore you don't need to add any resolvers to your build definition; you can simply add the scamandrill client 
in your build definition **libraryDependencies** as follow: 

``` "com.github.dzsessona"     %% "scamandrill"          % "version"  ```

Replace **version** with the last version of this project (1.0.0 at the time of writing this document). 
All versions for this plugin can be found [here]({{ site.url }}/versions). In case you are confused about the difference
 between build.sbt and Build.scala read [this](http://www.scala-sbt.org/release/docs/Getting-Started/Full-Def.html).

<h4 id="configuration" style="margin-top: 20px;">Configuration</h4>

All the requests that you can make to Mandrill API require that you provide your api key. In fact, consider the following
snippet of code with the bare minimum instructions to make a request to madrill api:

```
import com.joypeg.scamandrill.client.MandrillAsyncClient
...
MandrillAsyncClient.usersPing(MKey(key = "THEKEY"))
```

So you need to replace 'THEKEY' with your own api key. But because you usually always use the same key in your application,
you can simply write the previous intruction as : ```MandrillAsyncClient.usersPing(MKey())``` . Scamandrill in this case
uses the default key in the configuration. The configuration uses the conventions of [typesafe config](https://github.com/typesafehub/config) and should 
specify the key and the default timeout for the blocking client (discussed later). So an example of **application.conf** should look like:

```
Mandrill {
    key="MANDRILLKEY",
    timoutInSeconds=5
}
```

If you look at the output of the [tests for users calls](https://github.com/dzsessona/scamandrill/blob/master/src/test/scala/com/joypeg/scamandrill/client/UserCallsTest.scala) for example 
you will see that if you will get an error message from mandrill if the key is not passed to method, or if a configuration file is not defined. Btw I choose this 
approach to alllow the user to override the default key for each call (specifying it when building your request object). Note that the same is valid for all calls: 

<img src="{{ site.url }}/assets/img/usertest.png" width="600">


<h2 id="calls">Calls and usage</h2>

<h2 id="example">Example</h2>

<h2 id="banchmarks">Benchmarks</h2>
