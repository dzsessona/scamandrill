#!/bin/bash

(cd ./svc-x; sbt "neo4jLoadDependencies")
(cd ./svc-user; sbt ";project svc-user-client;neo4jLoadDependencies;project svc-user-core;neo4jLoadDependencies;project svc-user;neo4jLoadDependencies")

