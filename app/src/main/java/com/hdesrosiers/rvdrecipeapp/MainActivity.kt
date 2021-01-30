package com.hdesrosiers.rvdrecipeapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.GsonBuilder
import com.hdesrosiers.rvdrecipeapp.domain.model.Recipe
import com.hdesrosiers.rvdrecipeapp.network.RecipeService
import com.hdesrosiers.rvdrecipeapp.network.model.RecipeDto
import com.hdesrosiers.rvdrecipeapp.network.model.RecipeDtoMapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


@AndroidEntryPoint // mark this component to be setup for di with Hilt/Dagger
class MainActivity : AppCompatActivity() {

    private val TAG: String = "AppDebug"

    // field injection because constructor injection doesn't work on activities
    @Inject
    lateinit var app: BaseApplication

    @Inject
    lateinit var someRandomString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: $someRandomString")
        Log.d(TAG, "onCreate: $app")

//        // build a recipe retrofit service object to access network data
//        val service = Retrofit.Builder()
//            .baseUrl("https://food2fork.ca/api/recipe/")
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//            .build()
//            .create(RecipeService::class.java)
//
//        // example request using coroutine on background thread (IO instead of Main)
//        CoroutineScope(IO).launch {
//            val recipe = service.get(
//                token = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48",
//                id = 583
//            )
//            Log.d("MainActivity", "onCreate: ${recipe.title}")
//        }
//
//        // example mapping of RecipeNetworkEntity <-> Recipe
//        val mapper = RecipeDtoMapper()
//        val recipe = Recipe()
//
//        val dto: RecipeDto = mapper.mapFromDomainModel(recipe)
//        val r: Recipe = mapper.mapToDomainModel(dto)

//        setContent {
//            ScrollableColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(
//                        color = Color(0xfff2f2f2)
//                    )
//            ) {
//                Image(
//                    bitmap = imageFromResource(
//                        res = resources,
//                        resId = R.drawable.happy_meal_small
//                    ),
//                    modifier = Modifier.height(300.dp),
//                    contentScale = ContentScale.Crop
//                )
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                    ) {
//                        Text(
//                            text = "Happy Meal",
//                            style = TextStyle(
//                                fontSize = TextUnit.Companion.Sp(
//                                    26
//                                )
//                            )
//                        )
//
//                        Text(
//                            text = "$5.99",
//                            style = TextStyle(
//                                color = Color(0xff85bb65),
//                                fontSize = TextUnit.Companion.Sp(
//                                    17
//                                )
//                            ),
//                            modifier = Modifier
//                                .align(alignment = Alignment.CenterVertically)
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.padding(top = 10.dp))
//
//                    Text(
//                        text = "800 Calories",
//                        style = TextStyle(
//                            fontSize = TextUnit.Companion.Sp(
//                                17
//                            )
//                        )
//                    )
//                    Spacer(modifier = Modifier.padding(top = 10.dp))
//
//                    Button(
//                        onClick = { /*TODO*/ },
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally)
//                    ) {
//                        Text(text = "ORDER NOW")
//                    }
//                }
//            }
//        }
    }
}