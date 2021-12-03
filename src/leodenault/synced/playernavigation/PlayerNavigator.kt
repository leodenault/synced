package leodenault.synced.playernavigation

import leodenault.synced.navigation.Navigator

interface PlayerNavigator : Navigator {
  fun navigateToPlayer()
}