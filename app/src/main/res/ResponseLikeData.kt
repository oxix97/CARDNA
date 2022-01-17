
import com.google.gson.annotations.SerializedName

data class ResponseLikeData(
    val `data`: DataX,
    val message: String,
    val status: Int,
    val success: Boolean
)