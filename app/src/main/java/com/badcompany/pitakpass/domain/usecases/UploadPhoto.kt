package com.badcompany.domain.usecases

import com.badcompany.core.ResultWrapper
import com.badcompany.domain.domainmodel.PhotoBody
import com.badcompany.domain.repository.CarRepository
import com.badcompany.domain.repository.FileUploadRepository
import java.io.File


/** User Login Use Case
 *
 */
class UploadPhoto(val repository: FileUploadRepository) :
    UseCaseWithParams<File, ResultWrapper<PhotoBody>>() {

    override suspend fun buildUseCase(params: File): ResultWrapper<PhotoBody> {
        return repository.uploadPhoto(params)
    }
}