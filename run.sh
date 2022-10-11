#!/usr/bin/env bash

function finish {
  bash ./stop.sh
}

trap finish EXIT

SCRIPT_VERSION=0.0.1
VERSION=0.0.0
ARTIFACT_ID=bootstrap

if [ ! -z "$1" ]
then
  VERSION=$1
fi

ARTIFACT_ID=$(xpath -q -n -e 'project/artifactId/text()' pom.xml)

baseImageId=$(docker images -q deb11.4slim-maven3.8.6-jdk17:$SCRIPT_VERSION)
if [ -z "$baseImageId" ]
then
  docker image build -t deb11.4slim-maven3.8.6-jdk17:$SCRIPT_VERSION - < DockerfileJDK
fi

projectImageId=$(docker images -q "$ARTIFACT_ID:$VERSION")
if [ ! -z "$projectImageId" ]
then
  docker image rm "$ARTIFACT_ID:$VERSION"
fi

docker image build -t "$ARTIFACT_ID:$VERSION" --build-arg SCRIPT_VERSION=$SCRIPT_VERSION --build-arg ARTIFACT_ID="$ARTIFACT_ID" --build-arg VERSION="$VERSION" .

rm -f $(pwd)/logs/*

docker run --rm -d -p 8080:8080 --env-file ./.env -v $(pwd)/logs:/opt/target/logs --name "$ARTIFACT_ID"-"$VERSION" "$ARTIFACT_ID":"$VERSION"

docker logs --follow "$ARTIFACT_ID"-"$VERSION"