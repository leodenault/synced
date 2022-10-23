package leodenault.synced.botaccess

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import leodenault.synced.coroutines.CoroutineScopes
import leodenault.synced.discord.DisconnectedClient
import leodenault.synced.discordnavigation.DiscordNavigator
import leodenault.synced.settings.SettingsManager

class BotAccessPageViewModel constructor(
    private val discordClient: DisconnectedClient,
    private val discordNavigator: DiscordNavigator,
    private val settingsManager: SettingsManager,
    initialErrorMessage: String? = null
) {
    var isConnecting by mutableStateOf(false)
    var botToken: String by mutableStateOf(settingsManager.fetch().botToken)
    var errorMessage by mutableStateOf(initialErrorMessage)
    var rememberBotToken by mutableStateOf(botToken.isNotEmpty())

    fun onConnectClick() {
        CoroutineScopes.viewModelScope.launch {
            discordClient.connect(botToken).fold(
                onSuccess = {
                    val settingsBuilder = settingsManager.fetch().toBuilder()
                    settingsManager.commit(
                        if (rememberBotToken) {
                            settingsBuilder.setBotToken(botToken).build()
                        } else {
                            settingsBuilder.clearBotToken().build()
                        }
                    )
                    discordNavigator.navigateToServerSelector(it)
                },
                onFailure = {
                    errorMessage =
                        "Failed to authenticate with bot token. Is the token typed correctly?"
                    isConnecting = false
                }
            )
        }
        isConnecting = true
    }

    fun onRememberBotTokenChange(newValue: Boolean) {
        rememberBotToken = newValue
    }
}