package com.badcompany.pitakpass.remote.model

import com.badcompany.pitakpass.util.Constants
import com.google.gson.annotations.SerializedName

/**
 * Representation for a [PassengerPostModel] fetched from the API
 */
data class PassengerPostModel(@SerializedName("id") val id: Long?=null,
                              @SerializedName("from") val from: PlaceModel,
                              @SerializedName("to") val to: PlaceModel,
                              @SerializedName("price") val price: Int,
                              @SerializedName("departureDate") val departureDate: String,
                              @SerializedName("createdDate") val createdDate: String,
                              @SerializedName("updatedDate") val updatedDate: String,
                              @SerializedName("finishedDate") val finishedDate: String?=null,
                              @SerializedName("timeFirstPart") val timeFirstPart: Boolean,
                              @SerializedName("timeSecondPart") val timeSecondPart: Boolean,
                              @SerializedName("timeThirdPart") val timeThirdPart: Boolean,
                              @SerializedName("timeFourthPart") val timeFourthPart: Boolean,
                              @SerializedName("airConditioner") val airConditioner: Boolean,
                              @SerializedName("remark") val remark: String,
                              @SerializedName("postStatus") val postStatus: String,
                              @SerializedName("seat") val seat: Int,
                              @SerializedName("postType") val postType: String = Constants.PASSENGER_POST_SIMPLE)