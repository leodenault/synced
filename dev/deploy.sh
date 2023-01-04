#!/bin/bash

CWD=$(pwd)
OUT_DIR=$1
PLATFORM=$2
PLATFORM_SCRIPT=$3
PLATFORM_BINARY=$4
LICENSES_ZIP=$5
JLINK_ZIP=$6
DATA_DEPS_DIR=$7
OUT_FILE=$8

TEMP_DIR="$OUT_DIR/Synced"

mkdir $TEMP_DIR

cp $PLATFORM_BINARY $TEMP_DIR
cp $PLATFORM_SCRIPT $TEMP_DIR

unzip -q $JLINK_ZIP -d $TEMP_DIR

unzip -q $LICENSES_ZIP -d $TEMP_DIR

rsync \
  -r -D \
  --links \
  --exclude=/external \
  --exclude=/java \
  --exclude=*.jar \
  $DATA_DEPS_DIR \
  $TEMP_DIR

pushd "$OUT_DIR" > /dev/null
zip -qr "$CWD/$OUT_FILE" Synced
popd > /dev/null

rm -rf $TEMP_DIR
