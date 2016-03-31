import sbt.Keys._

name := "vault-auth"

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play-ws" % "2.5.1",
    "org.scalaz" %% "scalaz-core" % "7.2.1",
    "org.scalaz" %% "scalaz-concurrent" % "7.2.1",
    "org.specs2" %% "specs2-core" % "3.7.2" % "it"
  ),
  scalacOptions in Test ++= Seq("-Yrangepos"),
  scalacOptions ++= Seq(
    "-Xlint",
    "-Xcheckinit",
    "-Xfatal-warnings",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-language:implicitConversions")
) ++ Defaults.itSettings

lazy val core = (project in file("core")).settings(commonSettings: _*).configs(IntegrationTest)