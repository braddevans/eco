plugins {
    id("io.papermc.paperweight.userdev") version "1.1.14"
}

group = "com.willfp"
version = rootProject.version

dependencies {
    paperDevBundle("1.17.1-R0.1-SNAPSHOT")
    compileOnly("net.kyori:adventure-api:4.9.1")
    compileOnly("net.kyori:adventure-text-serializer-gson:4.8.1")
}

configurations.create("mapped")

tasks {
    build {
        dependsOn(reobfJar)
    }

    artifacts {
        this.add("mapped", reobfJar)
    }
}