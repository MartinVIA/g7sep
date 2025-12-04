# Push the generated docs/file_operations_data.js to the remote repo
# Usage: run this script from the repo root in PowerShell
# Make sure you have write access and your git credentials configured.

$repoRoot = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $repoRoot

$target = "docs/file_operations_data.js"
if (-Not (Test-Path $target)) {
    Write-Host "File not found: $target" -ForegroundColor Yellow
    exit 1
}

git add $target
$now = Get-Date -Format "yyyy-MM-dd HH:mm:ss"
$commitMsg = "Update file_operations_data.js - $now"

git commit -m $commitMsg
if ($LASTEXITCODE -ne 0) {
    Write-Host "Nothing to commit or commit failed." -ForegroundColor Yellow
    exit 0
}

git push
if ($LASTEXITCODE -eq 0) {
    Write-Host "Pushed $target to remote." -ForegroundColor Green
} else {
    Write-Host "Push failed. Check remote/credentials." -ForegroundColor Red
}
