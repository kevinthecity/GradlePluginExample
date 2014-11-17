package com.phamousapps.gradle

import org.gradle.api.Project

/**
 * Extension model to add onto a project.
 */
public class VersionExtension {

    def String VERSION_FILE_NAME = "versions.gradle"

    Map<String, Integer> mVersionMap = new HashMap<>();
    String mOldVersion;

    VersionExtension() {
        mVersionMap.put(Version.MAJOR.key, 0);
        mVersionMap.put(Version.MINOR.key, 0);
        mVersionMap.put(Version.BUGFIX.key, 0);
        mVersionMap.put(Version.ALPHA.key, 0);

        mOldVersion = null;
    }

    /**
     * @return the version name as a period-separated string.
     */
    def versionName() {
        StringBuilder builder = new StringBuilder();
        builder.append(mVersionMap.get(Version.MAJOR.key)).append('.');
        builder.append(mVersionMap.get(Version.MINOR.key)).append('.');
        builder.append(mVersionMap.get(Version.BUGFIX.key)).append('.');
        builder.append(mVersionMap.get(Version.ALPHA.key));
        return builder.toString();
    }

    /**
     * @return the version code as a number.
     */
    def versionCode() {
        (mVersionMap.get(Version.MAJOR.key) * 10**6) + (mVersionMap.get(Version.MINOR.key) * 10**4) +
                (mVersionMap.get(Version.BUGFIX.key) * 10**2) + (mVersionMap.get(Version.ALPHA.key))
    }

    /**
     * Load the version file into the project.
     */
    def loadVersionsIntoProject(Project project) {
        def versionFile = new File(project.projectDir, VERSION_FILE_NAME)
        if (versionFile.exists()) {
            versionFile.eachLine { line ->
                if (line.contains('=')) {
                    def (key, value) = line.split('=').collect { it.trim() }
                    if (value == null || !value.isNumber()) {
                        value = 0;
                    }
                    project.appVersion.mVersionMap.put(key, Integer.valueOf(value));
                }
            }

        } else {
            // Create an empty file
            saveVersionFile(project);
        }

        mOldVersion = versionName();
    }

    /**
     * Save the current project version into the version file.
     */
    def saveVersionFile(Project project) {
        // Write to file
        new File(project.projectDir, VERSION_FILE_NAME).withWriter { out ->

            StringBuilder builder = new StringBuilder();
            builder.append(Version.MAJOR.key).append('=').append(mVersionMap.get(Version.MAJOR.key)).append('\n')
            builder.append(Version.MINOR.key).append('=').append(mVersionMap.get(Version.MINOR.key)).append('\n')
            builder.append(Version.BUGFIX.key).append('=').append(mVersionMap.get(Version.BUGFIX.key)).append('\n')
            builder.append(Version.ALPHA.key).append('=').append(mVersionMap.get(Version.ALPHA.key))

            out.write(builder.toString())
        }
    }

    /**
     * Bump the specified version by 1.
     */
    def bump(Version version) {

        switch (version) {
            case Version.MAJOR:
                mVersionMap.put(Version.MAJOR.key, mVersionMap.get(Version.MAJOR.key) + 1);
                mVersionMap.put(Version.MINOR.key, 0);
                mVersionMap.put(Version.BUGFIX.key, 0);
                mVersionMap.put(Version.ALPHA.key, 0);

                break;
            case Version.MINOR:
                mVersionMap.put(Version.MINOR.key, mVersionMap.get(Version.MINOR.key) + 1);
                mVersionMap.put(Version.BUGFIX.key, 0);
                mVersionMap.put(Version.ALPHA.key, 0);

                break;
            case Version.BUGFIX:
                mVersionMap.put(Version.BUGFIX.key, mVersionMap.get(Version.BUGFIX.key) + 1);
                mVersionMap.put(Version.ALPHA.key, 0);

                break;
            case Version.ALPHA:
                mVersionMap.put(Version.ALPHA.key, mVersionMap.get(Version.ALPHA.key) + 1);
                break;
        }

        println("Previous Version: $mOldVersion");
        println("Current Version: " + versionName());
        mOldVersion = versionName();
    }
}