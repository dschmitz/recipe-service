classpathTypes += "maven-plugin"
addSbtPlugin("com.eed3si9n"      % "sbt-buildinfo"          % "0.7.0")
addSbtPlugin("com.github.gseitz" % "sbt-release"            % "1.0.6")
addSbtPlugin("com.typesafe.sbt"  % "sbt-git"                % "0.9.3")
addSbtPlugin("com.typesafe.sbt"  % "sbt-license-report"     % "1.2.0")
addSbtPlugin("com.typesafe.sbt"  % "sbt-native-packager"    % "1.3.1")
addSbtPlugin("de.heikoseeberger" % "sbt-header"             % "3.0.2")
addSbtPlugin("io.gatling"        % "gatling-sbt"            % "2.2.2")
addSbtPlugin("org.scalastyle"    %% "scalastyle-sbt-plugin" % "1.0.0")
addSbtPlugin("org.scoverage"     % "sbt-scoverage"          % "1.5.1")
addSbtPlugin("org.wartremover"   % "sbt-wartremover"        % "2.2.1")
addSbtPlugin("com.lucidchart"    % "sbt-scalafmt"           % "1.12")
//addSbtPlugin("com.typesafe.sbt"  % "sbt-site"               % "1.3.1")
libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.21"
