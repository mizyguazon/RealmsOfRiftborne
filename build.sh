#!/bin/zsh

set -euo pipefail

mkdir -p bin
find src -name "*.java" -print0 | xargs -0 javac -d bin
