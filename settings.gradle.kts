pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // Design tokens AAR from https://github.com/esteban505r/design-system (GitHub Packages).
        // Local auth: ~/.gradle/gradle.properties → gpr.user / gpr.key (PAT with read:packages).
        // CI: set GITHUB_ACTOR + GITHUB_TOKEN or ORG_GRADLE_PROJECT_gpr.user / ORG_GRADLE_PROJECT_gpr.key.
        maven {
            url = uri("https://maven.pkg.github.com/esteban505r/design-system")
            credentials {
                username =
                    providers.gradleProperty("gpr.user").orNull
                        ?: System.getenv("GITHUB_ACTOR")
                        ?: ""
                password =
                    providers.gradleProperty("gpr.key").orNull
                        ?: System.getenv("GITHUB_TOKEN")
                        ?: ""
            }
        }
    }
}

rootProject.name = "DesignSystemDemo"
include(":app")