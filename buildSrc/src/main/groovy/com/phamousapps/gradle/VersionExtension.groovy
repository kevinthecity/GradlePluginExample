package com.phamousapps.gradle

import org.gradle.api.Project

/**
 * Extension model to add onto a project.
 */
public class VersionExtension {

    def String VERSION_FILE_NAME = "versions.gradle"

    Map<Version, Integer> mVersionMap = new HashMap<>();
    String mOldVersion;

    VersionExtension() {
        mVersionMap.put(Version.MAJOR, 0);
        mVersionMap.put(Version.MINOR, 0);
        mVersionMap.put(Version.BUGFIX, 0);
        mVersionMap.put(Version.ALPHA, 0);

        mOldVersion = null;
    }

    /**
     * @return the version name as a period-separated string.
     */
    def versionName() {
        StringBuilder builder = new StringBuilder();
        builder.append(mVersionMap.get(Version.MAJOR)).append('.');
        builder.append(mVersionMap.get(Version.MINOR)).append('.');
        builder.append(mVersionMap.get(Version.BUGFIX)).append('.');
        builder.append(mVersionMap.get(Version.ALPHA));
        return builder.toString();
    }

    /**
     * @return the version code as a number.
     */
    def versionCode() {
        (mVersionMap.get(Version.MAJOR) * 10**6) + (mVersionMap.get(Version.MINOR) * 10**4) +
                (mVersionMap.get(Version.BUGFIX) * 10**2) + (mVersionMap.get(Version.ALPHA))
    }

    /**
     * Load the version file into the project.
     */
    def loadVersionsIntoProject(Project project) {
        def versionFile = new File(project.projectDir, VERSION_FILE_NAME)
        versionFile.eachLine { line ->
            if (line.contains('=')) {
                def (key, value) = line.split('=').collect { it.trim() }
                project.appVersion.mVersionMap.put(key, Integer.parseInt(value));
            }
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
            builder.append(Version.MAJOR).append('=').append(mVersionMap.get(Version.MAJOR)).append('\n')
            builder.append(Version.MINOR).append('=').append(mVersionMap.get(Version.MINOR)).append('\n')
            builder.append(Version.BUGFIX).append('=').append(mVersionMap.get(Version.BUGFIX)).append('\n')
            builder.append(Version.ALPHA).append('=').append(mVersionMap.get(Version.ALPHA))

            out.write(builder.toString())
        }
    }

    /**
     * Bump the specified version by 1.
     */
    def bump(Version version) {

        switch (version) {
        case Version.MAJOR:
            mVersionMap.put(Version.MAJOR, mVersionMap.get(Version.MAJOR) + 1);
            mVersionMap.put(Version.MINOR, 0);
            mVersionMap.put(Version.BUGFIX, 0);
            mVersionMap.put(Version.ALPHA, 0);

            break;
        case Version.MINOR:
            mVersionMap.put(Version.MINOR, mVersionMap.get(Version.MINOR) + 1);
            mVersionMap.put(Version.BUGFIX, 0);
            mVersionMap.put(Version.ALPHA, 0);

            break;
        case Version.BUGFIX:
            mVersionMap.put(Version.BUGFIX, mVersionMap.get(Version.BUGFIX) + 1);
            mVersionMap.put(Version.ALPHA, 0);

            break;
        case Version.ALPHA:
            mVersionMap.put(Version.ALPHA, mVersionMap.get(Version.ALPHA) + 1);
            break;
        }

        println("Previous Version: $mOldVersion");
        println("Current Version: " + versionName());
        mOldVersion = versionName();
    }
}