#!/bin/sh
DESTINATION=/tools/thinj/devel
LIBDIR=$DESTINATION/lib

mkdir -p $DESTINATION || exit 1
mkdir -p $LIBDIR || exit 1

[ -d bin ] && rm -rf bin
mkdir bin

javac -sourcepath src -d bin `find src -name "*.java" -print` || exit 1

jar cf $LIBDIR/thinjrt.jar -C bin java || exit 1

