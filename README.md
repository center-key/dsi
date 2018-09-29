# Design-Side Includes (DSI)

_Command line tool to convert legacy SSI code into static HTML_

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/center-key/fetch-json/blob/master/LICENSE.txt)

## Setup
1. Install [Java](https://www.oracle.com/technetwork/java/javase/downloads)
1. Execute `mkdir -p ~/apps/dsi`
1. Download the [dist/dsi.jar](dist/dsi.jar) executable JAR file into the folder `~/apps/dsi`

Verify your setup:
```shell
$ java -jar ~/apps/dsi/dsi.jar --version
```

## Usage
Command format:
```shell
$ java -jar ~/apps/dsi/dsi.jar [SrcFolder] [FileName] [NewExt]
```

| Parameter   | Description | Default Value |
| ----------- | ----------- | ------------- |
| `SrcFolder` | The "source folder" specifies the location of the base HTML files and fragment HTML files.  Valid folder names are dependent on your operating system.  Enclose the folder name in quotes if it includes spaces. | The current folder |
| `FileName`  | The "file name" specifies the name of the base HTML file (ex: `index.bhtml`).  Wildcards ("*") are supported | `*.bhtml` |
| `NewExt`    | The "new extension" specifies the file extension of the new files. | `.html` |

## Examples
Default command:
```shell
$ java -jar ~/apps/dsi/dsi.jar  #convert .bhtml files to .html
$ mv *.html ../webroot          #move .html files to web root
```
Command to generate `.jsp` files from `.bjsp` files in the `public_html` folder:
```shell
$ java -jar ~/apps/dsi/dsi.jar public_html *.bjsp .jsp
```

---
[MIT License](LICENSE.txt)
