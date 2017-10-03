// *****************************************************************************
// Projects
// *****************************************************************************

lazy val `recipe-service` =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin, BuildInfoPlugin, GatlingPlugin, GitVersioning, JavaAppPackaging, DockerPlugin)
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.akkaActor,
        library.akkaCluster,
        library.akkaHttp,
        library.akkaHttpCore,
        library.akkaHttpCors,
        library.akkaHttpCirce,
        library.circeCore,
        library.circeGeneric,
        library.circeParser,
        library.typesafeConfig,
        library.akkaLog4j,
        library.log4jCore,
        library.log4j,
        library.log4jSlf4jImpl,
        library.swagger,
        library.swaggerJaxrs,
        library.scalaCheck,
        library.scalaTest,
        library.akkaMultiNodeTestkit,
        library.akkaHttpTestkit,
        library.gatling,
        library.gatlingHighcharts
      )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {

    object Version {
      val scala          = "2.12.3"
      val akka           = "2.5.6"
      val akkaHttp       = "10.0.10"
      val akkaHttpCors   = "0.2.2"
      val akkaHttpJson   = "1.18.0"
      val akkaLog4j      = "1.5.0"
      val circe          = "0.8.0"
      val swagger        = "0.11.0"
      val swaggerJaxrs   = "1.5.16"
      val log4j          = "2.9.1"
      val scalaCheck     = "1.13.5"
      val scalaTest      = "3.0.4"
      val typesafeConfig = "1.3.1"
      val gatling        = "2.3.0"
    }

    val akkaActor            = "com.typesafe.akka"            %% "akka-actor"               % Version.akka
    val akkaCluster          = "com.typesafe.akka"            %% "akka-cluster"             % Version.akka
    val akkaHttp             = "com.typesafe.akka"            %% "akka-http"                % Version.akkaHttp
    val akkaHttpCore         = "com.typesafe.akka"            %% "akka-http-core"           % Version.akkaHttp
    val akkaHttpCors         = "ch.megard"                    %% "akka-http-cors"           % Version.akkaHttpCors
    val akkaHttpCirce        = "de.heikoseeberger"            %% "akka-http-circe"          % Version.akkaHttpJson
    val circeCore            = "io.circe"                     %% "circe-core"               % Version.circe
    val circeGeneric         = "io.circe"                     %% "circe-generic"            % Version.circe
    val circeParser          = "io.circe"                     %% "circe-parser"             % Version.circe
    val swagger              = "com.github.swagger-akka-http" %% "swagger-akka-http"        % Version.swagger
    val swaggerJaxrs         = "io.swagger"                   % "swagger-jaxrs"             % Version.swaggerJaxrs
    val typesafeConfig       = "com.typesafe"                 % "config"                    % Version.typesafeConfig
    val akkaLog4j            = "de.heikoseeberger"            %% "akka-log4j"               % Version.akkaLog4j
    val log4jCore            = "org.apache.logging.log4j"     % "log4j-core"                % Version.log4j
    val log4jSlf4jImpl       = "org.apache.logging.log4j"     % "log4j-slf4j-impl"          % Version.log4j
    val log4j                = "org.apache.logging.log4j"     % "log4j-api"                 % Version.log4j
    val scalaCheck           = "org.scalacheck"               %% "scalacheck"               % Version.scalaCheck % Test
    val scalaTest            = "org.scalatest"                %% "scalatest"                % Version.scalaTest % Test
    val akkaMultiNodeTestkit = "com.typesafe.akka"            %% "akka-multi-node-testkit"  % Version.akka % Test
    val akkaHttpTestkit      = "com.typesafe.akka"            %% "akka-http-testkit"        % Version.akkaHttp % Test
    val gatling              = "io.gatling"                   % "gatling-test-framework"    % Version.gatling % "test,it"
    val gatlingHighcharts    = "io.gatling.highcharts"        % "gatling-charts-highcharts" % Version.gatling % "test,it"
  }

// *****************************************************************************
// Settings
// *****************************************************************************        |

lazy val settings =
commonSettings ++
scalafmtSettings ++
wartRemoverSettings ++
gitSettings ++
headerSettings ++
buildInfoSettings ++
dockerSettings ++
publishSettings ++
releaseSettings ++
scoverageSettings

lazy val commonSettings =
  Seq(
    scalaVersion       := library.Version.scala,
    crossScalaVersions := Seq(scalaVersion.value, library.Version.scala),
    organization       := "io.uport",
    licenses += ("Apache 2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    mappings.in(Compile, packageBin) += baseDirectory.in(ThisBuild).value / "LICENSE" -> "LICENSE",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding",
      "UTF-8",
      "-feature",
      "-Ywarn-dead-code",
      "-Ywarn-numeric-widen",
      "-Ywarn-value-discard",
      "-Ywarn-unused"
    ),
    javacOptions ++= Seq(
      "-source",
      "1.8",
      "-target",
      "1.8"
    ),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test)    := Seq(scalaSource.in(Test).value)
    //incOptions := incOptions.value.withNameHashing(true),
  )

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile         := true,
    scalafmtOnCompile.in(Sbt) := true,
    scalafmtVersion           := "1.3.0"
  )

lazy val buildInfoSettings = Seq(
  buildInfoKeys    := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
  buildInfoPackage := "io.uport.recipe.buildinfo",
  buildInfoOptions += BuildInfoOption.BuildTime
)

lazy val gitSettings =
  Seq(
    git.useGitDescribe := true
  )

import de.heikoseeberger.sbtheader.License._

lazy val headerSettings = Seq(
  headerLicense := Some(ALv2("2017", "David Schmitz"))
)

lazy val dockerSettings = Seq(
  //daemonUser.in(Docker) := "nobody",
  maintainer.in(Docker) := "David Schmitz",
  dockerBaseImage       := "java:8",
  dockerExposedPorts    := Vector(8000),
  dockerRepository      := Some("uport")
)

lazy val wartRemoverSettings = Seq(
  wartremoverErrors in (Compile, compile) ++= Warts.unsafe
//  wartremoverErrors in (Compile, compile) ++= Warts.allBut(Wart.Any, Wart.StringPlusAny),
//  wartremoverExcluded ++= (sourceManaged ** "*.scala").value.get
)

lazy val scoverageSettings = Seq(
  coverageMinimum       := 60,
  coverageFailOnMinimum := false
)

lazy val publishSettings = Seq(
  publishMavenStyle       := true,
  publishArtifact in Test := false,
  publishTo := {
    val nexus = "http://127.0.0.1:48081/"
    if (isSnapshot.value) {
      Some("snapshots".at(nexus + "repository/maven-snapshots"))
    } else {
      Some("releases".at(nexus + "repository/maven-releases"))
    }
  }
)

import sbtrelease.ReleasePlugin.autoImport._
import ReleaseTransformations._

lazy val releaseSettings = Seq(
  releaseProcess := Seq[ReleaseStep](
    checkSnapshotDependencies,
    inquireVersions,
    runTest,
    setReleaseVersion,
    commitReleaseVersion,
    tagRelease,
    //publishDocker,
    publishArtifacts,
    setNextVersion,
    commitNextVersion,
    pushChanges
  )
)
