package com.iBaby.commands;

import java.util.Set;

import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

public class FakeCommandSender implements CommandSender {

	public PermissionAttachment addAttachment(Plugin arg0) { return null; }

	public PermissionAttachment addAttachment(Plugin arg0, int arg1) { return null; }

	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2) { return null; }

	public PermissionAttachment addAttachment(Plugin arg0, String arg1, boolean arg2, int arg3) { return null;}

	public Set<PermissionAttachmentInfo> getEffectivePermissions() { return null; }

	public boolean hasPermission(String arg0) { return false;}

	public boolean hasPermission(Permission arg0) { return false;}

	public boolean isPermissionSet(String arg0) { return false; }

	public boolean isPermissionSet(Permission arg0) { return false; }

	public void recalculatePermissions() {}

	public void removeAttachment(PermissionAttachment arg0) {}

	public boolean isOp() { return false; }

	public void setOp(boolean arg0) {}

	public String getName() { return null; }

	public Server getServer() { return null; }

	public void sendMessage(String arg0) {}

	public void sendMessage(String[] arg0) {}

}
