package com.xwrdcenter.plugin.codegen

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.util.GradleVersion


class CodeGenPlugin() : Plugin<Project> {

    companion object {
        const val VERIFICATION_GROUP = "欣网研发中心"
        const val PLUGIN_EXTENSION_NAME = "codeGen"
    }

    lateinit var project: Project

    override fun apply(target: Project) {

        if(GradleVersion.current()< GradleVersion.version("4.0")){
            throw GradleException("This version of codeGen supports Gradle 4.0+ only. Please upgrade.")
        }

        target.let {
            project = target
            if (project.plugins.withType(JavaPlugin::class.java).isNotEmpty()) {
                project.extensions.create(PLUGIN_EXTENSION_NAME, CodeGenExtension::class.java)
                createCodeGenTask()
            } else {
                throw GradleException("You must apply the java plugin before using this plugin.")
            }
        }
    }

    private fun createCodeGenTask() {
        project.tasks.create(CodeGenTask.CODEGENTASK, CodeGenTask::class.java)
    }
}