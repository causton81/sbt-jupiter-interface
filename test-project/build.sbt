import sbt.Keys.libraryDependencies
import com.github.sbt.junit.jupiter.sbt.Import.JupiterKeys._


//logBuffered in Test := false

lazy val junit = (project in file("src/junit"))
  .enablePlugins(JvmPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "com.github.sbt" % "junit-interface" % "0.13.3" % "test",
      "junit" % "junit" % "4.13.2" % "test",
    ),
    testOptions += Tests.Argument(TestFrameworks.JUnit, "-a", "-v")
  )

lazy val jupiter = (project in file("src/jupiter"))
  .settings(
    libraryDependencies ++= Seq(
      "com.github.sbt.junit" % "jupiter-interface" % jupiterVersion.value % "test",
      "org.junit.jupiter" % "junit-jupiter-params" % junitJupiterVersion.value % "test"
    ),
    resolvers += Resolver.mavenLocal,
    Test / parallelExecution := true
  )

lazy val vintage = (project in file("src/vintage"))
  .settings(
    libraryDependencies ++= Seq(
      "com.github.sbt.junit" % "jupiter-interface" % jupiterVersion.value % "test",
      "org.junit.vintage" % "junit-vintage-engine" % junitVintageVersion.value % "test"
    ),
    resolvers += Resolver.mavenLocal
  )

lazy val cucumber = (project in file("src/cucumber"))
  .settings(
      Test / JupiterKeys.platformEngines := Seq("junit-platform-suite"),
      libraryDependencies ++= Seq(
      "com.github.sbt.junit" % "jupiter-interface" % jupiterVersion.value % "test",
      "org.junit.platform" % "junit-platform-suite" % "1.11.4" % "test",
      "io.cucumber" % "cucumber-junit-platform-engine" % "7.21.1" % "test",
      "io.cucumber" %% "cucumber-scala" % "8.25.1" % "test",
      "org.hamcrest" % "hamcrest" % "3.0" % "test",

    ),
    resolvers += Resolver.mavenLocal
  )

lazy val root = (project in file("."))
  .aggregate(junit)
  .aggregate(jupiter)
  .aggregate(vintage)
  .aggregate(cucumber)
