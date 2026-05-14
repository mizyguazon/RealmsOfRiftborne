$ErrorActionPreference = "Stop"

$workspace = Split-Path -Parent $MyInvocation.MyCommand.Path
$srcDir = Join-Path $workspace "src"
$binDir = Join-Path $workspace "bin"

New-Item -ItemType Directory -Force -Path $binDir | Out-Null

$sources = Get-ChildItem -Path $srcDir -Recurse -Filter *.java | ForEach-Object { $_.FullName }

if (-not $sources) {
    throw "No Java source files found under $srcDir"
}

javac -d $binDir $sources
