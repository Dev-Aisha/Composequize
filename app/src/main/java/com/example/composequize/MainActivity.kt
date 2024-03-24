package com.example.composequize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composequize.ui.theme.ComposeQuizeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuizeTheme {

//                val state1= remember { mutableStateListOf(0)
//                    var state2 by remember { mutableStateListOf(0)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Column(Modifier.padding(16.dp)) {
                       Quiz()


                       }

                   }
                }
            }
        }
    }
@Composable
fun Quiz(
    viewModel:QuizViewModel=androidx.lifecycle.viewmodel.compose.viewModel(),
    ){
    LazyColumn(Modifier.padding(16.dp)) {
        items(viewModel.qlist){qitem->
            Text(text = qitem.ques)
            val seloption = rememberSaveable{
                mutableStateOf("")
            }

            qitem.answ.forEach{answer->
                Row(){
                    Modifier
                        .padding(vertical = 12.dp)
                        .fillMaxWidth()
                        .clickable {
                            seloption.value = answer
                            viewModel.checkAnswer(qitem, answer)
                        }
                    RadioButton(
                        selected = (answer == seloption.value) ,
                        onClick = {
                            seloption.value =answer
                            viewModel.checkAnswer(qitem,answer)

                    }
                    )
                    Text(text = answer)
                }
            }
        }
        item{
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    viewModel.onSubmit()

                }){
                    Text(text = "submit")
            }

            }
        }
        item {
            Text(text = viewModel.score.value)

        }

    }
}

