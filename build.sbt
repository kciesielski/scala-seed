val zioVersion        = "1.0.0-RC18"
val catsVersion       = "2.1.1"
val catsEffVersion    = "2.1.2"
val monixVersion      = "3.1.0"
val scalaTestVersion  = "3.0.8"
val scalaCheckVersion = "1.14.0"

resolvers += Resolver.sonatypeRepo("releases")
resolvers += Resolver.sonatypeRepo("snapshots")

lazy val root = (project in file("."))
  .settings(
    organization := "com.softwaremill",
    name := "kc-seed",
    version := "1.0.0-SNAPSHOT",
    scalaVersion := "2.13.1",
    libraryDependencies ++= Seq(
      "dev.zio"              %% "zio"                 % zioVersion,
      "org.typelevel"        %% "cats-core"           % catsVersion,
      "org.typelevel"        %% "cats-effect"         % catsEffVersion,
      "org.scalatest"        %% "scalatest"           % scalaTestVersion % Test,
      "org.scalacheck"       %% "scalacheck"          % scalaCheckVersion % Test,
      "com.github.chocpanda" %% "scalacheck-magnolia" % "0.3.1" % Test,
      "io.chrisdavenport"    %% "cats-scalacheck"     % "0.2.0" % Test
    )
  )
  .settings(
    wartremoverWarnings in (Test, compile) --= Seq(
      Wart.Var,
      Wart.MutableDataStructures,
      Wart.Null
    ),
    wartremoverWarnings in (IntegrationTest, compile) := Warts.all
      .diff(smlWartremoverTestCompileExclusions)
      .diff(Seq(Wart.Var, Wart.MutableDataStructures))
  )
