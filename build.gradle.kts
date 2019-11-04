import org.asciidoctor.gradle.jvm.AsciidoctorTask

plugins {
    id("org.asciidoctor.jvm.convert") version "2.3.0"
}

subprojects {

    tasks {
        "asciidoctor"(AsciidoctorTask::class) {
            setSourceDir(file("doc"))
            sources(delegateClosureOf<PatternSet> {
                include("*.adoc")
            })
            //setOutputDir(file("../public/${projectDir.getName()}")
            //setSeparateOutputDirs(false)
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
