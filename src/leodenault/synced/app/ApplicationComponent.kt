package leodenault.synced.app

import dagger.Component
import leodenault.synced.audio.AudioDaggerModule
import leodenault.synced.desktopaudio.DesktopAudioDaggerModule
import leodenault.synced.discord.DiscordDaggerModule
import leodenault.synced.error.ErrorDaggerModule
import leodenault.synced.loadingpage.LoadingPageDaggerModule
import leodenault.synced.discordnavigation.DiscordNavigationDaggerModule
import leodenault.synced.player.PlayerDaggerModule
import leodenault.synced.playernavigation.PlayerNavigationDaggerModule
import leodenault.synced.serverselector.ServerSelectorDaggerModule
import leodenault.synced.settings.SettingsDaggerModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AudioDaggerModule::class,
    DesktopAudioDaggerModule::class,
    DiscordDaggerModule::class,
    DiscordNavigationDaggerModule::class,
    ErrorDaggerModule::class,
    LoadingPageDaggerModule::class,
    PlayerDaggerModule::class,
    PlayerNavigationDaggerModule::class,
    ServerSelectorDaggerModule::class,
    SettingsDaggerModule::class,
  ]
)
interface ApplicationComponent {
  val application: Application
}