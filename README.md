# Flag Capture Kata

  - Author: Sébastien Mosser
  - Version: 0.1 (Feb 2017)

## Objectives

  - [ ] Start a Java project as a Maven multi-modules project
  - [ ] Trigger the execution of a program with `mvn exec:java`
  - [ ] Slice a complex requirements into small and vertical slices
  - [ ] Unit test while developing business code

## Problem Statement

> A map is composed of a rectangular grid (height x width), which contains at least one flag somewhere in the map. A flag is uniquely identified by a name. A player is able to explore the map and capture flag when it discovers one. The player always spawns on the top-left corner of the map, facing East.
    

## Starting Development with Maven

First of all, we create a Git repository (this very one), and clone it on the local computer

```
azrael:polytech mosser$ git clone https://github.com/polytechnice-si/3A_GL_FlagCapture.git
Cloning into '3A_GL_FlagCapture'...
remote: Counting objects: 4, done.
remote: Compressing objects: 100% (4/4), done.
remote: Total 4 (delta 0), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (4/4), done.
```

Then, we create 3 maven projects:

  - A root project (type: pom), which contains the two others and acts as parent
  - A project to store the `game`, as a java program (type: jar)
  - A project to store the `player` (type: jar), with a dependencies requiring the `game`

```
azrael:polytech mosser$ cd 3A_GL_FlagCapture/
azrael:3A_GL_FlagCapture mosser$ mkdir -p game/src/main/java game/src/test/java 
azrael:3A_GL_FlagCapture mosser$ mkdir -p player/src/main/java player/src/test/java 
azrael:3A_GL_FlagCapture mosser$ touch pom.xml game/pom.xml player/pom.xml
```  

The _Project Object Models_ (POMs) associated to these 3 projects are quite usual:

  - [`game/pom.xml`]() is a classical java project, loading JUnit for unit testing;
  - [`player.pom.xml`]() is another java project, loading `game` to access to the map
  - [`pom.xml`]() is a _pom_ project, i.e., a maven project that does not produce executable code, but instead is used to model hierarchy among projects.

 We can test through the command line that the projects are valid, executing `mvn clean package` returns a build success. Thus, we can import the project in our favorite IDE (aka, IntelliJ), and start project implementation.
 
## Building the Game

The game is quite simple. It exposes an interface to create and explore maps:


 
