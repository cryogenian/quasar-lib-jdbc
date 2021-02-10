ThisBuild / crossScalaVersions := Seq("2.12.12")
ThisBuild / scalaVersion := (ThisBuild / crossScalaVersions).value.head

ThisBuild / githubRepository := "quasar-lib-jdbc"

ThisBuild / homepage := Some(url("https://github.com/precog/quasar-lib-jdbc"))

ThisBuild / scmInfo := Some(ScmInfo(
  url("https://github.com/precog/quasar-lib-jdbc"),
  "scm:git@github.com:precog/quasar-lib-jdbc.git"))

ThisBuild / publishAsOSSProject := true

val DoobieVersion = "0.9.2"

lazy val quasarVersion =
  Def.setting[String](managedVersions.value("precog-quasar"))

// Include to also publish a project's tests
lazy val publishTestsSettings = Seq(
  Test / packageBin / publishArtifact := true)

lazy val root = project
  .in(file("."))
  .settings(noPublishSettings)
  .aggregate(core)

lazy val core = project
  .in(file("core"))
  .settings(
    name := "quasar-lib-jdbc",
    libraryDependencies ++= Seq(
      "com.precog" %% "quasar-connector" % quasarVersion.value,

      "org.slf4s" %% "slf4s-api" % "1.7.25",

      "org.tpolecat" %% "doobie-core" % DoobieVersion,
      "org.tpolecat" %% "doobie-hikari" % DoobieVersion,

      "io.chrisdavenport" %% "log4cats-slf4j" % "1.1.1",

      "org.specs2" %% "specs2-core" % "4.10.5" % Test
    ))
  .evictToLocal("QUASAR_PATH", "api", true)
  .evictToLocal("QUASAR_PATH", "connector", true)
