#!/bin/sh
PATH_OF_PROJECT=$(dirname "$0")/../
cd $PATH_OF_PROJECT
./gradlew run -Pargs="-v $*"
