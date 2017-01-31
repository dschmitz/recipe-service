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
      val akka           = "2.4.16"
      val akkaHttp       = "10.0.3"
      val akkaHttpCors   = "0.1.10"
      val akkaHttpJson   = "1.11.0"
      val akkaLog4j      = "1.2.2"
      val circe          = "0.6.1"
      val swagger        = "0.9.1"
      val log4j          = "2.8"
      val scalaCheck     = "1.13.4"
      val scalaTest      = "3.0.1"
      val typesafeConfig = "1.3.1"
      val gatling        = "2.2.3"
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
    val typesafeConfig       = "com.typesafe"                 % "config"                    % Version.typesafeConfig
    val akkaLog4j            = "de.heikoseeberger"            %% "akka-log4j"               % Version.akkaLog4j
    val log4jCore            = "org.apache.logging.log4j"     % "log4j-core"                % Version.log4j
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
  publishSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "2.11.8",
    crossScalaVersions := Seq(scalaVersion.value, "2.11.8"),
    organization := "io.uport",
    licenses += ("Apache 2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    mappings.in(Compile, packageBin) += baseDirectory.in(ThisBuild).value / "LICENSE" -> "LICENSE",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding",
      "UTF-8"
    ),
    javacOptions ++= Seq(
      "-source",
      "1.8",
      "-target",
      "1.8"
    ),
    unmanagedSourceDirectories.in(Compile) := Seq(scalaSource.in(Compile).value),
    unmanagedSourceDirectories.in(Test) := Seq(scalaSource.in(Test).value),
    credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
  )

lazy val buildInfoSettings = Seq(
  buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion),
  buildInfoPackage := "io.uport.recipe.buildinfo",
  buildInfoOptions += BuildInfoOption.ToJson,
  buildInfoOptions += BuildInfoOption.BuildTime
)

lazy val scalafmtSettings =
  reformatOnCompileSettings ++
  Seq(
    formatSbtFiles := true,
    scalafmtConfig := Some(baseDirectory.in(ThisBuild).value / ".scalafmt.conf"),
    ivyScala := ivyScala.value
      .map(_.copy(overrideScalaVersion = sbtPlugin.value)) // TODO Remove once this workaround no longer needed (https://github.com/sbt/sbt/issues/2786)!
  )

lazy val gitSettings =
  Seq(
    git.useGitDescribe := true
  )

import de.heikoseeberger.sbtheader.HeaderPattern
import de.heikoseeberger.sbtheader.license.Apache2_0

lazy val headerSettings = Seq(
  headers := Map(
    "scala" -> Apache2_0("2017", "David Schmitz"),
    "conf" -> Apache2_0("2017", "David Schmitz", "#")
  )
)

lazy val dockerSettings = Seq(
  //daemonUser.in(Docker) := "root",
  maintainer.in(Docker) := "David Schmitz",
  dockerBaseImage := "java:8",
  dockerExposedPorts := Vector(8000),
  dockerRepository := Some("uport")
)

lazy val wartRemoverSettings = Seq(
  wartremoverErrors in (Compile, compile) ++= Warts.unsafe,
  wartremoverExcluded += sourceManaged.value / "main/sbt-buildinfo/BuildInfo.scala"
)

lazy val publishSettings = Seq(
  publishMavenStyle := true,
  publishTo := {
    val nexus = "http://127.0.0.1:48081/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "repository/maven-snapshots")
    else
      Some("releases" at nexus + "repository/maven-releases")
  }
)
