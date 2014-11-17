package com.phamousapps.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // Add a VersionExtension object to te project variable
        project.extensions.create("appVersion", VersionExtension)

        // Load version from versions.gradle
        project.appVersion.loadVersionsIntoProject(project)

        // Create the tasks
        project.afterEvaluate {
            Version.values().each { version ->
                project.tasks.create(name: "$version.taskName") {
                    doLast {
                        // Bump version
                        project.appVersion.bump(version)

                        // Save File
                        project.appVersion.saveVersionFile(project);

                        // Load into build
                        project.android.applicationVariants.all { variant ->
                            variant.mergedFlavor.versionName = project.appVersion.versionName();
                            variant.mergedFlavor.versionCode = project.appVersion.versionCode();
                        }
                    }
                }
            }
        }
    }
}