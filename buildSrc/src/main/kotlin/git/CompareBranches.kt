package git

import git.GitCommand.log
import git.GitCommand.prettyFormat
import org.gradle.api.Project

fun Project.compareBranches(main: String, second: String) = executeGitCommand(
    log + "$main..$second" + prettyFormat
).replace("bump app version", "")