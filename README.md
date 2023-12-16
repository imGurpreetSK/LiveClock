## LiveClock
This simple application shows user the system time with an exit-enter animation for each time element.
The date shown is in format "HH:mm:ss" (24 hour time starting at 00:00:00).

### Notes
1. [Important] The project requires Java 17 [toolchain](https://docs.gradle.org/current/userguide/toolchains.html) 
   to build. If absent, the correct toolchain is automatically downloaded during first run.
2. JUnit5 used for testing.
3. RxJava3 used as reactive streams spec implementation for asynchronous work.
4. Manual dependency inject, given the size of project.
5. StrictMode to monitor jank, unlawful disk operations.
6. Min SDK for project is 26 - if support for 21 (Lollipop) is required, please enable [core library desugaring](https://developer.android.com/studio/write/java8-support#library-desugaring) as the app depends on `java.time` 
   package available on Java versions 8 and above.

### Demo
[liveclock.webm](https://github.com/imGurpreetSK/LiveClock/assets/11317262/5a8fc3e0-0df6-44c4-bb22-704f31bbb24c)
