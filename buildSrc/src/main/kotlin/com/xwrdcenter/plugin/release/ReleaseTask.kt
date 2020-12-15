package com.xwrdcenter.plugin.release

import com.xwrdcenter.plugin.release.XWReleasePlugin.Companion.PLUGIN_EXTENSION_NAME
import com.xwrdcenter.plugin.release.XWReleasePlugin.Companion.VERIFICATION_GROUP
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.Input


open class ReleaseTask() : DefaultTask() {

    @Input
    var sourceFilePath = "${project.buildDir}/libs"
    @Input
    var destFilePath = "${project.rootDir}/deploy"

    init {
        group = VERIFICATION_GROUP
        description = "${project.name} release"

        if (project.tasks.findByName("bootJar") != null) {
//            println("bootJar");
            this.dependsOn("bootJar")
        } else if (project.tasks.findByName("bootRepackage") != null) {
//            println("bootJar");
            this.dependsOn("bootRepackage")
        } else if (project.tasks.findByName("war") != null) {
//            println("war");
            this.dependsOn("war")
        } else if (project.tasks.findByName("shadowJar") != null) {
//            println("jar");
            this.dependsOn("shadowJar")
        } else if (project.tasks.findByName("jar") != null) {
//            println("jar");
            this.dependsOn("jar")
        } else {
            println("毛，什么包都打不了了")
        }
    }

    companion object {
        val XWRELEASE = "xwRelease"
    }

    @TaskAction
    fun release() {
        val releasePluginConfiguration = project.property(PLUGIN_EXTENSION_NAME) as ReleaseExtension
        if (!releasePluginConfiguration.sourceFilePath.equals("")) {
            sourceFilePath = releasePluginConfiguration.sourceFilePath
        }

        if (!releasePluginConfiguration.destFilePath.equals("")) {
            destFilePath = releasePluginConfiguration.destFilePath
        }

        project.copy {
            from(sourceFilePath).include("*.jar", "*.zip", "*.war")
            into(destFilePath)

        }
    }

}
