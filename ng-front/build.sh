#!/usr/bin/env bash

function main() {
    npm install
    npm run build
    docker build -t aui-front .
}

main "$@"
