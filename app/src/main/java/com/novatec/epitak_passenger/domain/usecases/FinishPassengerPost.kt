package com.novatec.epitak_passenger.domain.usecases

import com.novatec.epitak_passenger.domain.repository.PassengerPostRepository
import com.novatec.epitak_passenger.util.ResultWrapper


/** User Login Use Case
 *
 */
class FinishPassengerPost(val repositoryPassenger: PassengerPostRepository) :
    UseCaseWithParams<String, ResultWrapper<Unit>>() {

    override suspend fun buildUseCase(params: String) =
        repositoryPassenger.finishPassengerPost(params)
}