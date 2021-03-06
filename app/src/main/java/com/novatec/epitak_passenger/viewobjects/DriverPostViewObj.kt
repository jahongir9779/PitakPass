package com.novatec.epitak_passenger.viewobjects

import android.os.Parcelable
import com.novatec.epitak_passenger.core.enums.EFuelType
import com.novatec.epitak_passenger.core.enums.EPostType
import com.novatec.epitak_passenger.domain.model.DriverPost
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DriverPostViewObj(
    val id: Long,
    val from: PlaceViewObj,
    val to: PlaceViewObj,
    var price: Int,
    val departureDate: String,
    val finishedDate: String? = null,
    val timeFirstPart: Boolean,
    val timeSecondPart: Boolean,
    val timeThirdPart: Boolean,
    val timeFourthPart: Boolean,
    val profile: ProfileViewObj? = null,
    val carId: Long? = null,
    val car: CarInPostViewObj? = null,
    val remark: String? = null,
    var seat: Int,
    val availableSeats: Int,
    val myLastOffer: UserOfferViewObj? = null,
    val passengerList: List<PassengerViewObj>? = null,
    var postType: EPostType
) : Parcelable {


    companion object {
        fun mapFromDriverPostModel(model: DriverPost): DriverPostViewObj {
            val profile = ProfileViewObj(
                model.profile?.phoneNum,
                model.profile?.name,
                model.profile?.surname,
                model.profile?.id,
                ImageViewObj(
                    model.profile?.image?.id,
                    model.profile?.image?.link,
                )
            )
            val passengerList = arrayListOf<PassengerViewObj>()
            model.passengerList?.forEach {
                passengerList.add(
                    PassengerViewObj(
                        it.id,
                        ProfileViewObj(
                            it.profile?.phoneNum,
                            it.profile?.name,
                            it.profile?.surname,
                            it.profile?.id,
                            ImageViewObj(
                                it.profile?.image?.id,
                                it.profile?.image?.link
                            )
                        )
                    )
                )
            }
            val myLastOffer =
                if (model.myLastOffer != null) UserOfferViewObj(
                    model.myLastOffer.id,
                    model.myLastOffer.id,
                    model.myLastOffer.repliedPostId,
                    model.myLastOffer.status,
                    model.myLastOffer.message,
                    model.myLastOffer.submitDate,
                    model.myLastOffer.priceInt,
                    model.myLastOffer.seat
                ) else null

            return DriverPostViewObj(
                model.id,
                PlaceViewObj(
                    model.from.districtId,
                    model.from.regionId,
                    model.from.lat,
                    model.from.lon,
                    model.from.regionName,
                    model.from.districtName
                ),
                PlaceViewObj(
                    model.to.districtId,
                    model.to.regionId,
                    model.to.lat,
                    model.to.lon,
                    model.to.regionName,
                    model.to.districtName
                ),
                model.price,
                model.departureDate,
                model.finishedDate,
                model.timeFirstPart,
                model.timeSecondPart,
                model.timeThirdPart,
                model.timeFourthPart,
                profile,
                model.carId,
                CarInPostViewObj(
                    model.car!!.id,
                    model.car.modelId,
                    ImageViewObj(
                        model.car.image!!.id,
                        model.car.image!!.link
                    ),
                    CarModelViewObj(
                        model.car.carModel!!.id,
                        model.car.carModel!!.name
                    ),
                    model.car.fuelType,
                    model.car.colorId,
                    model.car.carNumber,
                    model.car.carYear,
                    model.car.airConditioner
                ),
                model.remark,
                model.seat,
                model.availableSeats,
                myLastOffer,
                passengerList,
                model.postType
            )
        }
    }
}

@Parcelize
data class UserOfferViewObj(
    val id: Long,
    val postId: Long,
    val repliedPostId: Long,
    val status: String,
    val message: String,
    val submitDate: String,
    val priceInt: Int,
    val seat: Int
) : Parcelable

@Parcelize
data class PassengerViewObj(
    var id: Long? = null,
    var profile: ProfileViewObj? = null,
    var submitDate: String? = null
) : Parcelable

/**
 * Representation for a [CarInPost] fetched from the API
 */
@Parcelize
data class CarInPostViewObj(
    var id: Long? = null,
    var modelId: Long? = null,
    var image: ImageViewObj? = null,
    var carModel: CarModelViewObj? = null,
    var fuelType: EFuelType? = null,
    var colorId: Long? = null,
    var carNumber: String? = null,
    var carYear: Int? = null,
    var airConditioner: Boolean? = null
) : Parcelable


@Parcelize
data class CarModelViewObj(
    val id: Long,
    val name: String? = null
) : Parcelable
