package com.epicplayera10.exampleplugin.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;

@CommandAlias("examplecommand")
@CommandPermission("exampleplugin.examplecommand")
public class ExampleCommand extends BaseCommand {
    @HelpCommand
    public void doHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }
}
