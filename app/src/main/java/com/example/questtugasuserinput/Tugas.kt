
package com.example.questtugasuserinput

import android.app.DatePickerDialog
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import java.util.Calendar
import java.util.Locale

@Composable
fun TugasScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            HeaderBiodata()

            FormBiodataCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(
                            R.dimen.form_overlap
                        )
                    )
            )
        }
    }
}

@Composable
private fun HeaderBiodata(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(
            id = R.drawable.header_biodata
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .fillMaxWidth()
            .height(
                dimensionResource(R.dimen.header_height)
            )
    )
}

@Composable
private fun FormBiodataCard(
    modifier: Modifier = Modifier
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var androidSkill by remember { mutableStateOf(false) }
    var uiuxSkill by remember { mutableStateOf(false) }
    var webSkill by remember { mutableStateOf(false) }

    var showSuccess by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }
    var experienceExpanded by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val beginner = stringResource(R.string.experience_beginner)
    val intermediate = stringResource(R.string.experience_intermediate)
    val advanced = stringResource(R.string.experience_advanced)

    val experienceOptions = listOf(
        beginner,
        intermediate,
        advanced
    )

    Card(
        modifier = modifier.padding(
            horizontal = dimensionResource(
                R.dimen.screen_padding
            )
        ),
        shape = RoundedCornerShape(
            dimensionResource(R.dimen.form_corner_radius)
        ),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.background_white)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(
                R.dimen.card_elevation
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(
                    dimensionResource(R.dimen.form_padding)
                ),
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(R.dimen.field_spacing)
            )
        ) {
            Text(
                text = stringResource(R.string.form_title),
                fontSize = dimensionResource(
                    R.dimen.title_size
                ).value.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.purple_primary),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = stringResource(R.string.form_subtitle),
                fontSize = dimensionResource(
                    R.dimen.subtitle_size
                ).value.sp,
                color = colorResource(R.color.text_secondary),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.small_spacing)
                )
            )


            }
        
    }
}
