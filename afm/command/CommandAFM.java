package afm.command;

import java.util.Arrays;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandServerBan;
import net.minecraft.command.CommandServerBanlist;
import net.minecraft.command.ICommandSender;
import afm.core.Properties;

public class CommandAFM extends CommandBase {

	@Override
	public String getCommandName() {
		return "afm";
	}

	@Override
	public String getCommandUsage(ICommandSender par1iCommandSender){
		return "/afm [setAt | getAt | killAll]";
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
		return commandSender.canCommandSenderUseCommand(3, "ban");
	}

	@Override
	public List addTabCompletionOptions(ICommandSender par1iCommandSender,
			String[] args) {
		
		if(args.length > 0){
			if(args[0].equalsIgnoreCase("setAt")){
				return CommandSetAt.getTabCompletion(par1iCommandSender, args);
			} else if(args[0].equalsIgnoreCase("getAt")){
				return CommandGetAt.getTabCompletion(par1iCommandSender, args);
			} else if(args[0].equalsIgnoreCase("killAll")){
				return CommandGetAt.getTabCompletion(par1iCommandSender, args);
			}
		}
		return Arrays.asList(new String[]{"setAt", "getAt", "killAll"});
	}

	@Override
	public void processCommand(ICommandSender commandSender, String[] args) {
		
		if(args.length < 1){
			commandSender.sendChatToPlayer("Usage: " + this.getCommandUsage(commandSender));
			return;
		}
		String command = args[0];
		if(command.equalsIgnoreCase("setAt")){
			CommandSetAt.handle(commandSender, args);
		} else if(command.equalsIgnoreCase("getAt")){
			CommandGetAt.handle(commandSender, args);
		} else if(command.equalsIgnoreCase("killAll")){
			CommandKillAll.handle(commandSender, args);
		} else {
			commandSender.sendChatToPlayer("You wrote: " + Arrays.toString(args));
		}
	}

}
