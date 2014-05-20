#!/bin/bash
lessc ~/installed/bootstrap-3.1.1/less/bootstrap.less > ~/projects/github/sbt-neo-dependencies/jekyll/assets/css/bootstrap.css
jekyll serve -s ~/projects/github/sbt-neo-dependencies/jekyll -d ~/projects/github/sbt-neo-dependencies/jekyll/_site