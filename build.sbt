name := "poker-calc"

version := "0.1"

scalaVersion := "2.13.1"

organization := "com.dakers"


// For cards
libraryDependencies += "com.dakers" %% "cards" % "0.1"

// Test dependencies
libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.8"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "org.mockito" % "mockito-core" % "3.2.4" % Test