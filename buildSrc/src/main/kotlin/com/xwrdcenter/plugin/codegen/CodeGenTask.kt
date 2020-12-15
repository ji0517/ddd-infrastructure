package com.xwrdcenter.plugin.codegen

import com.xwrdcenter.plugin.codegen.CodeGenPlugin.Companion.VERIFICATION_GROUP
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.Input


open class CodeGenTask : Exec() {

    companion object {
        val CODEGENTASK = "codeGen"
    }

    init {
        group = VERIFICATION_GROUP
        description = "Generate code from the template"
        workingDir = project.file("${project.rootDir}/buildSrc/codegen")

    }

    override fun exec() {
        commandLine = listOf("java", "-jar", "codegeneration-1.0.0.jar", templateName, genTables)
        super.exec()
    }

    val templateName: String
        @Input get() {
            val extension = project.extensions.findByType(CodeGenExtension::class.java)
            extension?.templateName?.let {
                return extension.templateName!!
            }
            return "default"
        }

    val genTables: String
        @Input get() {
            val extension = project.extensions.findByType(CodeGenExtension::class.java)
            var tables = ""
            extension?.genTables?.let {
                for (item in it) {
                    tables += item + ","
                }
                tables = tables.substring(0..tables.length - 2)
                return tables
            }
            return ""
        }


}
