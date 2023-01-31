package leodenault.synced.app

import dagger.Component
import leodenault.synced.audio.AudioDaggerModule
import leodenault.synced.desktopaudio.DesktopAudioDaggerModule
import leodenault.synced.discord.DiscordDaggerModule
import leodenault.synced.loadingpage.LoadingPageDaggerModule
import leodenault.synced.navigation.NavigationDaggerModule
import leodenault.synced.player.PlayerDaggerModule
import leodenault.synced.channelselection.ChannelSelectionDaggerModule
import leodenault.synced.settings.SettingsDaggerModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AudioDaggerModule::class,
    DesktopAudioDaggerModule::class,
    DiscordDaggerModule::class,
    LoadingPageDaggerModule::class,
    NavigationDaggerModule::class,
    PlayerDaggerModule::class,
    ChannelSelectionDaggerModule::class,
    SettingsDaggerModule::class,
  ]
)
interface ApplicationComponent {
  val application: Application
}