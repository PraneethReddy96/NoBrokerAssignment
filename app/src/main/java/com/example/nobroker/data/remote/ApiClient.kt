


import com.example.nobroker.data.model.NoBrokerResponse
import retrofit2.http.*

interface  ApiClient {

    @GET("/b/60fa8fefa917050205ce5470")
    suspend  fun getDetails(): NoBrokerResponse


}

