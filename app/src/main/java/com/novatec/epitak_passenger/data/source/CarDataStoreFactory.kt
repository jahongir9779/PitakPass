//package com.novatec.epitak_passenger.data.source
//
//import com.novatec.epitak_passenger.data.repository.CarDataStore
//import javax.inject.Inject
//
///**
// * Create an instance of a BufferooDataStore
// */
//open class CarDataStoreFactory @Inject constructor(
//    private val carRemoteDataStore: CarRemoteDataStore) {
//
//    /**
//     * Returns a DataStore based on whether or not there is content in the cache and the cache
//     * has not expired
//     */
//    open fun retrieveDataStore(isCached: Boolean): CarDataStore {
////        if (isCached && !userCache.isExpired()) {
////            return retrieveCacheDataStore()
////        }
//        return retrieveRemoteDataStore()
//    }
//
////    /**
////     * Return an instance of the Cache Data Store
////     */
////    open fun retrieveCacheDataStore(): UserDataStore {
////        return userCacheDataStore
////    }
//
//    /**
//     * Return an instance of the Remote Data Store
//     */
//    open fun retrieveRemoteDataStore(): CarDataStore {
//        return carRemoteDataStore
//    }
//
//}