package com.girogevoro.gitapp.ui.navigation

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.*
import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.AppScreen

class NavigatorOnlyActivity(
    protected val activity: FragmentActivity
) : Navigator {
    override fun applyCommands(commands: Array<out Command>) {
        for (command in commands) {
            try {
                applyCommand(command)
            } catch (e: RuntimeException) {
                errorOnApplyCommand(command, e)
            }
        }
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Forward -> forward(command)
            is Back -> activityBack()
        }
    }

    private fun forward(command: Forward) {
         when (val screen = command.screen as AppScreen) {
            is ActivityScreen -> {
                checkAndStartActivity(screen)
            }
             else -> {}
         }
    }

    private fun checkAndStartActivity(screen: ActivityScreen) {
        // Check if we can start activity
        val activityIntent = screen.createIntent(activity)
        try {
            activity.startActivity(activityIntent, screen.startActivityOptions)
        } catch (e: ActivityNotFoundException) {
            unexistingActivity(screen, activityIntent)
        }
    }

    private fun activityBack() {
        activity.finish()
    }

    private fun errorOnApplyCommand(
        command: Command,
        error: RuntimeException
    ) {
        throw error
    }

    private fun unexistingActivity(
        screen: ActivityScreen,
        activityIntent: Intent
    ) {
        // Do nothing by default
    }
}