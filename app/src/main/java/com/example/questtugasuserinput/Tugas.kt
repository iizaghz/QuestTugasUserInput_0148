
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

            // ROW 1: Nama depan dan nama belakang
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.field_spacing)
                )
            ) {
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    FormInputField(
                        label = stringResource(R.string.first_name),
                        value = firstName,
                        onValueChange = { firstName = it },
                        isError = showError && firstName.isBlank()
                    )
                }

                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    FormInputField(
                        label = stringResource(R.string.last_name),
                        value = lastName,
                        onValueChange = { lastName = it },
                        isError = showError && lastName.isBlank()
                    )
                }
            }

            // ROW 2: Email selebar form
            FormInputField(
                label = stringResource(R.string.email),
                value = email,
                onValueChange = { email = it },
                isError = showError &&
                        !Patterns.EMAIL_ADDRESS.matcher(email).matches()
            )

            // ROW 3: Tanggal lahir dan pengalaman
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(R.dimen.field_spacing)
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Tanggal lahir
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = birthDate,
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text(
                                stringResource(R.string.birth_date)
                            )
                        },
                        placeholder = {
                            Text(
                                stringResource(R.string.date_format_hint)
                            )
                        },
                        trailingIcon = {
                            Text("▦")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(
                            dimensionResource(R.dimen.field_corner_radius)
                        ),
                        singleLine = true,
                        isError = showError && birthDate.isBlank()
                    )

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable {
                                val calendar = Calendar.getInstance()

                                DatePickerDialog(
                                    context,
                                    { _, year, month, day ->
                                        birthDate = String.format(
                                            Locale.getDefault(),
                                            "%02d/%02d/%04d",
                                            day,
                                            month + 1,
                                            year
                                        )
                                    },
                                    calendar.get(Calendar.YEAR),
                                    calendar.get(Calendar.MONTH),
                                    calendar.get(Calendar.DAY_OF_MONTH)
                                ).show()
                            }
                    )
                }

                // Pilihan pengalaman berupa dropdown
                Box(
                    modifier = Modifier.weight(1f)
                ) {
                    OutlinedTextField(
                        value = experience,
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text(
                                stringResource(R.string.experience_label)
                            )
                        },
                        placeholder = {
                            Text("Pilih pengalaman")
                        },
                        trailingIcon = {
                            Text("▾")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(
                            dimensionResource(R.dimen.field_corner_radius)
                        ),
                        singleLine = true,
                        isError = showError && experience.isBlank()
                    )

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable {
                                experienceExpanded = true
                            }
                    )

                    DropdownMenu(
                        expanded = experienceExpanded,
                        onDismissRequest = {
                            experienceExpanded = false
                        }
                    ) {
                        experienceOptions.forEach { option ->
                            DropdownMenuItem(
                                text = {
                                    Text(option)
                                },
                                onClick = {
                                    experience = option
                                    experienceExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            // LABEL SKILL
            Text(
                text = stringResource(R.string.skills_label),
                color = colorResource(R.color.text_primary)
            )

            // ROW 4: Tiga checkbox skill dalam satu baris
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                SkillCheckbox(
                    label = stringResource(R.string.skill_android),
                    checked = androidSkill,
                    onCheckedChange = { androidSkill = it }
                )

                SkillCheckbox(
                    label = stringResource(R.string.skill_uiux),
                    checked = uiuxSkill,
                    onCheckedChange = { uiuxSkill = it }
                )

                SkillCheckbox(
                    label = stringResource(R.string.skill_web),
                    checked = webSkill,
                    onCheckedChange = { webSkill = it }
                )
            }

            // ROW 5: Tombol Proceed selebar form
            Button(
                onClick = {
                    val valid =
                        firstName.isNotBlank() &&
                                lastName.isNotBlank() &&
                                Patterns.EMAIL_ADDRESS
                                    .matcher(email)
                                    .matches() &&
                                birthDate.isNotBlank() &&
                                experience.isNotBlank() &&
                                (androidSkill || uiuxSkill || webSkill)

                    showError = !valid
                    showSuccess = valid
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        dimensionResource(R.dimen.button_height)
                    ),
                shape = RoundedCornerShape(
                    dimensionResource(R.dimen.button_corner_radius)
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(
                        R.color.purple_primary
                    )
                )
            ) {
                Text(
                    text = stringResource(R.string.proceed),
                    fontSize = dimensionResource(
                        R.dimen.button_text_size
                    ).value.sp
                )
            }
        }
    }

    // Dialog berhasil
    if (showSuccess) {
        AlertDialog(
            onDismissRequest = {
                showSuccess = false
            },
            title = {
                Text(stringResource(R.string.success_title))
            },
            text = {
                Text(stringResource(R.string.success_message))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showSuccess = false
                    }
                ) {
                    Text(stringResource(R.string.confirm))
                }
            }
        )
    }

    // Dialog validasi
    if (showError) {
        AlertDialog(
            onDismissRequest = {
                showError = false
            },
            title = {
                Text(stringResource(R.string.form_error))
            },
            text = {
                Text(stringResource(R.string.required_field))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showError = false
                    }
                ) {
                    Text(stringResource(R.string.confirm))
                }
            }
        )
    }
}

@Composable
private fun FormInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            dimensionResource(R.dimen.field_corner_radius)
        ),
        singleLine = true,
        isError = isError
    )
}
