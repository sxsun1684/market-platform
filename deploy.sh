#!/usr/bin/env sh

# abort on errors
set -e

# navigate to the frontend directory
cd frontend

# build the project
npm run build

# navigate into the build output directory
cd dist

# if you are deploying to a custom domain
# echo 'www.example.com' > CNAME

git init
git add -A
git commit -m 'deploy'

# if you are deploying to https://<USERNAME>.github.io/<REPO>
git push -f git@github.com:sxsun1684/market-platform.git main:gh-pages

# return to the root directory
cd -
