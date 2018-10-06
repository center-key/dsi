# Design-Side Includes (DSI)

_Command line tool to convert legacy SSI code into static HTML_

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/center-key/fetch-json/blob/master/LICENSE.txt)

## Setup
1. Install [Java](https://www.oracle.com/technetwork/java/javase/downloads) and
1. install [Groovy](http://groovy-lang.org/download.html) (or just download the distribution into the `~/apps/groovy` folder)
1. Run command `mkdir -p ~/apps/dsi`
1. Download the two files from the [dist](dist) folder into the `~/apps/dsi` folder
1. Run command `chmod +x ~/apps/dsi/run.sh`

Verify your setup:
```shell
$ source ~/apps/dsi/run.sh --version
```

## Usage
Command format:
```shell
$ source ~/apps/dsi/run.sh [SrcFolder] [FileName] [NewExt]
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
$ source ~/apps/dsi/run.sh  #convert .bhtml files to .html
$ mv *.html ../webroot      #move .html files to web root
```
Command to generate `.jsp` files from `.bjsp` files in the `public_html` folder:
```shell
$ source ~/apps/dsi/run.sh public_html *.bjsp .jsp
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

See the Specification Cases in the **spec** folder for the syntax.

---
[MIT License](LICENSE.txt)
