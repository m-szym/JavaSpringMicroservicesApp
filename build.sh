#!/usr/bin/env bash

function main() {
    export PATH="$PATH:/Applications/IntelliJ IDEA.app/Contents/plugins/maven/lib/maven3/bin"
    cd ./missions/; sh ./build.sh; cd ..
    cd ./targets/; sh ./build.sh; cd ..
    cd ./gateway/; sh ./build.sh; cd ..
    cd ./ng-front/; sh ./build.sh; cd ..
    cd ./eureka-discovery/; sh ./build.sh; cd ..
}

main "$@"
