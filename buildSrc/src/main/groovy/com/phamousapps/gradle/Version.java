package com.phamousapps.gradle;

/**
 * Created by kevingrant on 11/16/14.
 */
public enum Version {

	MAJOR("bumpMajor", "major"),
	MINOR("bumpMinor", "minor"),
	BUGFIX("bumpBugFix", "bugFix"),
	ALPHA("bumpAlpha", "alpha");

	String taskName;
	String versionKey;

	Version(String taskName, String versionKey) {
		this.taskName = taskName;
		this.versionKey = versionKey;
	}
}
