#!/bin/sh

OUT=$1
JLINK=$2
JMODS=$3

if [ -d $1 ]; then
  rm -rf $1
fi

$JLINK \
  --add-modules java.base,java.desktop,java.logging,java.management,jdk.crypto.cryptoki \
  --output $OUT \
  --module-path $JMODS
