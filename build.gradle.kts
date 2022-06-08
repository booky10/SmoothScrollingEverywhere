import java.io.ByteArrayOutputStream

plugins {
    id("fabric-loom") version "0.12-SNAPSHOT"
    id("maven-publish")
}

fun getGitCommit(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

val archivesBaseName = "SmoothScrollingEverywhere"
version = "3.2.0+fabric.${getGitCommit()}"
group = "me.shedaniel"

repositories {
    maven("https://maven.shedaniel.me/")
}

dependencies {
    minecraft("com.mojang:minecraft:1.19")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:0.14.6")
    modImplementation(include("me.shedaniel.cloth:cloth-config-fabric:6.0.45")!!)
}

java {
    withSourcesJar()

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        artifactId = project.name.toLowerCase()
        from(components["java"])
    }
}

tasks {
    processResources {
        inputs.property("version", project.version)
        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }

    withType<JavaCompile> {
        options.encoding = Charsets.UTF_8.name()
        options.release.set(17)
    }

    jar {
        from("LICENSE") {
            rename { return@rename "${it}_smoothscrollingeverywhere" }
        }
    }
}

loom {
    accessWidenerPath.set(File(project.rootDir, "src/main/resources/smoothscrollingeverywhere.accesswidener"))
}
