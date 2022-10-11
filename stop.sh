#!/usr/bin/env bash

SCRIPT_VERSION=0.0.1
VERSION=0.0.0
ARTIFACT_ID=bootstrap

if [ ! -z "$1" ]
then
  VERSION=$1
fi

ARTIFACT_ID=$(xpath -q -n -e 'project/artifactId/text()' pom.xml)

docker stop "$ARTIFACT_ID"-"$VERSION"