import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version "3.1.0"
    java
}

val buildFrontend = tasks.register<NpmTask>("buildFrontend") {
    this.npmCommand.set(listOf("run"))
    args.set(listOf("build"))
    dependsOn(tasks.npmInstall)
    inputs.dir(project.fileTree("src").exclude("**/*.spec.ts"))
    inputs.dir("node_modules")
    inputs.dir("public")
    inputs.files("package.json", "vite.config.json", "index.html", "tsconfig.json")
    outputs.dir("${projectDir}/dist")
}
