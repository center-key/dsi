# Design-Side Includes (DSI)

_Command line tool to convert legacy SSI code into static HTML_

[![License:MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/center-key/fetch-json/blob/main/LICENSE.txt)

## Setup
1. Install [Homebrew](https://brew.sh) and then install Java and Groovy:<br>
   `$ brew install openjdk groovy`<br>
2. Verify installs:<br>
   `$ java --version`<br>
   `$ groovy --version`<br>
3. Run the commands:<br>
   `$ mkdir -p ~/apps/dsi`<br>
   `$ cd ~/apps/dsi`<br>
   `$ curl --remote-name https://raw.githubusercontent.com/center-key/dsi/main/dist/dsi.jar`<br>
   `$ curl --remote-name https://raw.githubusercontent.com/center-key/dsi/main/dist/run.sh`<br>
   `$ chmod +x ~/apps/dsi/run.sh`<br>

Verify your setup:
```shell
$ ~/apps/dsi/run.sh --version
```

## Usage
Command format:
```shell
$ ~/apps/dsi/run.sh [SrcFolder] [FileName] [NewExt]
```
The parameters are optional.

| Parameter   | Description | Default Value |
| ----------- | ----------- | ------------- |
| `SrcFolder` | The "source folder" specifies the location of the base HTML files and fragment HTML files.  Valid folder names are dependent on your operating system.  Enclose the folder name in quotes if it includes spaces. | The current folder |
| `FileName`  | The "file name" specifies the name of the base HTML file (ex: `index.bhtml`).  Wildcards ("*") are supported | `*.bhtml` |
| `NewExt`    | The "new extension" specifies the file extension of the new files. | `.html` |

## Examples
Default command:
```shell
$ ~/apps/dsi/run.sh  #convert .bhtml files to .html
$ mv *.html ../webroot      #move .html files to web root
```
Command to generate `.jsp` files from `.bjsp` files in the `public_html` folder:
```shell
$ ~/apps/dsi/run.sh public_html "*.bjsp" ".jsp"
```

## Page Tags
Supported page tags:
   * `include`
   * `if`
   * `elif`
   * `else`
   * `endif`
   * `set`
   * `echo`

See the Specification Suite in the **spec** folder for the syntax.

---
[MIT License](LICENSE.txt)
