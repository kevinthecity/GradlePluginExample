package com.phamousapps.gradle

class Versions {

    static String MAJOR = "major";
    static String MINOR = "minor";
    static String BUGFIX = "bugFix";
    static String ALPHA = "alpha";

    static VersionTask[] TASKS = [
            new VersionTask("bumpMajor", MAJOR),
            new VersionTask("bumpMinor", MINOR),
            new VersionTask("bumpBugFix", BUGFIX),
            new VersionTask("bumpAlpha", ALPHA)]

    static class VersionTask {

        VersionTask(String taskName, String versionVariable) {
            this.taskName = taskName;
            this.versionVariable = versionVariable;
            this.value = 0;
        }

        def String taskName
        def String versionVariable
        def int value
    }
}