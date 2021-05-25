classpathTypes += "maven-plugin"
addSbtPlugin("com.eed3si9n"      % "sbt-buildinfo"          % "0.10.0")
addSbtPlugin("com.github.gseitz" % "sbt-release"            % "1.0.13")
addSbtPlugin("com.typesafe.sbt"  % "sbt-git"                % "1.0.0")
addSbtPlugin("com.typesafe.sbt"  % "sbt-license-report"     % "1.2.0")
addSbtPlugin("com.typesafe.sbt"  % "sbt-native-packager"    % "1.8.1")
addSbtPlugin("de.heikoseeberger" % "sbt-header"             % "5.6.0")
addSbtPlugin("io.gatling"        % "gatling-sbt"            % "3.2.2")
addSbtPlugin("org.scalastyle"    %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("org.scoverage"     % "sbt-scoverage"          % "1.8.1")
addSbtPlugin("org.wartremover"   % "sbt-wartremover"        % "2.4.15")
addSbtPlugin("org.scalameta"     % "sbt-scalafmt"           % "2.4.2")

//addSbtPlugin("com.typesafe.sbt"  % "sbt-site"               % "1.3.1")
libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.30"
