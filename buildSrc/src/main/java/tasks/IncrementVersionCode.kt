package tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import props.incrementVersionCode

open class IncrementVersionCode : DefaultTask() { // Keep the file open otherwise gradle won't be able to generate the proxy class.

    init {
        group = "env-tasks" // This will be the group name for your task.
        description = "incrementVersionCode"
    }

    @TaskAction // Marks a function as the action to run when the task is executed.
    fun run() {
        incrementVersionCode("version.properties")
    }
}