val catsEffectV      = "3.3.0"
val catsV            = "2.7.0"
val fs2V             = "3.2.2"
val munitCatsEffectV = "1.0.6"
val munitV           = "0.7.29"

lazy val root = (project in file("modules/aoc")).settings(
  organization := "ca.valencik",
  name         := "AdventOfCode",
  version      := "0.1.0",
  scalaVersion := "3.1.0",
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core"           % catsV,
    "org.typelevel" %% "cats-effect"         % catsEffectV,
    "co.fs2"        %% "fs2-core"            % fs2V,
    "co.fs2"        %% "fs2-io"              % fs2V,
    "org.scalameta" %% "munit"               % munitV           % Test,
    "org.typelevel" %% "munit-cats-effect-3" % munitCatsEffectV % Test
  )
)
