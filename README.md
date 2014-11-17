# GradlePluginExample

Example of a Gradle Plugin that can manage your version numbers. Based on a talk given by Lisa Neigut at Droidcon London 
[(video)](https://skillsmatter.com/skillscasts/5625-gradlin-plugging-it-in-for-build-success)
[(slides)](https://speakerdeck.com/niftynei/gradlin-plugging-it-in-for-build-success).

The main code to look at in this project is located in the `buildSrc` folder. The only other thing to look at is the `build.gradle` file in the app folder. Everything else is just boilerplate.

When adding the `buildSrc` folder to your project, manually add the folders 
- `buildSrc`
  - `src`
    - `main`
      - `groovy`
      - `resources`
      
After that, do a Gradle sync, and then your project should auto-generate the `.iml` and the `local.properties` files. At that point, you must also create a `build.gradle` for this plugin. At a minimum, it must have the same contents as the one in this projet, but you can flesh it out with more.

NOTE: This project does not contain details on uploading the plugin to a remote repo or maven. This demo is only for adding the plugin code locally to your project.

## Running

To see the new tasks you've added, type `./gradlew tasks` into the console, and notice that there are now 4 new tasks under "Other Tasks". You can run them individually (e.g. `./gradlew bumpMajor`), or you can run it as part of a full build (e.g. `./gradlew bumpMajor assembleDebug`).

Example output:

```

./gradlew tasks
...
Other tasks
-----------
bumpAlpha
bumpBugFix
bumpMajor
bumpMinor

./gradlew bumpMajor
...
:app:bumpMajor
Previous Version: 1.0.0.0
Current Version: 2.0.0.0


./gradlew bumpMinor
...
:app:bumpMinor
Previous Version: 2.0.0.0
Current Version: 2.1.0.0


./gradlew bumpMajor
...
:app:bumpMajor
Previous Version: 2.1.0.0
Current Version: 3.0.0.0
```

