import sbt.Keys._

name := "vault"

lazy val uscalaVersion = "0.3.1"
lazy val specs2Version = "3.7.2"
lazy val circeVersion = "0.4.1"

lazy val commonSettings = Seq(
  version := "0.3.1",
  scalaVersion := "2.11.8",
  organization := "anselmer.vault",
  autoAPIMappings := true,
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  bintrayReleaseOnPublish := false,
  libraryDependencies ++= Seq(
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.3",
    "org.uscala" %% "uscala-result" % uscalaVersion,
    "org.uscala" %% "uscala-result-async" % uscalaVersion
  ),
  libraryDependencies ++= Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % circeVersion),
  scalacOptions ++= Seq(
    "-Xlint",
    "-Xcheckinit",
    "-Xfatal-warnings",
    "-unchecked",
    "-deprecation",
    "-feature",
    "-language:implicitConversions")
)

lazy val core = (project in file("core")).
    settings(name := "vault-core").
    settings(commonSettings: _*)

