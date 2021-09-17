package spark.practise
import scala.collection.mutable.{ HashMap, MultiMap, Set,Map }
import scala.io.Source
import scala.collection.JavaConversions._
import java.io.File
object assignment {
  def main(args: Array[String]): Unit = {
   val dirpath = "/home/rajkumar/Documents/"
   var counter = 0
   var missedsignal = 0
   var total = 0.0
   var tupleorder = ()
   
    var hashMap =  Map[String, Array[String]]()
    
    


def getListOfFiles(dir: File, extensions: List[String]): List[File] = {
    dir.listFiles.filter(_.isFile).toList.filter { file =>
        extensions.exists(file.getName.endsWith(_))
    }
}
val okFileExtensions = List("csv")
val files = getListOfFiles(new File(dirpath), okFileExtensions)
val noofprocessedfiles = files.length
print("The no ofprocessedfiles: ", noofprocessedfiles)

for (elem <-files){
  
  for (line <- Source.fromFile(elem).getLines.drop(1)) {
    counter = counter+1
    
    println(line)
    var key1 = line.split(",")(0)
    var value = line.split(",")(1)
    hashMap(key1) = Array(value)
}

}
print(" The no of measurements it processed : ", counter)
print(hashMap)

for (value <- hashMap.keys){
  val list = hashMap(value)
  var max = list.reduceLeft(_ max_)
  var min = list.reduceLeft(_ min_)
        
      for ( i <- 0 to (list.length - 1)) {
        if(list[i] == "NaN"){
          continue
        }
         total += list(i).toInt;
      }
  tupleorder = tupleorder:+(value,max,min,total)

  for (listcont <- list){
    if(listcont == "NaN"){
      missedsignal = missedsignal+1
      
    }
  }
}

val newtuple = tupleorder._4.order()

print(" The no of measurements Failed : ", missedsignal)

print("Sensors with highest avg humidity:", newtuple)

}
}

