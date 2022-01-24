
import com.google.gson.annotations.SerializedName

data class ResponseRefreshTokenData(
    val `data`: Data,
    val message: String,
    val status: Int,
    val success: Boolean
)