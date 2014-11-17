GradlePluginExample
===================

Example of a Gradle Plugin that can manage your version numbers. Based on a talk given by [Lisa Neigut at Droidcon London](https://skillsmatter.com/skillscasts/5625-gradlin-plugging-it-in-for-build-success).

The main code to look at in this project is located in the `buildSrc` folder. The only other thing to look at is the `build.gradle` file in the app folder. Everything else is just boilerplate.

When adding the `buildSrc` folder to your project, manually add the folders 
- `buildSrc`
  - `src`
    - `main`
      - `groovy`
      - `resources`
      
After that, do a Gradle sync, and then your project should auto-generate the `.iml` and the `local.properties` files. At that point, you must also create a `build.gradle` for this plugin. At a minimum, it must have the same contents as the one in this projet, but you can flesh it out with more.
