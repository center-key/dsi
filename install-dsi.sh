#!/bin/bash
###########################################
# Install: Design-Side Includes (DSI)     #
# MIT ~ https://github.com/center-key/dsi #
###########################################

# To run this installer:
#     $ curl --silent https://raw.githubusercontent.com/center-key/dsi/main/install-dsi.sh | bash

installFolder=~/apps/dsi

echo
echo "Install: Design-Side Includes (DSI)"
echo "==================================="
which brew || { echo "[ABORT] Install Homebrew (https://brew.sh) first."; exit; }
brew install openjdk groovy
java --version
groovy --version
mkdir -pv $installFolder
cd $installFolder
curl --remote-name https://raw.githubusercontent.com/center-key/dsi/main/dist/dsi.jar
curl --remote-name https://raw.githubusercontent.com/center-key/dsi/main/dist/run.sh
chmod +x run.sh
pwd
ls -o $installFolder
$installFolder/run.sh --version
echo
