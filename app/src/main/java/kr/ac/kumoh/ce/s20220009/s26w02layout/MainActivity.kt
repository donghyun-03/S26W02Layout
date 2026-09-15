package kr.ac.kumoh.ce.s20220009.s26w02layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.ce.s20220009.s26w02layout.ui.theme.S26W02LayoutTheme   // ← 빨간 줄이면 지우고 Alt+Enter로 자동 import

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S26W02LayoutTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    // 로그인 여부와 입력값을 화면 상태로 기억
    var loggedIn by remember { mutableStateOf(false) }
    var studentId by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        if (!loggedIn) {
            LoginScreen(
                studentId = studentId,
                name = name,
                onStudentIdChange = { studentId = it },
                onNameChange = { name = it },
                onSubmit = { loggedIn = true },
                modifier = Modifier.padding(innerPadding)
            )
        } else {
            MyLinearLayout(
                studentId = studentId,
                name = name,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun LoginScreen(
    studentId: String,
    name: String,
    onStudentIdChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "로그인", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = studentId,
            onValueChange = onStudentIdChange,
            label = { Text("학번") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("이름") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onSubmit,
            enabled = studentId.isNotBlank() && name.isNotBlank(),  // 둘 다 입력해야 활성화
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("확인")
        }
    }
}

@Composable
fun MyLinearLayout(
    studentId: String,
    name: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = "안녕하세요",
            modifier = Modifier
                .background(Color.Yellow)
                .padding(8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Row {
            Text(
                text = "국립금오공과대학교",
                modifier = Modifier
                    .background(Color.Cyan)
                    .padding(8.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
            )
            Text(
                text = "스마트 앱 프로그래밍",
                modifier = Modifier
                    .background(Color.Magenta)
                    .padding(8.dp)
                    .weight(1f),
                textAlign = TextAlign.Center,
            )
        }

        // ↓↓↓ 로그인에서 입력한 학번·이름이 추가로 표시되는 부분
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "학번: $studentId",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
        Text(
            text = "이름: $name",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
        )
    }
}