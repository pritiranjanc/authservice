###
# Multi-stage Dockerfile with custom JRE
###

# Step : build and package
ARG SCRIPT_VERSION=0.0.1
FROM deb11.4slim-maven3.8.6-jdk17:$SCRIPT_VERSION as BUILD
ARG ARTIFACT_ID=bootstrap
ARG VERSION=0.0.0
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline -Dversion=${VERSION}
COPY src/ /build/src/
RUN mvn package -Dversion=${VERSION}

RUN jlink --output "${ARTIFACT_ID}_jre" --add-modules $(jdeps --print-module-deps --ignore-missing-deps --multi-release 17 -recursive -cp $(mvn -Dversion=${VERSION} dependency:build-classpath -DincludeScope=compile -Dmaven.test.skip=true | awk '/Dependencies classpath/{getline; print}') target/"${ARTIFACT_ID}-${VERSION}.jar")

# Step : final docker image
FROM debian:11.4-slim
ARG ARTIFACT_ID=bootstrap
ARG VERSION=0.0.0
EXPOSE 8080
COPY --from=BUILD /build/target /opt/target
COPY --from=BUILD /build/"${ARTIFACT_ID}_jre" /opt/"${ARTIFACT_ID}_jre"
WORKDIR /opt/target
ENV ARTIFACT_ID=$ARTIFACT_ID
ENV VERSION=$VERSION
CMD ["sh", "-c", "/opt/${ARTIFACT_ID}_jre/bin/java -jar ${ARTIFACT_ID}-${VERSION}.jar"]
###