import scala.collection.mutable.HashSet

object csvGenerator{
  
  def nextIndex(dataArraySize: Int) : Int = {
    val rnd = new scala.util.Random
    return rnd.nextInt(dataArraySize)
  }

  def readInputUrlData(urlFileName:String) : Array[String] = {
    val urlIn = new java.io.BufferedReader(new java.io.FileReader(urlFileName))
    val urlArray = new Array[String](45)
    var nextLineUrl = urlIn.readLine
    var arrayIndex = 0
    while ((nextLineUrl != null)){
      urlArray(arrayIndex) = nextLineUrl
      nextLineUrl = urlIn.readLine
      arrayIndex += 1
    }
    urlArray
  }

  def readInputLabelData(labelFileName:String) : Array[String] = {
    val labelIn = new java.io.BufferedReader(new java.io.FileReader(labelFileName))
    val labelArray = new Array[String](50)
    var nextLineLabel = labelIn.readLine
    var arrayIndex = 0
    while ((nextLineLabel != null)){
      labelArray(arrayIndex) = nextLineLabel
      nextLineLabel = labelIn.readLine
      arrayIndex += 1
    }
    labelArray
  }

  def pickIndices(dataArray: Array[String]) : Array[Int] = {
    var urlSet = new HashSet[Int]

    while (urlSet.size < 5){
      urlSet += nextIndex(dataArray.size)
    }
    urlSet.toArray
  }
  
  def retEntry(dataArray:Array[String],indices:Array[Int]): String = {
    var entry = dataArray(indices(0)) +","+dataArray(indices(1))+","+dataArray(indices(2))+","+dataArray(indices(3))+","+dataArray(indices(4))+"\n"
    entry
  }

  def createOutputFiles(urlArray:Array[String],labelArray:Array[String], outputUrlFileName:String, outputLabelFileName:String) = {
    val urlOut = new java.io.FileWriter(outputUrlFileName)
    val labelOut = new java.io.FileWriter(outputLabelFileName)
    urlOut.write("articleUrl1,articleUrl2,articleUrl3,articleUrl4,articleUrl5\n")
    labelOut.write("articleUrl1,articleUrl2,articleUrl3,articleUrl4,articleUrl5\n")

    var lines = 1
    
    var indices = pickIndices(urlArray)
    
    while (lines <= 25){  
        urlOut.write(retEntry(urlArray,indices))
        labelOut.write(retEntry(labelArray,indices))
        indices = pickIndices(urlArray)
        println(indices)
        lines += 1
    }
    urlOut.close
    labelOut.close
  }
  
  def main(args: Array[String]) {
    val inputUrlFileName = args(0)
    val inputLabelFileName = args(1)
    val outputUrlFileName = args(2)
    val outputLabelFileName = args(3)
    
    var urlArray = readInputUrlData(inputUrlFileName)
    var labelArray = readInputLabelData(inputLabelFileName)
    createOutputFiles(urlArray, labelArray, outputUrlFileName, outputLabelFileName)
  }
}