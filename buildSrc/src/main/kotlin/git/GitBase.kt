package git

import org.gradle.api.Project
import java.io.ByteArrayOutputStream

object GitCommand {
    val log = listOf("git", "log")
    val prettyFormat = "--pretty=format:%s"
}

fun Project.executeGitCommand(cmd: List<String>): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = cmd
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

fun Project.executeGitCommand(vararg cmd: String): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = cmd.toList()
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

fun Project.getCurrentBranch() = executeGitCommand("git", "branch", "--show-current")