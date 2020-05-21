resolvers ++= Seq(
  Resolver.mavenLocal,
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

lazy val commonSettings = Seq(
// Refine scalac params from tpolecat
  scalacOptions --= Seq(
    "-Xfatal-warnings"
  )
)

lazy val zioDeps = libraryDependencies ++= Seq(
  "dev.zio" %% "zio-streams"  % Version.zio,
  "dev.zio" %% "zio-test"     % Version.zio % "test",
  "dev.zio" %% "zio-test-sbt" % Version.zio % "test"
)

lazy val flinkDeps = libraryDependencies ++= Seq(
  "org.apache.flink" %% "flink-scala"           % Version.flink,
  "org.apache.flink" %% "flink-streaming-scala" % Version.flink
)

lazy val root = (project in file("."))
  .settings(
    organization := "Neurodyne",
    name := "flink",
    version := "0.0.1",
    scalaVersion := "2.12.10",
    maxErrors := 3,
    commonSettings,
    zioDeps,
    flinkDeps,
    testFrameworks := Seq(new TestFramework("zio.test.sbt.ZTestFramework"))
  )

// Aliases
addCommandAlias("rel", "reload")
addCommandAlias("com", "all compile test:compile it:compile")
addCommandAlias("fix", "all compile:scalafix test:scalafix")
addCommandAlias("fmt", "all scalafmtSbt scalafmtAll")

scalafixDependencies in ThisBuild += "com.nequissimus" %% "sort-imports" % "0.5.0"
