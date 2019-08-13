Modules.apply {

    fun registerProject(subfolder: String, vararg projects: String) {
        projects.forEach { p ->
            project(p).projectDir = File(rootDir, "sources/$subfolder/${p.removePrefix(":")}")
        }
    }

    // root
    include(app, buildSrc)

    // source-base
    include(core)
    registerProject("base", core)

    // features
//    include(feature1, ..., featureN)
//    registerProject("feature", feature1, ..., featureN)
}
