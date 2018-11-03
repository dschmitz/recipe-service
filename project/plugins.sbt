classpathTypes += "maven-plugin"
addSbtPlugin("com.eed3si9n"      % "sbt-buildinfo"          % "0.9.0")
addSbtPlugin("com.github.gseitz" % "sbt-release"            % "1.0.10")
addSbtPlugin("com.typesafe.sbt"  % "sbt-git"                % "1.0.0")
addSbtPlugin("com.typesafe.sbt"  % "sbt-license-report"     % "1.2.0")
addSbtPlugin("com.typesafe.sbt"  % "sbt-native-packager"    % "1.3.12")
addSbtPlugin("de.heikoseeberger" % "sbt-header"             % "5.0.0")
addSbtPlugin("io.gatling"        % "gatling-sbt"            % "3.0.0")
addSbtPlugin("org.scalastyle"    %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("org.scoverage"     % "sbt-scoverage"          % "1.5.1")
addSbtPlugin("org.wartremover"   % "sbt-wartremover"        % "2.3.3")
addSbtPlugin("com.lucidchart"    % "sbt-scalafmt"           % "1.15")
//addSbtPlugin("com.typesafe.sbt"  % "sbt-site"               % "1.3.1")
libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.25"
