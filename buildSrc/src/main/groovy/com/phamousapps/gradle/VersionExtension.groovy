package com.phamousapps.gradle

import org.gradle.api.Project

public class VersionExtension {

    def String VERSION_FILE_NAME = "versions.gradle"

    Map<String, Integer> mVersionMap = new HashMap<>();

    VersionExtension() {
        mVersionMap.put(Versions.MAJOR, 0);
        mVersionMap.put(Versions.MINOR, 0);
        mVersionMap.put(Versions.BUGFIX, 0);
        mVersionMap.put(Versions.ALPHA, 0);
    }

//    def int alpha
//    def int bugFix
//    def int minor
//    def int major

    def String DOT = "."

    def versionName() {
//        major + DOT + minor + DOT + bugFix + DOT + alpha

        StringBuilder builder = new StringBuilder();
        builder.append(mVersionMap.get(Versions.MAJOR)).append(DOT);
        builder.append(mVersionMap.get(Versions.MINOR)).append(DOT);
        builder.append(mVersionMap.get(Versions.BUGFIX)).append(DOT);
        builder.append(mVersionMap.get(Versions.ALPHA));
        return builder.toString();
    }

    def versionCode() {
//        major * 10**6 + minor * 10**4 + bugFix * 10**2 + alpha
        (mVersionMap.get(Versions.MAJOR) * 10**6) + (mVersionMap.get(Versions.MINOR) * 10**4) +
                (mVersionMap.get(Versions.BUGFIX) * 10**2) + (mVersionMap.get(Versions.ALPHA))
    }

    def loadVersions(Project project) {

        def versionFile = new File(project.projectDir, VERSION_FILE_NAME)
        versionFile.eachLine { line ->
            if (line.contains('=')) {
                def (key, value) = line.split('=').collect { it.trim() }

                println("Loadin Version")

                project.appVersion.mVersionMap.put(key, Integer.parseInt(value));

//                int version = Integer.parseInt(value)
//                switch (key) {
//                    case Versions.TASKS[0].versionVariable:
//                        project.appVersion.major = version
//                        break;
//                    case Versions.TASKS[1].versionVariable:
//                        project.appVersion.minor = version
//                        break;
//                    case Versions.TASKS[2].versionVariable:
//                        project.appVersion.bugFix = version
//                        break;
//                    case Versions.TASKS[3].versionVariable:
//                        project.appVersion.alpha = version
//                        break;
//                }
            }
        }
    }

    def saveVersions(Project project) {
        // Write to file
        new File(project.projectDir, VERSION_FILE_NAME).withWriter { out ->

            StringBuilder builder = new StringBuilder();

//            builder.append(Versions.TASKS[0].versionVariable).append('=').append(major).append('\n')
//            builder.append(Versions.TASKS[1].versionVariable).append('=').append(minor).append('\n')
//            builder.append(Versions.TASKS[2].versionVariable).append('=').append(bugFix).append('\n')
//            builder.append(Versions.TASKS[3].versionVariable).append('=').append(alpha)

            builder.append(Versions.MAJOR).append('=').append(mVersionMap.get(Versions.MAJOR)).append('\n')
            builder.append(Versions.MINOR).append('=').append(mVersionMap.get(Versions.MINOR)).append('\n')
            builder.append(Versions.BUGFIX).append('=').append(mVersionMap.get(Versions.BUGFIX)).append('\n')
            builder.append(Versions.ALPHA).append('=').append(mVersionMap.get(Versions.ALPHA))

            builder.toString();

            out.write(builder.toString())
        }
    }

    def bump(String versionPiece) {

        println("Bumping version")

        if (versionPiece == Versions.TASKS[0].versionVariable) {
//            major += 1;
//            minor = 0;
//            bugFix = 0;
//            alpha = 0;

            mVersionMap.put(Versions.MAJOR, mVersionMap.get(Versions.MAJOR) + 1);
            mVersionMap.put(Versions.MINOR, 0);
            mVersionMap.put(Versions.BUGFIX, 0);
            mVersionMap.put(Versions.ALPHA, 0);

        } else if (versionPiece == Versions.TASKS[1].versionVariable) {
//            minor += 1;
//            bugFix = 0;
//            alpha = 0;

            mVersionMap.put(Versions.MINOR, mVersionMap.get(Versions.MINOR) + 1);
            mVersionMap.put(Versions.BUGFIX, 0);
            mVersionMap.put(Versions.ALPHA, 0);

        } else if (versionPiece == Versions.TASKS[2].versionVariable) {
//            bugFix += 1;
//            alpha = 0;

            mVersionMap.put(Versions.BUGFIX, mVersionMap.get(Versions.BUGFIX) + 1);
            mVersionMap.put(Versions.ALPHA, 0);

        } else if (versionPiece == Versions.TASKS[3].versionVariable) {
//            alpha += 1;
            mVersionMap.put(Versions.ALPHA, mVersionMap.get(Versions.ALPHA) + 1);
        }

        println("finished bumping version: " + versionName())
    }
}