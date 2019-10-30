plugins {
    id("org.asciidoctor.jvm.convert") version "3.0.0-alpha.3" apply false
}

subprojects {
    apply(plugin = "org.asciidoctor.jvm.convert")
    tasks {
        "asciidoctor"(AsciidoctorTask::class) {
            sourceDir = file("docs")
            sources(delegateClosureOf<PatternSet> {
                include("toplevel.adoc", "another.adoc", "third.adoc")
            })
            outputDir = file("build/docs")
        }
    }
}


tasks.register("finalize") {
    doLast {
        copy {
            from("public/toc")
            into("public")
            include("index.html")
        }
        copy {
            from("public")
            into("../vittali.github.io")
            include("**/*")
            println("copied !")
        }
    }
}