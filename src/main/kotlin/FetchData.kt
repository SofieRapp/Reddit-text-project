import com.google.gson.JsonObject
import com.google.gson.JsonParser
import java.io.File
import java.net.URI
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.UriBuilder

class FetchData {

    fun buildUri(basePath: String, vararg query: Pair<String,String>) : URI{
        val uri = UriBuilder.fromUri(basePath)
        if (query.isNotEmpty()){
            var i = 0
            while (query.size > i){
                uri.queryParam(query[i].first, query[i].second)
                i++
            }
        }
        return uri.build()
    }

    fun fetchFromUri(uri: URI) : JsonObject{
        val client = ClientBuilder.newClient()
        val response = client.target(uri).request("application/json").get()

        val result = response.readEntity(String::class.java)

        val parser = JsonParser()
        return parser.parse(result) as JsonObject
    }

    fun writeToFile(input: JsonObject){

        var filename = "src/main/resources/"
        filename += input.get("data").asJsonObject.get("children").asJsonArray[0].asJsonObject.get("data").asJsonObject.get("subreddit").asString + ".txt"
        val myFile = File(filename)

        myFile.bufferedWriter().use { out ->
            input.get("data").asJsonObject.get("children").asJsonArray.forEach {
                out.write(it.asJsonObject.get("data").asJsonObject.get("title").asString.trim() + "\n")
            }
        }
        println("File filled!")
    }

}

fun main(args: Array<String>) {
    val fetchData = FetchData()
    //Todo Tests from this?
//    println(fetchData.buildUri("https://api.met.no/weatherapi/textforecast/1.6", Pair("forecast", "land"), Pair("language", "nb")))
//    println(fetchData.buildUri("https://api.met.no/weatherapi/textforecast/1.6", Pair("forecast", "land")))
//    println(fetchData.buildUri("https://api.met.no/weatherapi/textforecast/1.6"))

    fetchData.writeToFile(fetchData.fetchFromUri(fetchData.buildUri("https://www.reddit.com/r/leagueoflegends/top/.json", Pair("limit", "100"), Pair("t", "day"))))
}