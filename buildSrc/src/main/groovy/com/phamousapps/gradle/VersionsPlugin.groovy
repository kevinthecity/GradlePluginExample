package com.phamousapps.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class VersionsPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        // Add a VersionExtension object to te project variable
        project.extensions.create("appVersion", VersionExtension)

        // Load version from versions.gradle
        project.appVersion.loadVersions(project)

        // Create the tasks
        project.afterEvaluate {

            Versions.TASKS.each { versionTask ->
                project.tasks.create(name: "$versionTask.taskName") {
                    doLast {
                        // Bump version
                        project.appVersion.bump(versionTask.versionVariable)

                        // Save File
                        project.appVersion.saveVersions(project);

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