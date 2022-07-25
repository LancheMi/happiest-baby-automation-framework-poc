package util

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import org.testng.SkipException
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

fun getTestData(fileName: String): Array<Array<Any?>> {
    val csvFile = File(fileName)
    val mapper = CsvMapper()
    val schema = CsvSchema.emptySchema().withHeader().withColumnSeparator(';')
    val it = mapper.readerFor(Hashtable::class.java)
        .with(schema)
        .readValues<Hashtable<String, String>>(csvFile)

    val hashtableList: ArrayList<Hashtable<String, String>> = ArrayList()

    while (it.hasNext()) {
        val rowAsMap = it.next()
        hashtableList.add(rowAsMap)
    }

    val dataSet = Array(hashtableList.size) {
        arrayOfNulls<Any>(
            1
        )
    }

    var dataRowNumber = 0
    for (elem in hashtableList) {
        dataSet[dataRowNumber][0] = elem
        dataRowNumber++
    }

    return dataSet
}

fun executeTest(condition: Boolean) {
    if (condition) {
        throw SkipException("Test skipped")
    }
}

fun executeTest(condition: String) {
    if (condition == "false") {
        throw SkipException("Test skipped")
    }
}