FROM amazoncorretto:17.0.4-al2

ARG SBT_VERSION=1.7.1

# prevent this error: java.lang.IllegalStateException: cannot run sbt from root directory without -Dsbt.rootdir=true; see sbt/sbt#1458
WORKDIR /app

COPY . /app

# install sbt
RUN \
  rm -f /etc/yum.repos.d/bintray-rpm.repo && \
  curl -L https://www.scala-sbt.org/sbt-rpm.repo > sbt-rpm.repo && \
  mv sbt-rpm.repo /etc/yum.repos.d/ && \
  yum install -y sbt-$SBT_VERSION-0.noarch && \
  sbt sbtVersion

CMD sbt compile && sbt run
