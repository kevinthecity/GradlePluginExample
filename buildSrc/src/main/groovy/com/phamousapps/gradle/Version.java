package com.phamousapps.gradle;

/**
 * Enum holding all of the versions to be bumped.
 */
public enum Version {

	// Example version 3.7.3.0

	ERA("bumpEra", "major"),
	MAJOR("bumpMajor", "major"),
	MINOR("bumpMinor", "minor"),
	ALPHA("bumpAlpha", "alpha");

	String taskName;
	String key;

	Version(String taskName, String versionKey) {
		this.taskName = taskName;
		this.key = versionKey;
	}
}
