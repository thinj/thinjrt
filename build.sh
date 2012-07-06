#!/bin/sh

# Setup environment:
.  ../thinj/config.sh || exit 1


javac -sourcepath src -d bin `find src -name "*.java" -print` || exit 1

jar cf $LIBDIR/thinjrt.jar -C bin java || exit 1

