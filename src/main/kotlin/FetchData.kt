import com.google.gson.JsonObject
import com.google.gson.JsonParser
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

    fun fetchFromUri(uri: URI){
        val client = ClientBuilder.newClient()
        val response = client.target(uri).request("application/json").get()

        val result = response.readEntity(String::class.java)
        println("Result as string : $result")

        val parser = JsonParser()
        val json = parser.parse(result) as JsonObject
        val temperature = json.get("data").asJsonObject.get("children").asJsonArray.get(0).asJsonObject.get("data").asJsonObject.get("title").asString

        println(temperature)
    }
}

fun main(args: Array<String>) {
    val fetchData = FetchData()
    //Todo Tests from this?
//    println(fetchData.buildUri("https://api.met.no/weatherapi/textforecast/1.6", Pair("forecast", "land"), Pair("language", "nb")))
//    println(fetchData.buildUri("https://api.met.no/weatherapi/textforecast/1.6", Pair("forecast", "land")))
//    println(fetchData.buildUri("https://api.met.no/weatherapi/textforecast/1.6"))

    fetchData.fetchFromUri(fetchData.buildUri("https://www.reddit.com/r/leagueoflegends/top/.json", Pair("limit", "5"), Pair("t", "day")))
}