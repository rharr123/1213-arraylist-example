plugins {
    id("java-library")
    // Add the application plugin
    id("application")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4") // JUnit 5 for testing
}

// Custom source directories (instead of Gradle's default src/main/java, src/test/java)
sourceSets {
    main {
        java.srcDirs("src")
    }
    test {
        java.srcDirs("test")
    }
}

// Use Java 17 via Gradle Toolchains
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

// Tell JUnit to use the Jupiter engine
tasks.test {
    useJUnitPlatform()
}

// Configure the application plugin
application {
    // Define the main class to run
    mainClass.set("Application.Main")
}