# Appwrite KMP SDK

![Maven Central](https://img.shields.io/maven-central/v/io.appwrite/sdk-for-kmp.svg?color=green&style=flat-square)
![License](https://img.shields.io/github/license/appwrite/sdk-for-kmp.svg?style=flat-square)
![Version](https://img.shields.io/badge/api%20version-1.6.1-blue.svg?style=flat-square)
[![Build Status](https://img.shields.io/travis/com/appwrite/sdk-generator?style=flat-square)](https://travis-ci.com/appwrite/sdk-generator)
[![Twitter Account](https://img.shields.io/twitter/follow/appwrite_io?color=00acee&label=twitter&style=flat-square)](https://twitter.com/appwrite_io)
[![Discord](https://img.shields.io/discord/564160730845151244?label=discord&style=flat-square)](https://appwrite.io/discord)

**This SDK is compatible with Appwrite server version 0.7.x. For older versions, please check previous releases.**

Appwrite is an open-source backend as a service server that abstract and simplify complex and repetitive development tasks behind a very simple to use REST API. Appwrite aims to help you develop your apps faster and in a more secure way. Use the Flutter SDK to integrate your app with the Appwrite server to easily start interacting with all of Appwrite backend APIs and tools. For full API documentation and tutorials go to https://appwrite.io/docs

![Appwrite](https://appwrite.io/v1/images/console.png)

## Installation

### Gradle

Appwrite's Android SDK is hosted on Maven Central. In order to fetch the Appwrite SDK, add this to your root level `build.gradle(.kts)` file:

```groovy
repositories {      
    mavenCentral()
}
```

If you would like to fetch our SNAPSHOT releases, you need to add the SNAPSHOT maven repository to your `build.gradle(.kts)`:

```groovy
repositories {
    maven {
        url "https://s01.oss.sonatype.org/content/repositories/snapshots/"
    }
}
```

Next, add the dependency to your project's `build.gradle(.kts)` file:

```groovy
implementation("io.appwrite:sdk-for-kmp:0.0.0-SNAPSHOT")
```

### Maven
Add this to your project's `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>io.appwrite</groupId>
        <artifactId>sdk-for-kmp</artifactId>
        <version>0.0.0-SNAPSHOT</version>
    </dependency>
</dependencies>
```


## Contribution

This library is auto-generated by Appwrite custom [SDK Generator](https://github.com/appwrite/sdk-generator). To learn more about how you can help us improve this SDK, please check the [contribution guide](https://github.com/appwrite/sdk-generator/blob/master/CONTRIBUTING.md) before sending a pull-request.

## License

Please see the [BSD-3-Clause license](https://raw.githubusercontent.com/appwrite/appwrite/master/LICENSE) file for more information.
