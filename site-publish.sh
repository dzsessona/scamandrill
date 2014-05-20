#!/bin/bash
DIR="/Users/dzsessona/Projects/github/sbt-neo-dependencies"
cd $DIR
SOURCE_BRANCH="docs"
DEST_BRANCH="gh-pages"

#------------------------------------------------------------
# BUILD THE SITE, COPY IT TO TMP AND COMMIT THE SOURCE BRANCH 
# -----------------------------------------------------------
git checkout $SOURCE_BRANCH
jekyll build -d $DIR/jekyll-site/ -s $DIR/jekyll/ --config $DIR/_config.yml
cp -R $DIR/jekyll-site/ /tmp/jekyll-site
git add -A
git commit -m "documentation and site"

#--------------------------------------------------------
# SWITCH TO GH-PAGES, COPY THE SITE FROM TEMP AND  COMMIT 
#--------------------------------------------------------
git checkout $DEST_BRANCH
cp -R /tmp/jekyll-site/* ./
git add -A
git commit -m "Published updated site"

#-------------------
# PUSH BOTH BRANCHES
#-------------------
git push origin $DEST_BRANCH
git checkout $SOURCE_BRANCH
git push origin $SOURCE_BRANCH