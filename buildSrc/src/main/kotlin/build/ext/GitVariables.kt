package build.ext

import org.gradle.api.Project
import java.io.ByteArrayOutputStream
import git.compareBranches
import git.getCurrentBranch

fun Project.getGitLastCommitMessage(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = listOf("git", "log", "-1", "--pretty=format:%s")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

fun Project.getGitHash(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine = listOf("git", "rev-parse", "--short", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}

fun Project.diffBetweenStable(): String = compareBranches(
    "origin/stable", getCurrentBranch()
)

fun Project.diffBetweenDev(): String = compareBranches(
    "dev", getCurrentBranch()
)