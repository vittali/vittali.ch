#!/bin/zsh
asciidoctor -D ../build **/*.adoc
#rsync -a --verbose --perms --times  --prune-empty-dirs --delete-after images ../build