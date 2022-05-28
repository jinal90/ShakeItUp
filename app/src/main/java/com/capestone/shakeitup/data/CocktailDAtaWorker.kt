//package com.capestone.shakeitup.data
//
//import android.content.Context
//import android.util.JsonReader
//import android.util.Log
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.withContext
//
//class CocktailDAtaWorker (
//    context: Context,
//    workerParams: WorkerParameters
//) : CoroutineWorker(context, workerParams) {
//    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
//        try {
//            val filename = inputData.getString(KEY_FILENAME)
//            if (filename != null) {
//                applicationContext.assets.open(filename).use { inputStream ->
//                    JsonReader(inputStream.reader()).use { jsonReader ->
//                        val plantList: List<Cocktail> = Gson().fromJson(jsonReader, plantType)
//
//                        val database = AppDatabase.getInstance(applicationContext)
//                        database.cocktailDao().saveCocktail()
//
//                        Result.success()
//                    }
//                }
//            } else {
//                Log.e(TAG, "Error seeding database - no valid filename")
//                Result.failure()
//            }
//        } catch (ex: Exception) {
//            Log.e(TAG, "Error seeding database", ex)
//            Result.failure()
//        }
//    }
//
//    companion object {
//        private const val TAG = "SeedDatabaseWorker"
//        const val KEY_FILENAME = "PLANT_DATA_FILENAME"
//    }
//}