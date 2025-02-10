
/*
 * sbt-jupiter-interface
 *
 * Copyright (c) 2017, Michael Aichler.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

name := "test-project"
Test / JupiterKeys.platformEngines := Seq("junit-platform-suite")
libraryDependencies ++= Seq(
  "com.github.sbt.junit" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test,
  "org.junit.platform" % "junit-platform-suite" % "1.11.4" % Test,
  "io.cucumber" % "cucumber-junit-platform-engine" % "7.21.1" % Test,
  "io.cucumber" %% "cucumber-scala" % "8.25.1" % Test,
  "org.hamcrest" % "hamcrest" % "3.0" % Test
)

testOptions += Tests.Argument("--with-types")

val checkTestDefinitions = taskKey[Unit]("Tests that the test is discovered properly")

checkTestDefinitions := {
  val definitions = (Test / definedTests).value

  assert(definitions.nonEmpty, "Did not find any test !")
  assert(definitions.length == 1, "Found more than the one test (" + definitions.length + ")!")

  streams.value.log.info("Test name = " + definitions.head.name)
  assert(definitions.head.name == "interop.CucumberTestSuite", "Failed to discover/name the unit test!")
}
