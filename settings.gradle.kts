pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}


dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jcenter.bintray.com")
        }
        maven {
            url = uri("https://oss.sonatype.org/content/repositories/ksoap2-android-releases/")
        }
        maven{
            url = uri("https://jitpack.io")
        }

    }
}


rootProject.name = "SocialMediaApp"
include(":app")
 