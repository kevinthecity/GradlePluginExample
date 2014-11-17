package com.phamousapps.gradle;

/**
 * Enum holding all of the versions to be bumped.
 */
public enum Version {

	MAJOR("bumpMajor", "major"),
	MINOR("bumpMinor", "minor"),
	BUGFIX("bumpBugFix", "bugFix"),
	ALPHA("bumpAlpha", "alpha");

	String taskName;
	String key;

	Version(String taskName, String versionKey) {
		this.taskName = taskName;
		this.key = versionKey;
	}
}
