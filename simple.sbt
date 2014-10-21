import AssemblyKeys._ // put this at the top of the file

assemblySettings

name := "Simple Project"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.1.0" % "provided"

libraryDependencies += "org.apache.spark" % "spark-mllib_2.10" % "1.1.0" % "provided"

lazy val hello = taskKey[String]("An example task")

hello := { println("Hello!"); "HelloString"}

