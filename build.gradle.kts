import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion by System.getProperties()
    kotlin("jvm").version("$kotlinVersion").apply(false)
}



allprojects {
    configurations.all {
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
        exclude("org.springframework.boot", "spring-boot-starter-jetty")
        exclude("org.springframework.boot", "spring-boot-starter-logging")
        exclude("ch.qos.logback", "logback-classic")
        exclude("ch.qos.logback", "logback-core")
        exclude(module = "junit")

//        exclude(group = "io.netty")
    }
}

subprojects {

    group = "com.xwtec"
    version = "1.0"

    apply {
        plugin("java")
        plugin("idea")
        plugin("eclipse")
//        plugin("kotlin")
        plugin("java-library")
        from("${rootDir}/dependencyDefinitions.gradle.kts")
    }

    repositories {
//        mavenLocal()
        //        maven("http://218.94.54.84:30014/repository/maven-snapshots/")
        maven("https://maven.aliyun.com/nexus/content/groups/public")
        mavenCentral()

        flatDir {
            dirs("${rootDir}/libs")
        }
    }



//    //如果需要指定不一样的jdk编译，这里的代码级别也需要改一下
    configure<JavaPluginConvention> {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks {

        withType<Jar> {
            manifest {
                attributes("Manifest-Version" to "1.0")
                attributes("Created-By" to "com.xwrdcenter")
            }
        }

        withType<Javadoc>().all {
            options.encoding = "UTF-8"
        }

        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }

        withType<KotlinCompile>().all {
            kotlinOptions {
                jvmTarget = "1.11"
                freeCompilerArgs = kotlin.collections.listOf("-Xjsr305=strict")
            }
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    val printdepsTask = task("printdeps") {
        doLast {
            println(project.extra["deps"])
        }
    }

}