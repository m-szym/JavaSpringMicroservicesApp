#!/usr/bin/env bash

function main() {
    JAVA_HOME=/Users/msz/Library/Java/JavaVirtualMachines/temurin-23
    mvn clean verify -e
    docker build -t aui-missions .
}

main "$@"
