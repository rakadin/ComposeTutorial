package com.example.composetutorial

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composetutorial.models.WellnessTask
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import com.example.composetutorial.viewmodels.WellnessViewModel

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,

                ) {
                    WellnessScreen()
                }
            }
        }
    }
}
@Preview
@Composable
fun WellnessScreen(modifier: Modifier = Modifier,
                   wellnessViewModel : WellnessViewModel = viewModel()){
    Column(modifier = modifier) {
        WaterCupNumberLayout()
        WellnessItemList(
            list = wellnessViewModel.tasks,
            onCloseTask = {task ->
                wellnessViewModel.remove(task)
            } ,
            onCheckedChange = {task, checked -> wellnessViewModel.changeTaskChecked(task, checked)}
            )
    }
}
@Preview
@Composable
fun PreviewCupNumberLayout(){
    WaterCupNumberLayout()

}
@Composable
fun WaterCupNumberLayout(modifier: Modifier = Modifier){
    var count by rememberSaveable { mutableStateOf(0) }
    Row (modifier = modifier
        .height(100.dp)
        .fillMaxWidth()
        .padding(all = 6.dp)
        .background(color = MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically){

        Image(painter = painterResource(id = R.drawable.water_cup), contentDescription = null,
            contentScale = ContentScale.Crop,

            modifier = Modifier
                .padding(6.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Column {
            Text(
                text = "Water consume?",
                modifier = Modifier.padding(horizontal = 8.dp)
                    ,
                style = MaterialTheme.typography.headlineMedium

            )
            if(count>0){
                if(count == 10){
                    Text(text = "$count cups complete!!!",
                        modifier = Modifier.padding(horizontal = 8.dp))
                }
                else{
                    if(count == 1){ // if count > 1 -> cups
                        Text(text = "You have drink $count cup",
                            modifier = Modifier.padding(horizontal = 8.dp))
                    }
                    else{
                        Text(text = "You have drink $count cups",
                            modifier = Modifier.padding(horizontal = 8.dp))
                    }

                }

            }
        }
        Button(onClick = { count++ },modifier = Modifier.padding(all = 6.dp), enabled = count <10) {
            Text(text = "ADD",
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}
@Composable
fun WellnessTaskItem(
    taskName: String,
    checked : Boolean,
    onCheckedChange: (Boolean) ->Unit,
    onClose : () ->Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        IconButton(onClick = { onClose()}) {
            Icon(Icons.Filled.Close, contentDescription = null)
        }
    }
}
@Composable
fun WellnessItemList(
    list: List<WellnessTask>,
    onCloseTask :(WellnessTask) ->Unit,
    onCheckedChange: (WellnessTask, Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
    ){
        items(items = list,
            key = {task -> task.id}
        ){
            task-> WellnessTaskItem(
            taskName = task.label,
            onClose = { onCloseTask(task)},
            checked = task.checked,
            onCheckedChange = {checked -> onCheckedChange(task, checked)}

            )
        }
    }
}

//@Composable
//fun WellnessTaskItems(taskName: String, onClose: () -> Unit, modifier: Modifier = Modifier){
//    var checkedState by rememberSaveable {
//        mutableStateOf(false)
//    }
//    WellnessTaskItem(taskName = taskName,
//        checked = checkedState ,
//        onCheckedChange = {newValue -> checkedState = newValue},
//        onClose = {onClose()},
//        modifier = modifier)
//}