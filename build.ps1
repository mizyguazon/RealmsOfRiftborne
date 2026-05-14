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

$resourceRoot = Join-Path $srcDir "com\ror\models\assets"
$resourceTarget = Join-Path $binDir "com\ror\models\assets"

if (Test-Path $resourceRoot) {
    New-Item -ItemType Directory -Force -Path $resourceTarget | Out-Null
    Copy-Item -Path (Join-Path $resourceRoot "*") -Destination $resourceTarget -Recurse -Force
}
