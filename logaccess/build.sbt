name := "iris-classifier-streams"
version := "1.0.0"
scalaVersion := "2.12.10"
exportJars := true

lazy val iris = (project in file("."))
    .settings(
        libraryDependencies ++= Seq(
            "org.scalatest" %% "scalatest" % "3.1.0" % Test,
            "org.apache.kafka" % "kafka-streams-test-utils" % "2.4.0" % Test,
            "org.apache.kafka" %% "kafka-streams-scala" % "2.4.0",
            "ml.combust.bundle" %% "bundle-ml" % "0.14.0",
            "ml.combust.mleap" %% "mleap-runtime" % "0.14.0",
            "com.jsuereth" %% "scala-arm" % "2.0",

        ),
        assemblyMergeStrategy in assembly := {
            case PathList("org", "apache", "spark", "unused", "UnusedStubClass.class") => MergeStrategy.first
            case "module-info.class" => MergeStrategy.discard
            case x =>
                val oldStrategy = (assemblyMergeStrategy in assembly).value
                oldStrategy(x)
        }
    )
    
