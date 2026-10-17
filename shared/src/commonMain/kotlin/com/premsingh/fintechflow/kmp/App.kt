package com.premsingh.fintechflow.kmp

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF10251F)
private val Muted = Color(0xFF71817A)
private val Canvas = Color(0xFFF4F7F4)
private val Emerald = Color(0xFF087E68)
private val Mint = Color(0xFFDDF3EA)

@Composable
fun App() {
    var tab by remember { mutableStateOf("Home") }
    var dark by remember { mutableStateOf(false) }
    var frozen by remember { mutableStateOf(false) }
    var balance by remember { mutableStateOf(8420.75) }
    val scheme = if (dark) darkColorScheme(primary = Color(0xFF63D9B6), background = Color(0xFF101915), surface = Color(0xFF19241F))
    else lightColorScheme(primary = Emerald, background = Canvas, surface = Color.White)

    MaterialTheme(colorScheme = scheme) {
        Surface(Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing).padding(horizontal = 22.dp, vertical = 18.dp)) {
                Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(color = Emerald, shape = RoundedCornerShape(15.dp), modifier = Modifier.size(44.dp)) {
                            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("↗", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text("FintechFlow", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp)
                            Text("YOUR MONEY, IN FLOW", color = Muted, fontSize = 9.sp, letterSpacing = 1.4.sp)
                        }
                    }
                    Surface(color = MaterialTheme.colorScheme.surface, shape = CircleShape) {
                        Text("PS", Modifier.padding(horizontal = 13.dp, vertical = 10.dp), color = Emerald, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                    }
                }
                Spacer(Modifier.height(22.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    listOf("Home", "Activity", "Cards", "Settings").forEach { item ->
                        val selected = tab == item
                        Surface(
                            color = if (selected) Emerald else MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(18.dp),
                            modifier = Modifier.weight(1f).clickable { tab = item },
                        ) {
                            Text(item, Modifier.padding(vertical = 11.dp), color = if (selected) Color.White else Muted, fontSize = 11.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                        }
                    }
                }
                Spacer(Modifier.height(18.dp))
                Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
                    when (tab) {
                        "Home" -> HomeScreen(balance, onAdd = { balance += 125.0 }, onTransfer = { balance = (balance - 32.50).coerceAtLeast(0.0) })
                        "Activity" -> ActivityScreen()
                        "Cards" -> CardsScreen(frozen, onToggle = { frozen = !frozen })
                        else -> SettingsScreen(dark, onToggle = { dark = !dark })
                    }
                }
                Text("DEMO DATA ONLY · NO REAL ACCOUNTS", Modifier.fillMaxWidth().padding(top = 12.dp), color = Muted, fontSize = 9.sp, letterSpacing = 1.sp, textAlign = TextAlign.Center)
            }
        }
    }
}

@Composable
private fun HomeScreen(balance: Double, onAdd: () -> Unit, onTransfer: () -> Unit) {
    Text("Good morning, Prem", color = Muted, fontSize = 13.sp)
    Spacer(Modifier.height(12.dp))
    Surface(color = Ink, shape = RoundedCornerShape(28.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(22.dp)) {
            Text("TOTAL BALANCE", color = Color(0xFFB7CEC4), fontSize = 10.sp, letterSpacing = 1.4.sp)
            Spacer(Modifier.height(10.dp))
            Text("$" + "%.2f".format(balance), color = Color.White, fontSize = 36.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("↗  4.8%   this month", color = Color(0xFF83E2BF), fontSize = 12.sp)
            Spacer(Modifier.height(19.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Button(onClick = onAdd, colors = ButtonDefaults.buttonColors(containerColor = Emerald), shape = RoundedCornerShape(14.dp)) { Text("＋ Add funds") }
                Button(onClick = onTransfer, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF263B33)), shape = RoundedCornerShape(14.dp)) { Text("↗ Transfer", color = Color.White) }
            }
        }
    }
    Spacer(Modifier.height(22.dp))
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text("Recent activity", color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("See all  ›", color = Emerald, fontSize = 12.sp)
    }
    Spacer(Modifier.height(8.dp))
    ActivityRow("☕", "Morning coffee", "Today · 9:41 AM", "−$5.80", Color(0xFFFFF0DF))
    ActivityRow("↙", "Salary deposit", "Yesterday · 8:30 AM", "+$4,250.00", Mint)
    ActivityRow("◈", "Market Street", "Yesterday · 6:12 PM", "−$62.40", Color(0xFFE9EDFF))
    Spacer(Modifier.height(18.dp))
    Text("Spending this week", color = MaterialTheme.colorScheme.onBackground, fontSize = 17.sp, fontWeight = FontWeight.Bold)
    Spacer(Modifier.height(10.dp))
    Surface(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(22.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Text("$384.20", color = MaterialTheme.colorScheme.onBackground, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text("of $700 weekly budget", color = Muted, fontSize = 11.sp)
            Spacer(Modifier.height(12.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp), verticalAlignment = Alignment.Bottom) {
                listOf(34, 58, 42, 76, 51, 88, 30).forEachIndexed { index, height ->
                    Box(Modifier.weight(1f).height(height.dp).background(if (index == 5) Emerald else Mint, RoundedCornerShape(8.dp)))
                }
            }
        }
    }
}

@Composable
private fun ActivityScreen() {
    Text("Your activity", color = MaterialTheme.colorScheme.onBackground, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    Text("A clear view of where money moves.", color = Muted, fontSize = 12.sp)
    Spacer(Modifier.height(18.dp))
    listOf("TODAY" to listOf("Coffee Bar" to "−$5.80", "Metro pass" to "−$3.25"), "YESTERDAY" to listOf("Salary deposit" to "+$4,250.00", "Market Street" to "−$62.40", "Streaming" to "−$14.99")).forEach { (day, rows) ->
        Text(day, color = Muted, fontSize = 10.sp, letterSpacing = 1.3.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(vertical = 10.dp))
        rows.forEach { (name, amount) -> ActivityRow("•", name, "Card · Completed", amount, Mint) }
    }
}

@Composable
private fun CardsScreen(frozen: Boolean, onToggle: () -> Unit) {
    Text("Your card", color = MaterialTheme.colorScheme.onBackground, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    Text("Controls for your everyday card.", color = Muted, fontSize = 12.sp)
    Spacer(Modifier.height(18.dp))
    Surface(color = if (frozen) Color(0xFF59645E) else Emerald, shape = RoundedCornerShape(26.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(22.dp)) {
            Text("FINTECHFLOW  ·  DEBIT", color = Color(0xFFC8EBDE), fontSize = 10.sp, letterSpacing = 1.4.sp)
            Spacer(Modifier.height(30.dp))
            Text("••••   ••••   ••••   4288", color = Color.White, fontSize = 18.sp, letterSpacing = 1.sp)
            Spacer(Modifier.height(18.dp))
            Text("PREM SINGH", color = Color.White, fontSize = 11.sp, letterSpacing = 1.3.sp)
        }
    }
    Spacer(Modifier.height(18.dp))
    Surface(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(22.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Text(if (frozen) "Card is frozen" else "Card is active", color = MaterialTheme.colorScheme.onBackground, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text("Freeze controls demo transactions only.", color = Muted, fontSize = 11.sp, modifier = Modifier.padding(top = 5.dp, bottom = 14.dp))
            Button(onClick = onToggle, colors = ButtonDefaults.buttonColors(containerColor = if (frozen) Emerald else Color(0xFFA84242)), shape = RoundedCornerShape(14.dp)) { Text(if (frozen) "Unfreeze card" else "Freeze card") }
        }
    }
}

@Composable
private fun SettingsScreen(dark: Boolean, onToggle: () -> Unit) {
    Text("Preferences", color = MaterialTheme.colorScheme.onBackground, fontSize = 25.sp, fontWeight = FontWeight.Bold)
    Text("Make your money view feel like yours.", color = Muted, fontSize = 12.sp)
    Spacer(Modifier.height(18.dp))
    Surface(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(22.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Text("Appearance", color = MaterialTheme.colorScheme.onBackground, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(if (dark) "Night mode is on" else "Day mode is on", color = Muted, fontSize = 12.sp, modifier = Modifier.padding(vertical = 10.dp))
            Button(onClick = onToggle, colors = ButtonDefaults.buttonColors(containerColor = Emerald), shape = RoundedCornerShape(14.dp)) { Text(if (dark) "Switch to day" else "Switch to night") }
        }
    }
    Spacer(Modifier.height(12.dp))
    Surface(color = MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(22.dp), modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(18.dp)) {
            Text("Privacy by design", color = MaterialTheme.colorScheme.onBackground, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text("Sample names and balances stay in this demo. There is no connection to a bank or payment service.", color = Muted, fontSize = 12.sp, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
private fun ActivityRow(icon: String, title: String, detail: String, amount: String, badge: Color) {
    Row(Modifier.fillMaxWidth().padding(vertical = 7.dp), verticalAlignment = Alignment.CenterVertically) {
        Surface(color = badge, shape = CircleShape, modifier = Modifier.size(42.dp)) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(icon, color = Emerald, fontWeight = FontWeight.Bold, fontSize = 17.sp) }
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = MaterialTheme.colorScheme.onBackground, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Text(detail, color = Muted, fontSize = 10.sp)
        }
        Text(amount, color = MaterialTheme.colorScheme.onBackground, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}
