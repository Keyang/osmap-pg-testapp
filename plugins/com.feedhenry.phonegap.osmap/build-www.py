

jsLibFolder="./www/js/"
cssLibFolder="./www/css/"
jsLibs=["leaflet.js","osmaptile.js"]
cssLibs=["leaflet.css"]
targetJSFile="./www/prod/prod.js"
targetCSSFile="./www/prod/prod.css"
def concatFiles(folder,files):
    content="/*this file is generated automatically. Please do not change. */\n"
    for f in files:
        path=folder+f
        fi=open(path,"r")
        content+=fi.read()
        content+="\n"
    return content 


def getJsLib():
    return concatFiles(jsLibFolder,jsLibs)

def getCssLib():
    return concatFiles(cssLibFolder,cssLibs)

def writeJsLib(content):
    f=open(targetJSFile,"w")
    f.write(content)

def writeCssLib(content):
    f=open(targetCSSFile,"w")
    f.write(content)

def main():
    print "Generating JS lib file"
    writeJsLib(getJsLib())
    print "Generating CSS lib file"
    writeCssLib(getCssLib())
    print "Done"


main()

